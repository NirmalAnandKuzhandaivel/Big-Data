/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistanceFareSorting;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class DistanceFareMapper extends Mapper<Object,Text,DistanceFare,DistanceFareRecord>{
    
    private DistanceFare df=new DistanceFare();
    private DistanceFareRecord dfr=new DistanceFareRecord();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        
        
        String tokens[]=value.toString().split(",");
        if(tokens.length==21){
        if(!(tokens[0].equals("")) && !(tokens[0].equalsIgnoreCase("VendorID"))){
        
        String date=tokens[1].split(" ")[0];
        
        
        
        
        
        double dist=Double.parseDouble(tokens[10]);
        double far=Double.parseDouble(tokens[11]);
        
        int distance=(int) dist;
        int fare=(int) far;
        if(fare>0){
        
        df.setDistance(distance);
        df.setFare(fare);
        
        dfr.setDate(date);
        dfr.setDistance(dist);
        dfr.setFare(far);
        
        context.write(df, dfr);
        }
        
        }
        }
         
    }
    
    
    
}
