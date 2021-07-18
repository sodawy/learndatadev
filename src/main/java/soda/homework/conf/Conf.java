package soda.homework.conf;

import org.apache.hadoop.conf.Configuration;


public class Conf {

    //hdfs的链接地址
    //public final static String hdfsUrl = "hdfs://a2c2a8680ae0:9000";
    public final static String hdfsUrl = "hdfs://jikehadoop01:8020/";
    //hdfs的名字
    public final static String HDFS_NAME = "fs.defaultFS";

    public static Configuration get() {
        Configuration conf = new Configuration();
        conf.set(HDFS_NAME, hdfsUrl);
        conf.set("mapreduce.app-submission.cross-platform", "true");
        return conf;

    }
}
