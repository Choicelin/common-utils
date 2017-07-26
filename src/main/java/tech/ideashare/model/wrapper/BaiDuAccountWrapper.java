package tech.ideashare.model.wrapper;

import tech.ideashare.model.BaiDuAccount;
import tech.ideashare.model.wrapper.BaseWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by lixiang on 11/07/2017.
 */
@XmlRootElement(name = "accounts")
public class BaiDuAccountWrapper extends BaseWrapper<BaiDuAccount> {

    private List<BaiDuAccount> list;

    @XmlElement(name = "account")
    public List<BaiDuAccount> getList() {
        return list;
    }

    public void setList(List<BaiDuAccount> list) {
        this.list= list;
    }
}
