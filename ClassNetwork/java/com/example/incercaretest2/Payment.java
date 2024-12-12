package com.example.incercaretest2;
import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {
    private long customerCode;
    private String paymentMethod;
    private float sum;
    private Date paymentDate;

    public Payment(){

    }
    public Payment(long customerCode, String paymentMethod, float sum, Date paymentDate) {
        this.customerCode = customerCode;
        this.paymentMethod = paymentMethod;
        this.sum = sum;
        this.paymentDate = paymentDate;
    }



    public long getCustomerCode() {
        return customerCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public float getSum() {
        return sum;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "customerCode=" + customerCode +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", sum=" + sum +
                ", paymentDate=" + paymentDate +
                '}';
    }


    public void setCustomerCode(long customerCode) {
        this.customerCode = customerCode;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setSum(float sum) {
        if (sum <= 0) {
            throw new IllegalArgumentException("invalid");
        }
        this.sum = sum;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}