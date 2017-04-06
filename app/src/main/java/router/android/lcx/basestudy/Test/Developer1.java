package router.android.lcx.basestudy.Test;

import router.android.lcx.basestudy.DataBase.Annotation.Colum;
import router.android.lcx.basestudy.DataBase.Annotation.Table;


/**
 * Created by lichenxi on 2017/4/7.
 */

@Table(name = "developer")
public class Developer1 {
   @Colum
    private String name;
    @Colum(id = true)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
