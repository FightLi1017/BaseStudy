package router.android.lcx.basestudy.DataBase;

import java.util.ArrayList;

import router.android.lcx.basestudy.DataBase.Annotation.Column;
import router.android.lcx.basestudy.DataBase.Annotation.Table;

/**
 * Created by lichenxi on 2017/4/6.
 */
@Table(name = "developer")
public class Developer extends BasePeople  {
    public static final String _ID="id";
    public static final String _NAME="name";
    public static final String _AGE="age";
//    public static final String _COMPANY="company";
//    public static final String _SKILLS="skills";
    @Column(id = true)
    private String id;

    @Override
    public String toString() {
        return "Developer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", mSkill=" + mSkill +
                '}';
    }

    @Column
    private String name;
    @Column
    private  int age;
//    @Column(type = Column.ColumnType.TONE)
//    private Company mCompany;
    @Column(type = Column.ColumnType.Serializable)
    private ArrayList<Skill> mSkill;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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

//    public Company getCompany() {
//        return mCompany;
//    }
//
//    public void setCompany(Company company) {
//        mCompany = company;
//    }

    public ArrayList<Skill> getSkill() {
        return mSkill;
    }

    public void setSkill(ArrayList<Skill> skill) {
        mSkill = skill;
    }
}
