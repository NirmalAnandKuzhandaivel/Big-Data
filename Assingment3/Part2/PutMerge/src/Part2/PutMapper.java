/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part2;

import java.io.IOException;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;

/**
 *
 * @author nirmal
 */
public class PutMapper extends Mapper<Object,Text,Text,FloatWritable>{
    
    private Text stock=new Text();
    private FloatWritable price=new FloatWritable();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        try{
            String line=value.toString();
            String[] tokens=line.split(",");
            if(!("stock_symbol".equals(tokens[1]))){
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
