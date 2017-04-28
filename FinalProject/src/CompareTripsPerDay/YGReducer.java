/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompareTripsPerDay;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author nirmal
 */
public class YGReducer extends Reducer<Text,Text,Text,Text>{
    
    private Text tmp=new Text();
    private String greenTaxi,yellowTaxi;

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        
        while(values.iterator().hasNext()){
                tmp=values.iterator().next();
                if(tmp.charAt(0)=='G'){
                   greenTaxi= tmp.toString();
                }
                else if(tmp.charAt(0)=='Y'){
                    yellowTaxi=tmp.toString();
                   
                }
            
            }
        
        if(greenTaxi!=null && yellowTaxi!=null){
              context.write(key, new Text(greenTaxi+","+yellowTaxi));
        }
        else if(greenTaxi==null){
            context.write(key, new Text("G-0"+","+yellowTaxi));
        }
        else if(yellowTaxi==null){
               context.write(key, new Text(greenTaxi+","+"Y-0"));
        }
      
        
    }
    
    
    
}
