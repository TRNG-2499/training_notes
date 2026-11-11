package com.revature.time;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.Instant;

public class TimeZoneBasics {
    public static void main(String[] args) {

        // Attaching a time zone to a LocalDateTime using atZone()
        LocalDateTime meeting = LocalDateTime.of(2026, 7, 1, 9, 0); // 9:00 AM, no zone yet
        ZonedDateTime denverMeeting = meeting.atZone(ZoneId.of("America/Denver"));
        System.out.println("Meeting in Denver: " + denverMeeting);

        // Converting the SAME instant to another time zone with withZoneSameInstant()
        // Note: the wall-clock time changes, but it's still the exact same moment
        ZonedDateTime londonMeeting = denverMeeting.withZoneSameInstant(ZoneId.of("Europe/London"));
        System.out.println("Same meeting, viewed from London: " + londonMeeting);

        // ZoneOffset: a fixed offset from UTC, no daylight saving awareness
        ZonedDateTime fixedOffsetTime = ZonedDateTime.now(ZoneOffset.of("+12:00"));
        System.out.println("Fixed offset time: " + fixedOffsetTime);

        // Instant: an absolute point on the timeline, always UTC
        Instant now = Instant.now();
        System.out.println("Current instant (UTC): " + now);

        // Converting a ZonedDateTime to an Instant (e.g. before saving to a database)
        Instant meetingInstant = denverMeeting.toInstant();
        System.out.println("Meeting as an Instant: " + meetingInstant);

        // Converting an Instant back to a ZonedDateTime (e.g. when displaying to a user)
        ZonedDateTime displayTime = meetingInstant.atZone(ZoneId.of("Asia/Tokyo"));
        System.out.println("Displayed to a user in Tokyo: " + displayTime);

        // Best practice illustration:
        // Store/transmit as Instant, convert to ZonedDateTime only for display
        Instant eventTimestamp = Instant.now();          // stored internally
        ZonedDateTime userView = eventTimestamp.atZone(ZoneId.of("America/New_York")); // shown to user
        System.out.println("Event shown to New York user: " + userView);
    }
}
