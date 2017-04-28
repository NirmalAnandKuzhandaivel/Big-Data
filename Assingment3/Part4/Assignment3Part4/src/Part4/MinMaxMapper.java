/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part4;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class MinMaxMapper extends Mapper<Object,Text,Text,MinMaxStock>{
    private Text date=new Text();
    private IntWritable volume=new IntWritable();
    private FloatWritable adj=new FloatWritable();
    
    private MinMaxStock stock=new MinMaxStock();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String stockFields[] = value.toString().split(",");
        System.out.println("Nirmal");
        System.out.print(stockFields[7]);
        if (!("stock_symbol".equals(stockFields[1]))) {
            date.set(stockFields[2]);
            volume.set(Integer.parseInt(stockFields[7]));
            adj.set(Float.parseFloat(stockFields[8]));
        }

        try {
            stock.setMaxVolume(volume);
            stock.setMinVolume(volume);
            stock.setMaxDate(date);
            stock.setMinDate(date);
            stock.setAdjClose(adj);
            context.write(new Text(stockFields[1]), stock);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    
    
}
