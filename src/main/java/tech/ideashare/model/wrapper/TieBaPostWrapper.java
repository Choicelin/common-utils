package tech.ideashare.model.wrapper;


import tech.ideashare.model.TieBaPost;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by lixiang on 11/07/2017.
 */
@XmlRootElement(name = "posts")
public class TieBaPostWrapper extends BaseWrapper<TieBaPost> {

    private List<String> title;

    private List<String> content;


    @XmlElement(name = "title")
    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    @XmlElement(name = "content")
    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }


    @Override
    public List<TieBaPost> getList() {
        return null;
    }

    @Override
    public void setList(List<TieBaPost> list) {

    }
}
