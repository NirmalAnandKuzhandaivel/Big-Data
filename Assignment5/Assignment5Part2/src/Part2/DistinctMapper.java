/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part2;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class DistinctMapper extends Mapper<Object,Text,Text,NullWritable>{

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String tokens[] = value.toString().split("-");
        context.write(new Text(tokens[0]),NullWritable.get());
       }
    
    
    
}
