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
public class Reducer1 extends Reducer<Text,Text,Text,Text>{
    
    private ArrayList<Text> listA=new ArrayList<Text>();
    private ArrayList<Text> listB=new ArrayList<Text>();
    private Text tmp=new Text();
    
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        
        listA.clear();
            listB.clear();
            while(values.iterator().hasNext()){
                tmp=values.iterator().next();
                if(tmp.charAt(0)=='A'){
                    listA.add(new Text(tmp.toString().substring(1)));
                }
                else if(tmp.charAt(0)=='B'){
                    int index=tmp.toString().indexOf(";");
                    listB.add(new Text(tmp.toString().substring(index+1)));
                }
            
            }
            
            executeJoinLogic(context);
     }
    
    private void executeJoinLogic(Context context) throws IOException, InterruptedException{
        
        if (!listA.isEmpty() && !listB.isEmpty()) {
            for (Text A : listA) {
                for (Text B : listB) {
                    context.write(A, B);
                }
            }
        }
        
    }
    
    
    
}
