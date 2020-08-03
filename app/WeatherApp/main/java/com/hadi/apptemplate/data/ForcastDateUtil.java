package com.hadi.apptemplate.data;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.Locale;

public class ForcastDateUtil {

    public ForcastDateUtil() {
    }

    public String getDayFromDate(String dateString) {
        DateFormat format = new SimpleDateFormat("yyyy-mm-d", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateString.split(" ")[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat displayname = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        return displayname.format(date);


      // return  new SimpleDateFormat("EEEE").format(dateObject); // the day of the week spelled out completely
        //System.out.println(simpleDateformat.format(now));

    }
}
