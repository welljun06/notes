package indi.welljun.domain;

public class Note {
    private int nid;
    private String nname;
    private int aid;
    private String content;
    private String createTime;

    public int getNid() {
        return nid;
    }

    public String getNname() {
        return nname;
    }

    public int getAid() {
        return aid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getContent() {
        return content;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
