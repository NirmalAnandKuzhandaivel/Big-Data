/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part3;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class PartitionMapper extends Mapper<Object,Text,IntWritable,Text>{

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        try {
            String tokens[]=value.toString().split(" ");
            String dataToken=tokens[3].substring(1, 12);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
            Date date=formatter.parse(dataToken);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH)+1;
            context.write(new IntWritable(month),value);
        } catch (ParseException ex) {
            Logger.getLogger(PartitionMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    
}
