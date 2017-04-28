/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MostDistanceTravelled;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author nirmal
 */
public class MDTReducer extends Reducer<Text,FloatWritable,Text,FloatWritable>{

    
    
    @Override
    protected void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
        
        float max=0;
        for(FloatWritable val:values){
            if(val.get()>max){
                max=val.get();
            }
        }
        context.write(key, new FloatWritable(max));
    }
    
    
    
}
