/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarrerGroupCount;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class ACMapper extends Mapper<Object,Text,Text,IntWritable>{

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String tokens[]=value.toString().split(",");
        context.write(new Text(tokens[2].split(":")[0]), new IntWritable(1));
        
    }
    
}
    

