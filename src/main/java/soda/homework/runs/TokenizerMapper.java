package soda.homework.runs;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TokenizerMapper extends Mapper<Object, Text, Text, TrafficBean> {

    private Text phoneNo = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        TrafficBean trafficBean = new TrafficBean(value.toString());
        phoneNo.set(trafficBean.getPhoneNo());
        context.write(phoneNo, trafficBean);
    }
}
