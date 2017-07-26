package tech.ideashare.model.wrapper;

import tech.ideashare.model.wrapper.BaseWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by lixiang on 14/07/2017.
 */
@XmlRootElement(name = "replies")
public class TieBaReplyWrapper extends BaseWrapper {

    private List<String> reply;



    @XmlElement(name = "reply")
    public List<String> getReply() {
        return reply;
    }

    public void setReply(List<String> reply) {
        this.reply = reply;
    }

    @Override
    public List getList() {
        return null;
    }

    @Override
    public void setList(List list) {

    }
}
