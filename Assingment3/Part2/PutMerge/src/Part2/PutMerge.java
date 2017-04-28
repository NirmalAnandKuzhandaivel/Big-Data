package Part2;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nirmal
 */
public class PutMerge {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException,InterruptedException,ClassNotFoundException{
        
        Configuration conf=new Configuration();
         Job job=Job.getInstance(conf, "Merge");
         job.setJarByClass(PutMerge.class);
         job.setMapperClass(PutMapper.class);
         job.setReducerClass(PutReducer.class);
         job.setCombinerClass(PutReducer.class);
         job.setOutputKeyClass(Text.class);
         job.setOutputValueClass(FloatWritable.class);
         
         FileInputFormat.addInputPath(job, new Path(args[0]));
         FileOutputFormat.setOutputPath(job,new Path(args[1]));
         System.exit(job.waitForCompletion(true) ? 0 : 1);
        // TODO code application logic here
    }
    
}
