/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part5;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 *
 * @author nirmal
 */
public class InnerJoin {
    
    public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException{
        Configuration conf=new Configuration();
        Job job=Job.getInstance(conf, "InnerJoin");
        job.setJarByClass(InnerJoin.class);
        
        MultipleInputs.addInputPath(job, new Path(args[0]),TextInputFormat.class, Mapper1.class);
        MultipleInputs.addInputPath(job, new Path(args[1]),TextInputFormat.class, Mapper2.class);
       
        job.setReducerClass(Reducer1.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path(args[2]));
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        boolean complete = job.waitForCompletion(true);

       Configuration conf2 = new Configuration();
       Job job2 = Job.getInstance(conf2, "chaining");
       if (complete) {
           job2.setJarByClass(InnerJoin.class);
            MultipleInputs.addInputPath(job2, new Path(args[2]),TextInputFormat.class, Mapper3.class);
            MultipleInputs.addInputPath(job2, new Path(args[3]),TextInputFormat.class, Mapper4.class);
           
            job2.setReducerClass(Reducer2.class);
            job2.setOutputFormatClass(TextOutputFormat.class);
            TextOutputFormat.setOutputPath(job2, new Path(args[4]));
            job2.setOutputKeyClass(Text.class);
            job2.setOutputValueClass(Text.class);

           System.exit(job2.waitForCompletion(true) ? 0 : 1);
       }
       
    }
    
}
