/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part4;

import java.io.IOException;
import java.util.Map;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author nirmal
 */
public class MCCombiner extends Reducer<Text,SortedMapWritable,Text,SortedMapWritable>{

    @Override
    protected void reduce(Text key, Iterable<SortedMapWritable> values, Context context) throws IOException, InterruptedException {
       SortedMapWritable outValue = new SortedMapWritable();
        
        for(SortedMapWritable v : values){
            for(Map.Entry<WritableComparable, Writable> entry : v.entrySet()){
                LongWritable count = (LongWritable) outValue.get(entry.getKey());
                
                if(count != null){
                    count.set(count.get() + ((LongWritable) entry.getValue()).get());
                    
                }
                else{
                    outValue.put(entry.getKey(), new LongWritable( ((LongWritable) entry.getValue()).get()));
                }
            }
            v.clear();
        }
        context.write(key, outValue);
    }
    
    
    
}
