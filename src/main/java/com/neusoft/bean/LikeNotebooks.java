package com.neusoft.bean;

public class LikeNotebooks {
    private Integer id;

    private String notebookId;

    private String time;

    private Integer userid;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(String notebookId) {
        this.notebookId = notebookId == null ? null : notebookId.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        String[] timeArr = time.split("\\+");
        time = timeArr[0].replace("T"," ");
        this.time = time == null ? null : time.trim();
    }
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}