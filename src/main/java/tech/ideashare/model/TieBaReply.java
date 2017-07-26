package tech.ideashare.model;

/**
 * Created by lixiang on 14/07/2017.
 */
public class TieBaReply {

    private String tbName;

    private String tid;

    private String fid;

    private String reply;


    public TieBaReply() {
    }

    public TieBaReply(String tbName, String tid, String reply) {
        this.tbName = tbName;
        this.tid = tid;
        this.reply = reply;
    }

    public TieBaReply(String tbName, String tid, String fid, String reply) {
        this.tbName = tbName;
        this.tid = tid;
        this.fid = fid;
        this.reply = reply;
    }

    public String getTbName() {
        return tbName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
