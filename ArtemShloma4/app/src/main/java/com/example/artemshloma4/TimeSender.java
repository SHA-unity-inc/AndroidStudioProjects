package com.example.artemshloma4;

import java.io.Serializable;

public class TimeSender implements Serializable {
    String day, time, comment;

    public TimeSender(String day, String time, String comment) {
        this.day = day;
        this.time = time;
        this.comment = comment;
    }
}
