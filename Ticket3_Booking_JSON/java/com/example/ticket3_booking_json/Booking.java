package com.example.ticket3_booking_json;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "bookings")
public class Booking {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long customerCode;
    private Date startDate;
    private String payingMethod;
    private float payedSum;

    public Booking(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Booking(long customerCode, Date startDate, String payingMethod, float payedSum) {
        this.customerCode = customerCode;
        this.startDate = startDate;
        this.payingMethod = payingMethod;
        this.payedSum = payedSum;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "customerCode=" + customerCode +
                ", startDate=" + startDate +
                ", payingMethod='" + payingMethod + '\'' +
                ", payedSum=" + payedSum +
                '}';
    }

    public long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(long customerCode) {
        String customerCodeStr = String.valueOf(customerCode);
        if (customerCodeStr.length() != 10) {
            throw new IllegalArgumentException("Customer code must have exactly 10 characters.");
        }
        this.customerCode = customerCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getPayingMethod() {
        return payingMethod;
    }

    public void setPayingMethod(String payingMethod) {

        this.payingMethod = payingMethod;
    }

    public float getPayedSum() {
        return payedSum;
    }

    public void setPayedSum(float payedSum) {
        if(payedSum<0)throw new IllegalArgumentException("sum shoul be > 0");
        this.payedSum = payedSum;
    }
}
