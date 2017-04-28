/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part4;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class MCMapper extends Mapper<Object,Text,Text,SortedMapWritable>{
    
    private static final LongWritable one = new LongWritable(1);

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
       String tokens[]=value.toString().split("::");
       SortedMapWritable output=new SortedMapWritable();
       output.put(new DoubleWritable(Double.parseDouble(tokens[2])), one);
       context.write(new Text(tokens[1]),output);
      }
    
    
    
}
