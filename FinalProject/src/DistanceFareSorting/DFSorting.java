/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistanceFareSorting;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author nirmal
 */
public class DFSorting {
    
    public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException{
        
        
        Configuration conf = new Configuration();
            Job job = Job.getInstance(conf,"SecondarySort");
            job.setJarByClass(DFSorting.class);
           
            job.setMapperClass(DistanceFareMapper.class);
            job.setMapOutputKeyClass(DistanceFare.class);
            job.setMapOutputValueClass(DistanceFareRecord.class);
            
            job.setSortComparatorClass(KeyComparator.class);
            job.setPartitionerClass(DistancePartitioner.class);
            job.setGroupingComparatorClass(GroupComparator.class);
            
            job.setReducerClass(DistanceFareReducer.class);
            job.setOutputKeyClass(NullWritable.class);
            job.setOutputValueClass(DistanceFareRecord.class);
            
            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            
           
            
            System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
    
}
