package com.example.tanasie_alexia_1108;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

        private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        public static Date fromString(String dateString) {
            try {
                return formatter.parse(dateString);
            } catch (ParseException e) {
                return null;
            }
        }

        public static String fromDate(Date value) {
            if(value == null) {
                return null;
            }
            return formatter.format(value);
        }
    }

