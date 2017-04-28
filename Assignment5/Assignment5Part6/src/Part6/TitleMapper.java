/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part6;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class TitleMapper extends Mapper<Object,Text,Text,Text>{

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String tokens[]=value.toString().split(",");
        if(!"movieId".equalsIgnoreCase(tokens[0])){
        context.write(new Text(tokens[0]),new Text("A" + tokens[1]));
        }
        
    }
    
    
    
}
