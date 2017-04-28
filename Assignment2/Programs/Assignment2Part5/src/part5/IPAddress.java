/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part5;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author nimi
 */
public class IPAddress {
    
    public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException{
    
    Configuration conf = new Configuration();
        Job job = Job.getInstance(conf,"IP Access");
        job.setJarByClass(IPAddress.class);
        job.setMapperClass(IPAddressMapper.class);
        job.setCombinerClass(IPAddressReducer.class);
        job.setReducerClass(IPAddressReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
