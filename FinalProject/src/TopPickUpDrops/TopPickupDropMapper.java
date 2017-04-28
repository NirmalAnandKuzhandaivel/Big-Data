/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TopPickUpDrops;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class TopPickupDropMapper extends Mapper<Object,Text,Text,IntWritable>{
    
    private Text outkey=new Text();
   // private PickDropTuple pd=new PickDropTuple();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String tokens[]=value.toString().split(",");
        String pickup,drop;
        pickup=tokens[21];
        drop=tokens[22];
        if((!pickup.equalsIgnoreCase("UnKnown")) && (!drop.equalsIgnoreCase("UnKnown"))){
           // pd.setPick(pickup);
           // pd.setDrop(drop);
            outkey=new Text(pickup+"-"+drop);
            context.write(outkey,new IntWritable(1));
        }
    }
    
    
    
}
