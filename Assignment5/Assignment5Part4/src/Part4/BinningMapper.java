/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part4;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

/**
 *
 * @author nirmal
 */
public class BinningMapper extends Mapper<Object,Text,Text,NullWritable>{
    
    public MultipleOutputs<Text,NullWritable> mos=null;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        mos=new MultipleOutputs(context);
    }
    
    

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String tokens[]=value.toString().split(" ");
        String timeToken=tokens[3].substring(13, 15);
        mos.write("bins", value, NullWritable.get(), timeToken);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        mos.close();
    }
    
    
    
    
    
}
