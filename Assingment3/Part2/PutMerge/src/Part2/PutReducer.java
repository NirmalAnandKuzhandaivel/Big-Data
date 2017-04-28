/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part2;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author nirmal
 */
public class PutReducer extends Reducer<Text,FloatWritable,Text,FloatWritable>{

    private FloatWritable result=new FloatWritable();

    @Override
    protected void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
        int count=0;
        float sum=0;
        for(FloatWritable val:values){
            sum+=val.get();
            count++;
        }
        float average=sum/count;
        result.set(average);
        context.write(key, result);
    }
    
    
    
    
}
