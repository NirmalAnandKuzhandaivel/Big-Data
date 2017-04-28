/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part4;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import org.apache.hadoop.io.DoubleWritable;
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
public class MCReducer extends Reducer<Text,SortedMapWritable,Text,MedianSDCustomWritable>{
    
    private MedianSDCustomWritable result = new MedianSDCustomWritable();
    private TreeMap<Double, Long> ratingMaps = new TreeMap<Double, Long>();

    @Override
    protected void reduce(Text key, Iterable<SortedMapWritable> values, Context context) throws IOException, InterruptedException {
        
         double sum = 0;
        double totalRatings = 0;
        ratingMaps.clear();
        result.setMedian(0);
        result.setStandardDeviation(0);
        
        for(SortedMapWritable v : values){
            for(Map.Entry<WritableComparable, Writable> entry : v.entrySet()){
                //int rating = ((IntWritable) entry.getKey()).get();
                Double rating = ((DoubleWritable) entry.getKey()).get();
                Long count = ((LongWritable) entry.getValue()).get();
                
                totalRatings += count;
                sum += rating * count;
                
                Long storedCount = ratingMaps.get(rating);
                if(storedCount == null){
                    ratingMaps.put(rating, count);
                }
                else{
                    ratingMaps.put(rating, storedCount+count);
                }
            }
        }
        
        double medianIndex = totalRatings / 2L;
        double prevRating = 0;
        double ratings = 0;
        double prevKey = 0;
        
        for(Map.Entry<Double, Long> entry : ratingMaps.entrySet()){
            ratings = prevRating + entry.getValue();
            
            if(prevRating <= medianIndex && medianIndex < ratings){
                if(totalRatings % 2 == 0 && prevRating == medianIndex){
                    result.setMedian((double) (entry.getKey() + prevKey) / 2.0);
                }else{
                    result.setMedian(entry.getKey());
                }
                break;
            }
            
            prevRating = ratings;
            prevKey = entry.getKey();
        }
        
        double mean = sum / totalRatings;
        double sumOfSquares = 0;
        
        for(Map.Entry<Double, Long> entry : ratingMaps.entrySet()){
            sumOfSquares += (entry.getKey() - mean) * (entry.getKey() - mean) * entry.getValue();
        }
        
        result.setStandardDeviation((double)Math.sqrt(sumOfSquares / (totalRatings - 1)));
        
        context.write(key, result);
        
        
    }
    
    
    
}
