/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part2;

import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nimi
 */
public class StockPriceMapper extends Mapper<Object,Text,Text,FloatWritable>{
    private Text stock=new Text();
    private FloatWritable price=new FloatWritable();

    
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException,
            NumberFormatException {
      try{
       String line=value.toString();
       String[] tokens=line.split(",");
       System.out.println(tokens);
       if(!("exchange".equals(tokens[0]))){
       stock.set(tokens[1]);
       price.set(Float.parseFloat(tokens[4]));
       context.write(stock, price);
      }
    }
    catch(NumberFormatException e){
    e.printStackTrace();
}
    }
}
    
    
    

