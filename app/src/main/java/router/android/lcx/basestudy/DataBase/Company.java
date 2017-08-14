package router.android.lcx.basestudy.DataBase;


import router.android.lcx.basestudy.DataBase.Annotation.Column;
import router.android.lcx.basestudy.DataBase.Annotation.Table;

/**
 * Created by lichenxi on 2017/4/6.
 */
@Table(name = "company")
public class Company extends BasePeople {
    @Column
    private String name;
    @Column
    private String id;
    @Column
    private String url;
    @Column
    private String tel;
    @Column
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
