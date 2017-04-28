/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MostDistanceTravelled;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class MDTMapper extends Mapper<Object,Text,Text,FloatWritable>
{
    FloatWritable distance;

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String tokens[]=value.toString().split(",");
        if(!("VendorID".equalsIgnoreCase(tokens[0]))){
        distance=new FloatWritable(Float.parseFloat(tokens[10]));
        
        context.write(new Text(tokens[21]),distance);
        }
    }
    
}
