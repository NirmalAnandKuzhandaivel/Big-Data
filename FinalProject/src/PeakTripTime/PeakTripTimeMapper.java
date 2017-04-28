/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PeakTripTime;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class PeakTripTimeMapper extends Mapper<Object,Text,Text,PTTWritable>{

    private PTTWritable pttTuple=new PTTWritable();
    
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        
        String tokens[]=value.toString().split(",");
        if(tokens.length==21){
        if(!(tokens[0].equals("")) && !(tokens[0].equalsIgnoreCase("VendorID"))){
        
        String time=tokens[1].split(" ")[1].split(":")[0];
        pttTuple.setTime(time);
        pttTuple.setCount(1);
        context.write(new Text(tokens[1].split(" ")[0]), pttTuple);
        }
        }
        
    }
    
    
    
}
