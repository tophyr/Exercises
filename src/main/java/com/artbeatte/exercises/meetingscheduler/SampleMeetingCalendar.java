package com.artbeatte.exercises.meetingscheduler;

import java.util.*;

/**
 * Created by sarbs on 12/16/15.
 */
public class SampleMeetingCalendar extends MeetingCalendar {
    private TreeSet<Meeting> mAgenda = new TreeSet<>(new Comparator<Meeting>() {
        @Override
        public int compare(Meeting o1, Meeting o2) {
            if (o1.endTime.before(o2.startTime)) {
                return -1;
            } else if (o1.startTime.after(o2.endTime)) {
                return 1;
            } else {
                return 0;
            }
        }
    });

    @Override
    public Set<Meeting> getAgenda() {
        return Collections.unmodifiableSet(mAgenda);
    }

    @Override
    public boolean addMeeting(Meeting m) {
        return mAgenda.add(m);
    }

    @Override
    public Meeting getMeetingAtTime(Date time) {
        Meeting instant = new Meeting(time, 0);
        NavigableSet<Meeting> subset = mAgenda.subSet(instant, true, instant, true);
        return subset.isEmpty() ? null : subset.first();
    }
}
