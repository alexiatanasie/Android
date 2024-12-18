package com.example.ticket3_booking_json;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

    private static SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    @TypeConverter
    public static Date fromString(String dateString){
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @TypeConverter
    public static String fromDate(Date value){

        if(value==null){
            return null;
        }

        return formatter.format(value);
    }

}
