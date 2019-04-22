package producer;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xh
 */
public class ProducerLog {
    /**
     *  设置起始时间和结束时间 */
    private  String startTime = "2018-01-01";
    private  String endTime = "2018-12-31";

    /**用于存放电话号码 和电话号码+姓名*/
    private List<String> phoneList = new ArrayList<>();
    private Map<String,String> phoneNameMap = new HashMap<>();

    public void initPhone(){
        phoneList.add("17078388295");
        phoneList.add("13980337439");
        phoneList.add("14575535933");
        phoneList.add("19902496992");
        phoneList.add("18549641558");
        phoneList.add("17005930322");
        phoneList.add("18468618874");
        phoneList.add("18576581848");
        phoneList.add("15978226424");
        phoneList.add("15542823911");
        phoneList.add("17526304161");
        phoneList.add("15422018558");
        phoneList.add("17269452013");
        phoneList.add("17764278604");
        phoneList.add("15711910344");
        phoneList.add("15714728273");
        phoneList.add("16061028454");
        phoneList.add("16264433631");
        phoneList.add("17601615878");
        phoneList.add("15897468949");

        phoneNameMap.put("17078388295", "李雁");
        phoneNameMap.put("13980337439", "卫艺");
        phoneNameMap.put("14575535933", "仰莉");
        phoneNameMap.put("19902496992", "陶欣悦");
        phoneNameMap.put("18549641558", "施梅梅");
        phoneNameMap.put("17005930322", "金虹霖");
        phoneNameMap.put("18468618874", "魏明艳");
        phoneNameMap.put("18576581848", "华贞");
        phoneNameMap.put("15978226424", "华啟倩");
        phoneNameMap.put("15542823911", "仲采绿");
        phoneNameMap.put("17526304161", "卫丹");
        phoneNameMap.put("15422018558", "戚丽红");
        phoneNameMap.put("17269452013", "何翠柔");
        phoneNameMap.put("17764278604", "钱溶艳");
        phoneNameMap.put("15711910344", "钱琳");
        phoneNameMap.put("15714728273", "缪静欣");
        phoneNameMap.put("16061028454", "焦秋菊");
        phoneNameMap.put("16264433631", "吕访琴");
        phoneNameMap.put("17601615878", "沈丹");
        phoneNameMap.put("15897468949", "褚美丽");

    }

    /**
    数据形式：主叫-被叫-通话建立时间-通话时长
    数据形式对应字段名：caller,callee,buildTime,duration
     */

    public String product(){
        /**主叫、被叫电话号*/
        String caller = null;
        String callee = null;

        //主叫、被叫姓名
        String callerName = null;
        String calleeName = null;

        /* 取得主叫好吗 */
        int callerIndex = (int) (Math.random() * phoneList.size());
        caller = phoneList.get(callerIndex);
        callerName = phoneNameMap.get(caller);


        /*
        取得被叫号码
        */
        int calleeIndex = (int) (Math.random() * phoneList.size());
        callee = phoneList.get(callerIndex);
        calleeName = phoneNameMap.get(callee);

        while (true){
            /**
             *random()左闭右开
             * 取得被叫号码*/
            calleeIndex = (int) (Math.random() * phoneList.size());
            callee = phoneList.get(calleeIndex);
            calleeName = phoneNameMap.get(callee);
            if (!caller.equals(callee)){
                break;
            }
        }

        //第三个参数：随机通话建立时间
        String buildTime = randomBuildTime(startTime,endTime);

        //第四个参数0000
        DecimalFormat df = new DecimalFormat("0000");
        String duration = df.format((int) (30 * 60 * Math.random()));

        StringBuilder sb = new StringBuilder();
        sb.append(caller + ",").append(callee + ",").append(buildTime + ",").append(duration);

        return sb.toString();
    }

    //时间形式：2018-01-18 22：38：53
    private  String randomBuildTime(String startTime, String endTime){

        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf1.parse(startTime);
            Date endDate = sdf1.parse(endTime);

            if (endDate.getTime() <= startDate.getTime()){
                return null;
            }

            //随机通话建立时间得Long型
            long randomTs = startDate.getTime() + (long) ((endDate.getTime() - startDate.getTime()) * Math.random());
            Date resultDate = new Date(randomTs);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            String resultTimeString = sdf2.format(resultDate);
            return resultTimeString;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    //写数据到文件中
    public void writeLog(String filePath){
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath, true));
            while (true){
                Thread.sleep(500);
                String log = product();
                System.out.println(log);
                osw.write(log+"\n");
                osw.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(java.lang.String[] args) {
        //输出路径
        //args = new String[]{"D:\\大数据\\第09期大数据（hunter）\\正式课程\\课程课件+笔记\\电信项目实战 - andy\\0126-电信项目（一）===\\hou_calllog.txt"};


        if (args == null || args.length<=0){
            System.out.println("没这个路径");
            return;
        }

        ProducerLog producerLog = new ProducerLog();
        producerLog.initPhone();
        producerLog.writeLog(args[0]);


    }








}
