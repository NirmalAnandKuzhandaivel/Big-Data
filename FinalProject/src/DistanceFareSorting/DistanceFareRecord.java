/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistanceFareSorting;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

/**
 *
 * @author nirmal
 */
public class DistanceFareRecord implements Writable{
    
    private String date;
    private double distance;
    private double fare;
    
    public DistanceFareRecord(){
        this.date=null;
        this.distance=0;
        this.fare=0;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
    
    

    @Override
    public void write(DataOutput d) throws IOException {
         //To change body of generated methods, choose Tools | Templates.
         WritableUtils.writeString(d, date);
        d.writeDouble(distance);
        d.writeDouble(fare);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        date = WritableUtils.readString(di);
        distance = di.readDouble();
        fare=di.readDouble();
    }
    
    public String toString(){
        return (new StringBuilder().append(date)
                .append(",").append(distance).append(",").append(fare).toString());
                
        }
}
