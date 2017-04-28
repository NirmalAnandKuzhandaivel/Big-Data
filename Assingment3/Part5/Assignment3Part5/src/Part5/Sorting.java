/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part5;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author nirmal
 */
public class Sorting {
    
    public static class Mapper1 extends Mapper<Object,Text,Text,FloatWritable>{

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String tokens[]=value.toString().split(",");
            if(!"movieId".equalsIgnoreCase(tokens[1])){
                try{
                    context.write(new Text(tokens[1]),new FloatWritable(Float.parseFloat(tokens[2])));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
            }
        }
        
        
        
    }
    
    public static class Reducer1 extends Reducer<Text,FloatWritable,Text,FloatWritable>{

        @Override
        protected void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
            float sum=0;
            int count=0;
            for(FloatWritable value:values){
                sum=sum+=value.get();
                count+=1;
            }
            float avg=sum/count;
            context.write(key,new FloatWritable(avg));
        }
        
    }
    
    public static class Mapper2 extends Mapper<Object,Text,FloatWritable,Text>{

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String row[] = value.toString().split("\\t");
            context.write(new FloatWritable(Float.parseFloat(row[1])),new Text(row[0]));
        }
        
    }
    
    public static class Reducer2 extends Reducer<FloatWritable,Text,Text,FloatWritable>{

        int i=0;
        @Override
        protected void reduce(FloatWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for(Text value:values){
                if(i<25){
                    context.write(value,key);
                }
                i++;
            }
        }
        
        
    }
    
    public static void main(String[] args) throws IOException,InterruptedException,ClassNotFoundException{
        
        Configuration conf=new Configuration();
         Job job=Job.getInstance(conf, "Merge");
         job.setJarByClass(Sorting.class);
         job.setMapperClass(Mapper1.class);
         job.setMapOutputKeyClass(Text.class);
         job.setMapOutputValueClass(FloatWritable.class);
         job.setReducerClass(Reducer1.class);
        
         //job.setCombinerClass(Reducer1.class);
         job.setOutputKeyClass(Text.class);
         job.setOutputValueClass(FloatWritable.class);
         
         FileInputFormat.addInputPath(job, new Path(args[0]));
         FileOutputFormat.setOutputPath(job,new Path(args[1]));
         boolean complete=job.waitForCompletion(true);
        
        
        Configuration conf2=new Configuration();
        Job job2=Job.getInstance(conf2, "Job Chaining");
        
        if(complete){
        job2.setJarByClass(Sorting.class);
        job2.setMapperClass(Mapper2.class);
        job2.setMapOutputKeyClass(FloatWritable.class);
        job2.setMapOutputValueClass(Text.class);
        
        job2.setSortComparatorClass(RatingComparator.class);
        job2.setReducerClass(Reducer2.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(FloatWritable.class);
        
        FileInputFormat.addInputPath(job2, new Path (args[1]));
        FileOutputFormat.setOutputPath(job2, new Path(args[2]));
        System.exit(job2.waitForCompletion(true)?0:1);
        }
    }
    
}
