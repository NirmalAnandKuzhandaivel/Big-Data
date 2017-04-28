/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class TestMapper extends Mapper<Object,Text,Text,IntWritable>{

    @Override
    protected void map(Object key, Text value, Mapper.Context context) throws IOException, ArrayIndexOutOfBoundsException,InterruptedException {
        String tokens[]=value.toString().split(",");
        if(tokens[6].trim().length()>0 && !(tokens[6].equalsIgnoreCase(""))){
        context.write(new Text(tokens[6]), new IntWritable(1));
        }
        
    }
    
}
