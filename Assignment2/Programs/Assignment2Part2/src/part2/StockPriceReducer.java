/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part2;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author nimi
 */
public class StockPriceReducer extends Reducer<Text, FloatWritable, Text, FloatWritable>{
  private FloatWritable result = new FloatWritable();
    
    public void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException{
        int count=0;
        float sum = 0;
        for(FloatWritable val : values){
            sum += val.get();
            count++;
        }
        float average=sum/count;
        result.set(average);
        System.out.println(result);
        context.write(key,result);
    }
    
}
