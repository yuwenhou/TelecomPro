# TelecomPro
大数据开发实战项目1-电信项目

### 1.ct_Producer生产数据
producer.ProducerLog
主叫 -- 被叫 -- 通话建立时间 -- 通话持续时间
格式化，时间 流

### 2.ct_consumer消费数据
#### 2.1 utils
utils.ConnectionInstance 连接初始化
utils.HbaseUtil 初始化hbase表格
utils.PropertiesUtil 获取配置文件的参数初始化

#### 2.2 kafka
kafka.HBaseConsumer kafka获得从数据传入hbase

#### 2.3 hbase
hbase.HBaseDAO 收到数据写入数据库
hbase.CalleeWriteObserver 协处理器，在收到数据的同时在f2加入被叫数据

### 3.ct_analysis将存在Hbase(hdfs)的数据导入到mysql
两种方法：3.1sqoop
        3.2Yarn（本项目选用）

3.1 kv 映射关系比较及包结构
3.1.1 base
3.1.2 key
3.1.3 value


