package indi.welljun.domain;

public class Send {
    int nid;
    int uid;
    String sendTime;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getNid() {
        return nid;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
