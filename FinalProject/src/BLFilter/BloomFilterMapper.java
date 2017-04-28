/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLFilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Sink;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class BloomFilterMapper extends Mapper<Object,Text,Text,NullWritable>{
    
    Funnel<PickUpDrop> pd=new Funnel<PickUpDrop>() {
        @Override
        public void funnel(PickUpDrop t, Sink sink) {
           sink.putString(t.Pickup, Charsets.UTF_8).putString(t.drop,Charsets.UTF_8);
        }
    };

    BloomFilter<PickUpDrop> filter=BloomFilter.create(pd,500,0.1);

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
         //To change body of generated methods, choose Tools | Templates.
         PickUpDrop pt=new PickUpDrop("Brooklyn", "Manhattan");
        ArrayList<PickUpDrop> list = new ArrayList<PickUpDrop>();
        list.add(pt);
        
        for(PickUpDrop p:list){
            filter.put(p);
        }
    }
    
    
    
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line[] = value.toString().split(",");
        PickUpDrop pudp;
        String pickup=line[21];
        String drop=line[22];
        if((!pickup.equalsIgnoreCase("")||pickup!=null || !pickup.equalsIgnoreCase("null"))
                &&
            (!drop.equalsIgnoreCase("")||drop!=null || !drop.equalsIgnoreCase("null"))){
            
            pudp = new PickUpDrop(pickup,drop);
            
            
            if(filter.mightContain(pudp)){
                context.write(value,NullWritable.get());
            }
        
    }
        
        
    }
    
    
    
    
    
    
    

    
}
