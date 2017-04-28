/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompareTripsPerDay;

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
public class TripsJoin {
    
    public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException{
        Configuration conf=new Configuration();
        Job job=Job.getInstance(conf, "InnerJoin");
        job.setJarByClass(TripsJoin.class);
        
        MultipleInputs.addInputPath(job, new Path(args[0]),TextInputFormat.class, GreenMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]),TextInputFormat.class, YellowMapper.class);
       
        job.setReducerClass(YGReducer.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path(args[2]));
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
    
}
