/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part2;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author nirmal
 */
public class AdjacentReducer extends Reducer<Text,AverageTuple,Text,AverageTuple>{
    
    private AverageTuple avgTuple=new AverageTuple();
    

    @Override
    protected void reduce(Text key, Iterable<AverageTuple> values, Context context) throws IOException, InterruptedException {
        float sum=0;
        int count=0;
        
        for(AverageTuple tuple:values){
            sum+=tuple.getCount().get() * tuple.getAvg().get();
            count+=tuple.getCount().get();
            
        }
        
        float average=sum/count;
        
        avgTuple.setAvg(new FloatWritable(average));
        avgTuple.setCount(new IntWritable(count));
        context.write(key,avgTuple);
        
            
    }

    
    
    
}
