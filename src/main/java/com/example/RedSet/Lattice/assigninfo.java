package com.example.RedSet.Lattice;

public class assigninfo {
   String id, text, code, inp="", timelimit;
   private static final assigninfo instance = new assigninfo();
   public static assigninfo getInstance(){
       return instance;
   }

    public String getInp() {
        return inp;
    }

    public void setInp(String inp) {
        this.inp = inp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }
}
