/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLFilter;

import PeakTripTime.PTTWritable;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class TipHourlyMapper extends Mapper<Object,Text,Text,AverageTuple>{
    
    private AverageTuple avg=new AverageTuple();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        
        String tokens[]=value.toString().split(",");
        String time=tokens[1].split(" ")[1].split(":")[0];
        
        avg.setTip(Double.parseDouble(tokens[14]));
        avg.setCount(1);
        
        context.write(new Text(time),avg);
        
        
    }
    
    
    
}
