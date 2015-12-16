package com.artbeatte.exercises.meetingscheduler;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by sarbs on 12/17/15.
 */
public class MeetingCalendarTest {
    private MeetingCalendar CALENDAR;

    @Before
    public void setUp() throws Exception {
        CALENDAR = new SampleMeetingCalendar();
    }

    @Test
    public void testConflictingMeetings() throws Exception {
        CALENDAR.addMeeting(new Meeting(new Date(), 60));
        CALENDAR.addMeeting(new Meeting(new Date(), 60));
        assertEquals(1, CALENDAR.getAgenda().size());
    }

    @Test
    public void testMeetingAtExisting() throws Exception {
        Meeting m = new Meeting(new Date(), 60);

        CALENDAR.addMeeting(m);

        assertEquals(m, CALENDAR.getMeetingAtTime(new Date()));
    }

    @Test
    public void testMeetingAtNonexistent() throws Exception {
        assertNull(CALENDAR.getMeetingAtTime(new Date()));
    }

    // TODO needs more tests
}
