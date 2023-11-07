package com.example.RedSet.Notes;

public class noteinfo {
    String title, note, user, date;

    public noteinfo(String title, String note, String user, String date) {
        this.title = title;
        this.note = note;
        this.user = user;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static int comp(noteinfo a, noteinfo b){
        return a.date.compareTo(b.date) ;
    }
}
