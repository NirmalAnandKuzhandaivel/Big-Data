/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
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
public class Movie_Std_Dev {
    
    public static class MovieMapper extends Mapper<Object,Text,Text,IntWritable>{

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String tokens[]=value.toString().split("::");
            context.write(new Text(tokens[1]),new IntWritable(Integer.parseInt(tokens[2])));
            
        }
        
        
        
    }
    
    public static class MovieReducer extends Reducer<Text,IntWritable,Text,MedianSDCustomWritable>{
        
        private MedianSDCustomWritable result=new MedianSDCustomWritable();
        private ArrayList<Integer> list=new ArrayList<Integer>();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum=0;
            int count=0;
            
            list.clear();
            result.setStandardDeviation(0);
            
            for(IntWritable val:values){
                list.add(val.get());
                sum+=val.get();
                count+=1;
            }
            
            Collections.sort(list);
            if(count%2==0)
            {
                result.setMedian((list.get((int)count/2) +list.get((int)count/2 - 1))/2);
            }   
            else
            {
                    result.setMedian(list.get((int) count/2));
            }
            
            double mean=sum/count;
            double sumOfSquares=0;
            for(double val:list){
                    sumOfSquares+=(val - mean)*(val-mean);
            }
            
            result.setStandardDeviation((double) Math.sqrt(sumOfSquares/(count-1)));
            context.write(key,result);
            
            
            
        }
        
    }
        
      public static void main(String args[])throws IOException, InterruptedException, ClassNotFoundException{
       Configuration conf = new Configuration();
       Job job1 = Job.getInstance(conf, "std_dev");

       job1.setJarByClass(Movie_Std_Dev.class);
       job1.setMapperClass(MovieMapper.class);
       job1.setMapOutputKeyClass(Text.class);
       job1.setMapOutputValueClass(IntWritable.class);

       job1.setReducerClass(MovieReducer.class);
       job1.setOutputKeyClass(Text.class);
       job1.setOutputValueClass(MedianSDCustomWritable.class);

       FileInputFormat.addInputPath(job1, new Path(args[0]));
       FileOutputFormat.setOutputPath(job1, new Path(args[1]));
       
       System.exit(job1.waitForCompletion(true)?0:1);
    }
        
        
        
    
    
    
    
}
