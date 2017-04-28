/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kvtif;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author nirmal
 */
public class KeyMapper extends Mapper<Text,Text,Text,IntWritable>{
    
        @Override
	public void map(Text key, Text value, Context context) throws IOException{
		
		try{   
                    context.write(key, new IntWritable(Integer.valueOf(value.toString())));
		}
		catch(NumberFormatException | IOException | InterruptedException e){
			e.printStackTrace();
		}
	}
}
