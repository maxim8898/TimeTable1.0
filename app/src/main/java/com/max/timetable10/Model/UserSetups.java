package com.max.timetable10.Model;

/**
 * Created by Влад on 23.10.2016.
 */

public class UserSetups {
    byte course;
    byte group;

    public byte getCourse() {
        return course;
    }

    public void setCourse(byte course) {
        this.course = course;
    }

    public byte getGroup() {
        return group;
    }

    public void setGroup(byte group) {
        this.group = group;
    }

    public UserSetups(byte course, byte group) {

        this.course = course;
        this.group = group;
    }
}
