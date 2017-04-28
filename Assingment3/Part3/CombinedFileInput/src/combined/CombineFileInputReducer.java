/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  org.apache.hadoop.io.Text
 *  org.apache.hadoop.mapreduce.Reducer
 *  org.apache.hadoop.mapreduce.Reducer$Context
 */
package combined;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CombineFileInputReducer
extends Reducer<Text, Text, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        
        for(Text value:values){
            context.write(key,value);
        }
    }
    
    
    
}

