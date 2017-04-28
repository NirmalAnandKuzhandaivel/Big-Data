/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part3;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nimi
 */
public class UserMapper extends Mapper<Object,Text,Text,IntWritable>{
  private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    
    public void map(Object key, Text value, Mapper.Context context) throws IOException, InterruptedException{
           String line=value.toString();
           String[] tokens=line.split("::");
            word.set(tokens[1]);
            System.out.println(word);
            context.write(word, one);
        }
    
    
}
