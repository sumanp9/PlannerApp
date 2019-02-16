package com.pleasedo.planner.com.pleasedo.dbs;

public class Note {

    private String noteID,noteTitle, noteDescription, noteDate;

    public Note(){}

    public Note(String noteTitle, String noteDescription, String noteDate){
        this.noteTitle  = noteTitle;
        this.noteDescription =noteDescription;
        this.noteDate = noteDate;
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }
}
