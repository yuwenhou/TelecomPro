# TelecomPro
大数据开发实战项目1-电信项目

### 1.ct_Producer生产数据
producer.ProducerLog
主叫 -- 被叫 -- 通话建立时间 -- 通话持续时间
### 2.ct_consumer消费数据

#### 2.1 utils
utils.ConnectionInstance 连接初始化
utils.HbaseUtil 初始化hbase表格
utils.PropertiesUtil 获取配置文件的参数初始化

#### 2.2 kafka
kafka.HBaseConsumer kafka获得从数据传入hbase

#### 2.3 hbase
hbase.HBaseDAO 收到数据写入数据库
