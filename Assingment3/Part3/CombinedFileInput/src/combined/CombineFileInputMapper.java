/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  org.apache.hadoop.io.LongWritable
 *  org.apache.hadoop.io.Text
 *  org.apache.hadoop.mapreduce.Mapper
 *  org.apache.hadoop.mapreduce.Mapper$Context
 */
package combined;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CombineFileInputMapper
extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String a = value.toString();
        String[] b = a.split(",");
        if (!b[0].equals("Name")) {
             context.write(new Text(b[0]), new Text(b[2]));
        }
    }
    
    
    
}

