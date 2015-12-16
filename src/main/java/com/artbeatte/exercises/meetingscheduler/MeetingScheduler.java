package com.artbeatte.exercises.meetingscheduler;

/**
 * @author art.beatte
 * @version 10/21/15
 */
public abstract class MeetingScheduler {
    public abstract boolean scheduleSoonestMeeting(int durationMinutes, MeetingCalendar[] attendees);
}
