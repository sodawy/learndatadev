package soda.homework.runs;

import com.sun.corba.se.spi.ior.Writeable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import soda.homework.conf.Conf;

import java.io.IOException;

public class RunTraffic {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = Conf.get();

        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        Job job = Job.getInstance(Conf.get(), "Soda: StatisticsTraffic");

        job.setJarByClass(RunTraffic.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setReducerClass(TrafficReducer.class);

        if (otherArgs.length < 2) {
            System.err.println("Usage: StatisticsTraffic.jar <in> [<in>...] <out>");
            System.exit(2);
        }

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TrafficBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        for (int i = 0; i < otherArgs.length - 1; ++i) {
            FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
        }
        FileOutputFormat.setOutputPath(job,
                new Path(otherArgs[otherArgs.length - 1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
