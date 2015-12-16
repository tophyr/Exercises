package com.artbeatte.exercises.meetingscheduler;

import java.util.Date;

public final class Meeting {
    private static final int MIN_TO_MILLI = 60000;

    public final Date startTime;
    public final Date endTime;
    public final int durationMinutes;

    public Meeting(Date start, int durationMinutes) {
        this.startTime = start;
        this.durationMinutes = durationMinutes;
        this.endTime = new Date(startTime.getTime() + (durationMinutes * MIN_TO_MILLI));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("meeting={startTime=")
                .append(startTime)
                .append(", durationMinutes=")
                .append(durationMinutes)
                .append("}");
        return sb.toString();
    }
}
