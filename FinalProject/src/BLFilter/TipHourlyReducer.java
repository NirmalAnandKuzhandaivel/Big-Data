/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLFilter;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author nirmal
 */
public class TipHourlyReducer extends Reducer<Text,AverageTuple,Text,DoubleWritable>{
    
     private AverageTuple avgTuple=new AverageTuple();

    @Override
    protected void reduce(Text key, Iterable<AverageTuple> values, Context context) throws IOException, InterruptedException {
        
        double sum=0;
        int count=0;
        
        for(AverageTuple tuple:values){
            sum+=tuple.getCount()*tuple.getTip();
            count+=tuple.getCount();
            
        }
        
        double average=sum/count;
        
        
        context.write(key,new DoubleWritable(average));
        
        
    }
    
    
    
}
