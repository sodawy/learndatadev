package soda.homework.runs;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class TrafficReducer extends Reducer<Text, TrafficBean, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<TrafficBean> values, Context context) throws IOException, InterruptedException {
        long totalUp = 0;
        long totalDown = 0;
        long totalSum = 0;
        Iterator<TrafficBean> iterator = values.iterator();
        while (iterator.hasNext()) {
            TrafficBean next = iterator.next();
            totalUp += next.getUp();
            totalDown += next.getDown();
            totalSum += next.getTotal();
        }
        TrafficBean trafficBean = new TrafficBean(totalUp, totalDown, totalSum);
        context.write(key, new Text(trafficBean.toString()));
    }
}
