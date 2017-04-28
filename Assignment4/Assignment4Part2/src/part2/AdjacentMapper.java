/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part2;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class AdjacentMapper extends Mapper<Object,Text,Text,AverageTuple>{
    
    private AverageTuple avgTuple=new AverageTuple();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String tokens[] = value.toString().split(",");
        if (!("date".equals(tokens[2]))) {
            
            
                
                //SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
               // Date date = format.parse(tokens[2]);
               // Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
               // calendar.setTime(date);
              // int i=tokens[2].indexOf("/", 3);
               // String year=tokens[2].substring(i+1);
//String.valueOf(calendar.get(Calendar.YEAR));
                avgTuple.setCount(new IntWritable(1));
                avgTuple.setAvg(new FloatWritable(Float.parseFloat(tokens[8])));
                context.write(new Text(tokens[2].substring(0, 4)), avgTuple);
            } 
            
        
    }
    
    
    
}
