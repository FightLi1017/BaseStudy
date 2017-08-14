package router.android.lcx.basestudy.DataBase;

/**
 * Created by lichenxi on 2017/4/6.
 */

public class Skill extends BasePeople {
    private String name;
    private String descript;


    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", descript='" + descript + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public Skill setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescript() {
        return descript;
    }

    public Skill setDescript(String descript) {
        this.descript = descript;
        return this;
    }
}
