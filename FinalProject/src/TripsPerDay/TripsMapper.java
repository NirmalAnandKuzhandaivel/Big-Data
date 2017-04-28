/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TripsPerDay;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class TripsMapper extends Mapper<Object,Text,Text,IntWritable>{

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        
        String tokens[]=value.toString().split(",");
        if(!(tokens[0].equals("")) && !(tokens[0].equalsIgnoreCase("VendorID"))){
            context.write(new Text(tokens[1].split(" ")[0]), new IntWritable(1));
        }
        
    }
    
    
    
}
