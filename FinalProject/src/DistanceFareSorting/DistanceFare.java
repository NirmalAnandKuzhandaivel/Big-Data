/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistanceFareSorting;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

/**
 *
 * @author nirmal
 */
public class DistanceFare implements WritableComparable<DistanceFare>{
    
    private Integer distance;
    private Integer fare;
    
    public DistanceFare(){
        this.distance=0;
        this.fare=0;
    }
    
    public DistanceFare(Integer d,Integer f){
        this.distance=f;
        this.fare=f;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }
    
    



    
    

    
    
    

    @Override
    public void write(DataOutput d) throws IOException {
        //To change body of generated methods, choose Tools | Templates.
        d.writeInt(distance);
        d.writeInt(fare);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
         //To change body of generated methods, choose Tools | Templates.
         distance = di.readInt();
         fare = di.readInt();
    }

    @Override
    public int compareTo(DistanceFare o) {
        
      
        
       int result = distance.compareTo(o.distance);
       if(result == 0){
           result = fare.compareTo(o.fare);
       }
       return result;

   

    }
    



    
}
