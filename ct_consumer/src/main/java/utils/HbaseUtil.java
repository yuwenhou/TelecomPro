package utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 1.namespace ====》 命名空间
 * 2.createTable ====》 表
 * 3.isTable ====》 判断表是否存在
 * 4.Region、RowKey、分区键
 */

public class HbaseUtil {
    /**
     *初始化命名空间
     * @param conf 配置对象
     * @param namespace 命名空间的名字
     * @throws Exception
     */
    public static void initNameSpace(Configuration conf, String namespace) throws Exception {
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        //命名空间描述器
        NamespaceDescriptor nd = NamespaceDescriptor
                .create(namespace)
                .addConfiguration("AUTHOR", "Yuwen")
                .build();
        //通过admin对象来创建命名空间
        admin.createNamespace(nd);
        //关闭两个对象
        close(admin,connection);
    }
    //关闭admin对象和connection对象
    private static void close(Admin admin, Connection connection) throws IOException {
        if(admin != null){
            admin.close();
        }
        if(connection != null){
            connection.close();
        }
    }

    /**
     * 创建Hbase的表
     * @param conf
     * @param tableName
     * @param regions
     * @param columnFamily
     */
    public static  void createTable(Configuration conf, String tableName, int regions, String... columnFamily ) throws IOException {
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        //判断表是否存在
        if (isExistTable(conf,tableName)){
            return;
        }
        //表描述器 HTableDescriptor
        HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(tableName));
        for(String cf : columnFamily){
            //列描述器
            htd.addFamily(new HColumnDescriptor(cf));
        }
        //创建表
        admin.createTable(htd,genSplitKeys(regions));
        //关闭对象
        close(admin,connection);
    }

    /**
     * 分区键
     *
     *
     * @param regions  region个数
     * @return splitkyes
     */
    private static byte[][] genSplitKeys(int regions) {
        //存放分区键的数组
        String[] keys = new String[regions];
        //格式化分区键的形式 00|01|02|
        DecimalFormat df = new DecimalFormat("00");
        for (int i =0; i<regions;i++){
            keys[i] = df.format(i) + "|";
        }

        byte[][] splitKeys = new byte[regions][];
        //排序 保证你这个分区键是有序的
        TreeSet<byte[]> treeSet = new TreeSet<>(Bytes.BYTES_COMPARATOR);
        for (int i =0; i< regions;i++){
            treeSet.add(Bytes.toBytes(keys[i]));
        }
        //输出
        Iterator<byte[]> iterator = treeSet.iterator();
        int index = 0;
        while (iterator.hasNext()){
            byte[] next = iterator.next();
            splitKeys[index++] = next;
        }

        return splitKeys;
    }

    /**
     * 判断表是否存在
     * @param conf 配置 conf
     * @param tableName 表=名
     */
    public static boolean isExistTable(Configuration conf, String tableName) throws IOException {
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();

        boolean result = admin.tableExists(TableName.valueOf(tableName));
        close(admin, connection);
        return result;


    }

    //热点问题

    /**
     * 生成rowkey:regionCode_caller_buildTime_callee_flag_duration
     * @param regionCode    散列的键
     * @param caller    主叫
     * @param buildTime 建立时间
     * @param callee    被叫
     * @param flag      表面主叫还是被叫
     * @param duration  听话持续时间
     * @return  返回rowkey
     */
    public static String genRowkey(String regionCode, String caller, String buildTime, String callee,String flag, String duration){

        StringBuilder sb = new StringBuilder();
        sb.append(regionCode +"_")
                .append(caller +"_")
                .append(buildTime +"_")
                .append(callee +"_")
                .append(flag +"_")
                .append(duration);

        return sb.toString();

    }

    //随便取出数据，做离散

    /**
     * 生成分区号
     * @param caller 主叫
     * @param buildTime 童话建立时间
     * @param regions   region个数
     * @return  返回分区号
     */
    public static String genRegionCode(String caller, String buildTime, int regions){
        int len = caller.length();
        //取出主叫后四位
        String lastPhone = caller.substring(len - 4);
        //取出年和月 buildTime：2018-06-22 13：27：21
        String ym = buildTime
                .replaceAll("-", "")
                .replaceAll(" ", "")
                .replaceAll(":", "")
                .substring(0,6);
        //离散操作1
        Integer x = Integer.valueOf(lastPhone) ^ Integer.valueOf(ym);
        //离散操作2
        int y = x.hashCode();
        //生成分区号
        int regionCode = y % regions;
        DecimalFormat df = new DecimalFormat("00");
        //格式化分区号
        return df.format(regionCode);
    }





}
