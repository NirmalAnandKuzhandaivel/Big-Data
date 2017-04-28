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
public class Mapper3 extends Mapper<Object,Text,Text,Text>{
    
    private Text isbn=new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String tokens[]=value.toString().split("\t");
        isbn=new Text(tokens[1].split(";")[0].replace("\"", ""));
        context.write(isbn, new Text("C" + value));
    }
    
    
    
}
