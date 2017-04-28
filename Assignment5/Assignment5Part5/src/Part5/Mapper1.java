/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part5;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class Mapper1 extends Mapper<Object,Text,Text,Text>{

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String tokens[]=value.toString().split(";");
            
            if(!("User-ID".equalsIgnoreCase(tokens[0]))){
                context.write(new Text(tokens[0]), new Text("A" + value));
    
           }
    }
}
