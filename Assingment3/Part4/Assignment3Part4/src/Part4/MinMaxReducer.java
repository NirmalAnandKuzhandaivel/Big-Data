/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part4;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author nirmal
 */
public class MinMaxReducer extends Reducer<Text,MinMaxStock,Text,MinMaxStock>{

    private MinMaxStock mo=new MinMaxStock();
    
    @Override
    protected void reduce(Text key, Iterable<MinMaxStock> values, Context context) throws IOException, InterruptedException {
        
        Integer maxVolume = 0;
        Integer minVolume = 0;
        float max_stock_price_adj_close = 0;
        
        Text minDate=new Text();
        Text maxDate=new Text();
        
       
        
        mo.setMaxDate(new Text("abc"));
        mo.setMinDate(new Text("xyz"));
        mo.setAdjClose(null);
        mo.setMaxVolume(null);
        mo.setMinVolume(null);
        
        for(MinMaxStock value:values){
            maxVolume=value.getMaxVolume().get();
            minVolume=value.getMinVolume().get();
          
            minDate=value.getMinDate();
            maxDate=value.getMaxDate();
                 
            if(mo.getMinVolume() == null ||
                    minVolume.compareTo(mo.getMinVolume().get()) < 0){
                mo.setMinVolume(new IntWritable(minVolume));
                mo.setMinDate(minDate);
            }
            
            if(mo.getMaxVolume() == null ||
                    maxVolume.compareTo(mo.getMaxVolume().get()) > 0){
                mo.setMaxVolume(new IntWritable(maxVolume));
                mo.setMaxDate(maxDate);
            }
            
            if(value.getAdjClose().get()>max_stock_price_adj_close){
                max_stock_price_adj_close=value.getAdjClose().get();
                mo.setAdjClose(new FloatWritable(max_stock_price_adj_close));
            }
            
        }
        
        context.write(key,mo);
        
       
     }
       
       // context.write(adjkey, adjCloseF);

   
    
    
    
}
