package com.artbeatte.exercises.meetingscheduler;

import java.util.Date;
import java.util.Set;

/**
 * Created by sarbs on 12/16/15.
 */
public abstract class MeetingCalendar {

    public abstract Set<Meeting> getAgenda();

    public abstract boolean addMeeting(Meeting m);

    public abstract Meeting getMeetingAtTime(Date time);
}
