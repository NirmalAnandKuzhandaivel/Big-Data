/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistanceFareSorting;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author nirmal
 */
public class DistanceFareReducer extends Reducer<DistanceFare,DistanceFareRecord,NullWritable,DistanceFareRecord>{

    @Override
    protected void reduce(DistanceFare key, Iterable<DistanceFareRecord> values, Context context) throws IOException, InterruptedException {
        
        for (DistanceFareRecord dfr:values){
            context.write(NullWritable.get(),dfr);
        }
        
    }
    
    
    
}
