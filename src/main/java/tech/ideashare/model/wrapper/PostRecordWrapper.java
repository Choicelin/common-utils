package tech.ideashare.model.wrapper;

import tech.ideashare.model.PostRecord;
import tech.ideashare.model.wrapper.BaseWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by lixiang on 11/07/2017.
 */
@XmlRootElement(name = "records")
public class PostRecordWrapper extends BaseWrapper<PostRecord> {

    private List<PostRecord> list;

    @XmlElement(name = "record")
    public List<PostRecord> getList() {
        return list;
    }

    public void setList(List<PostRecord> list) {
        this.list=list;
    }
}
