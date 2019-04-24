package hbase;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;
import utils.HbaseUtil;
import utils.PropertiesUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @description:
 * @time:2019/4/24
 */
public class CalleeWriteObserver extends BaseRegionObserver {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");





    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        super.postPut(e, put, edit, durability);
        //获取想要操作的那张目标表
        String targetTableName = PropertiesUtil.getProperty("hbase.calllog.tablename");
        //获取当前put数据的表
        String currentTablename = e.getEnvironment().getRegionInfo().getTable().getNameAsString();

        if (!targetTableName.equals(currentTablename)){
            return;
        }
        //05_19902496992_2018-05-20 05: column=f1:flag, timestamp=1556068315959, value=1
        // 48:45_15422018558_1_0870
        String oriRowKey = Bytes.toString(put.getRow());

        String[] splitOriRowKey = oriRowKey.split("_");
        String caller = splitOriRowKey[1];
        String callee = splitOriRowKey[3];
        String buildTime = splitOriRowKey[2];

        //如果当前插入的是被叫数据，则直接返回（因为默认提供的数据全部为主叫数据）
        String flag = splitOriRowKey[4];
        String calleeflag = "0";
        if (flag.equals(calleeflag)){
            return;
        }
        flag = calleeflag;

        String duration = splitOriRowKey[5];

        Integer regions = Integer.valueOf(PropertiesUtil.getProperty("hbase.calllog.regions"));

        String regionCode = HbaseUtil.genRegionCode(caller, buildTime, regions);

        String calleeRowkey = HbaseUtil.genRowkey(regionCode, callee, buildTime, caller, flag, duration);
        String buildTimeTS = "";
        try {
            buildTimeTS= String.valueOf(sdf.parse(buildTime).getTime());
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        Put calleeput = new Put(Bytes.toBytes(calleeRowkey));
        calleeput.addColumn(Bytes.toBytes("f2"),Bytes.toBytes("callee"),Bytes.toBytes(callee));
        calleeput.addColumn(Bytes.toBytes("f2"),Bytes.toBytes("caller"),Bytes.toBytes(caller));
        calleeput.addColumn(Bytes.toBytes("f2"),Bytes.toBytes("buildTimeReplace"),Bytes.toBytes(buildTime));
        calleeput.addColumn(Bytes.toBytes("f2"),Bytes.toBytes("buildTimeTS"),Bytes.toBytes(buildTimeTS));
        calleeput.addColumn(Bytes.toBytes("f2"),Bytes.toBytes("flag"),Bytes.toBytes(flag));
        calleeput.addColumn(Bytes.toBytes("f2"),Bytes.toBytes("duration"),Bytes.toBytes(duration));

        //获得表，并将数据插入表
        Table table =  e.getEnvironment().getTable(TableName.valueOf(targetTableName));
        table.put(calleeput);
        table.close();


    }
}
