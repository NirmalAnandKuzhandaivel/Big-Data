/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kvtif;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 *
 * @author nirmal
 */
public class KeyMain {
    
    public static void main(String[] args) throws IOException,InterruptedException,ClassNotFoundException{
        
        Configuration conf=new Configuration();
        conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", ",");
         Job job=Job.getInstance(conf, "Merge");
         job.setJarByClass(KeyMain.class);
         job.setInputFormatClass(KeyValueTextInputFormat.class);
         job.setOutputFormatClass(TextOutputFormat.class);
         job.setMapperClass(KeyMapper.class);
         job.setReducerClass(KeyReducer.class);
         job.setCombinerClass(KeyReducer.class);
         job.setMapOutputKeyClass(Text.class);
         job.setMapOutputValueClass(IntWritable.class);
        
         job.setOutputKeyClass(Text.class);
         job.setOutputValueClass(IntWritable.class);
         
         FileInputFormat.addInputPath(job, new Path(args[0]));
         FileOutputFormat.setOutputPath(job,new Path(args[1]));
         System.exit(job.waitForCompletion(true) ? 0 : 1);
        // TODO code application logic here
    }
    
}
