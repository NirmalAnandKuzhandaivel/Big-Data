/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLFilter;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author nirmal
 */
public class BLFilter {
    
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        // TODO code application logic here
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf,"Bloom Filter");
        job.setJarByClass(BLFilter.class);
        job.setMapperClass(BloomFilterMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setNumReduceTasks(0);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean complete = job.waitForCompletion(true);

       Configuration conf2 = new Configuration();
       Job job2 = Job.getInstance(conf2, "chaining");
       if (complete) {
           job2.setJarByClass(BLFilter.class);
           job2.setMapperClass(TipHourlyMapper.class);
           job2.setMapOutputKeyClass(Text.class);
           job2.setMapOutputValueClass(AverageTuple.class);

           job2.setReducerClass(TipHourlyReducer.class);
           job2.setOutputKeyClass(Text.class);
           job2.setOutputValueClass(DoubleWritable.class);

           FileInputFormat.addInputPath(job2, new Path(args[1]));
           FileOutputFormat.setOutputPath(job2, new Path(args[2]));

           System.exit(job2.waitForCompletion(true) ? 0 : 1);
       }
    }
    
}
