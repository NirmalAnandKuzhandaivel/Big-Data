/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PeakTripTime;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author nirmal
 */
public class PeakTripTime {
    
    public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException{
        Configuration conf = new Configuration();
            Job job = Job.getInstance(conf, "PeakTripTime");
            job.setJarByClass(PeakTripTime.class);
           
            job.setMapperClass(PeakTripTimeMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(PTTWritable.class);
            
           job.setCombinerClass(PeakTripTimeReducer.class);
            job.setReducerClass(PeakTripTimeReducer.class);
            job.setOutputKeyClass(Text.class);
           job.setOutputValueClass(PTTWritable.class);
            //job.setNumReduceTasks(0);
            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            
            System.exit(job.waitForCompletion(true) ? 0 : 1);
    } 
    
}
