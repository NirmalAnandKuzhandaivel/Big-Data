/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part5;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author nirmal
 */
public class Reducer2 extends Reducer<Text,Text,Text,Text>{
    
    private ArrayList<Text> listC=new ArrayList<Text>();
    private ArrayList<Text> listD=new ArrayList<Text>();
    private Text tmp=new Text();


    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            
            listC.clear();
            listD.clear();
            while(values.iterator().hasNext()){
                tmp=values.iterator().next();
                if(tmp.charAt(0)=='C'){
                    listC.add(new Text(tmp.toString().substring(1)));
                }
                else if(tmp.charAt(0)=='D'){
                    //int index=tmp.toString().indexOf(";");
                    listD.add(new Text(tmp.toString().substring(1)));
                }
            
            }
            
            executeJoinLogic(context);
    }
    
    
    
    private void executeJoinLogic(Context context) throws IOException, InterruptedException{
        
        if (!listC.isEmpty() && !listD.isEmpty()) {
            for (Text A : listC) {
                for (Text B : listD) {
                    context.write(A, B);
                }
            }
        }
    
    }
    
} 
