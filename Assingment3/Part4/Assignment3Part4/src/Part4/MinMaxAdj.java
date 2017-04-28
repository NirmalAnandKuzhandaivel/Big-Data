/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part4;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
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
public class MinMaxAdj {
    public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException{
        Configuration conf = new Configuration();
            Job job = Job.getInstance(conf, "MinMaxAdj");
            job.setJarByClass(MinMaxAdj.class);
           
            job.setMapperClass(MinMaxMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(MinMaxStock.class);
            
           
            job.setReducerClass(MinMaxReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(MinMaxStock.class);
            
            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            
            System.exit(job.waitForCompletion(true) ? 0 : 1);
    }    
}
