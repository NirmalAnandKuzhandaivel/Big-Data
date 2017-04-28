/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DayOfWeekRides;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

/**
 *
 * @author nirmal
 */
public class DOWMapper extends Mapper<Object,Text,Text,NullWritable>{
    
    public MultipleOutputs<Text,NullWritable> mos=null;
    private Text output;
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        mos.close();
    }

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException{
       
        try {
            
            float pickup_long,pickup_lat,drop_long,drop_lat;
            String pickup,drop;
            
            String tokens[]=value.toString().split(",");
            if(!(tokens[0].equals("")) && !(tokens[0].equalsIgnoreCase("VendorID"))){
            String s=tokens[1];
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=formatter.parse(s);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String dayOfWeek = cal.getDisplayName( Calendar.DAY_OF_WEEK ,Calendar.LONG, Locale.getDefault());
            
            pickup_long=Float.parseFloat(tokens[5]);
            pickup_lat=Float.parseFloat(tokens[6]);
            drop_long=Float.parseFloat(tokens[7]);
            drop_lat=Float.parseFloat(tokens[8]);
            
    if((pickup_long > -74.016309 && pickup_long<-73.943986) && (pickup_lat>40.703363 && pickup_lat< 40.822683)) {
           pickup="Manhattan"; 
    }else if((pickup_long>-73.996224 && pickup_long< -73.904354) && (pickup_lat>40.590195 && pickup_lat< 40.699933)){
        pickup="Brooklyn"; 
    }else if((pickup_long>-73.929015 && pickup_long< -73.732468) && (pickup_lat>40.667349 && pickup_lat< 40.782815)){
       pickup="Queen"; 
    }else if((pickup_long>-73.909672 && pickup_long< -73.814439) && (pickup_lat>40.808118 && pickup_lat< 40.910298)){
        pickup="Bronx"; 
    }else if((pickup_long>-74.241142 && pickup_long< -74.093445) && (pickup_lat>40.506272 && pickup_lat< 40.643899)){
        pickup="Staten Island"; 
    }else if((pickup_long>-73.815939 && pickup_long< -73.764694) && (pickup_lat>40.636008 && pickup_lat< 40.662522)){
        pickup="JFK Airport"; 
    }else{
        pickup="UnKnown";
    }
    
    if((drop_long > -74.016309 && drop_long<-73.943986) && (drop_lat>40.703363 && drop_lat< 40.822683)) {
           drop="Manhattan"; 
    }else if((drop_long>-73.996224 && drop_long< -73.904354) && (drop_lat>40.590195 && drop_lat< 40.699933)){
        drop="Brooklyn"; 
    }else if((drop_long>-73.929015 && drop_long< -73.732468) && (drop_lat>40.667349 && drop_lat< 40.782815)){
       drop="Queen"; 
    }else if((drop_long>-73.909672 && drop_long< -73.814439) && (drop_lat>40.808118 && drop_lat< 40.910298)){
        drop="Bronx"; 
    }else if((drop_long>-74.241142 && drop_long< -74.093445) && (drop_lat>40.506272 && drop_lat< 40.643899)){
        drop="Staten Island"; 
    }else if((drop_long>-73.815939 && drop_long< -73.764694) && (drop_lat>40.636008 && drop_lat< 40.662522)){
        drop="JFK Airport"; 
    }else{
        drop="UnKnown";
    }
    
    
         output=new Text(value+","+pickup+","+drop);
         
            if(dayOfWeek.equalsIgnoreCase("Monday")){
              mos.write("bins", output, NullWritable.get(), "Monday");  
            }
            if(dayOfWeek.equalsIgnoreCase("Tuesday")){
              mos.write("bins", output, NullWritable.get(), "Tuesday");  
            }
            if(dayOfWeek.equalsIgnoreCase("Wednesday")){
              mos.write("bins", output, NullWritable.get(), "Wednesday");  
            }
            if(dayOfWeek.equalsIgnoreCase("Thursday")){
              mos.write("bins", output, NullWritable.get(), "Thursday");  
            }
            if(dayOfWeek.equalsIgnoreCase("Friday")){
              mos.write("bins", output, NullWritable.get(), "Friday");  
            }
            if(dayOfWeek.equalsIgnoreCase("Saturday")){
              mos.write("bins", output, NullWritable.get(), "Saturday");  
            }
            if(dayOfWeek.equalsIgnoreCase("Sunday")){
              mos.write("bins", output, NullWritable.get(), "Sunday");  
            }
            
            
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(DOWMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        mos=new MultipleOutputs(context);
    }
    
    
    
}
