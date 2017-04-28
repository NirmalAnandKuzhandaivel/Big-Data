/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompareTripsPerDay;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class YellowMapper extends Mapper<Object,Text,Text,Text>{

     private Text outKey=new Text();
    private Text outValue=new Text();
    
    
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
       
        String tokens[]=value.toString().split("\\t");
        outKey.set(tokens[0]);
        outValue.set("Y"+tokens[1]);
        context.write(outKey,outValue);
   
    }
    
    
    
}
