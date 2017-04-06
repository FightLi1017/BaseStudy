package router.android.lcx.basestudy.DataBase;

import java.util.ArrayList;

/**
 * Created by lichenxi on 2017/4/6.
 */

public class Developer {
    public static final String _ID="id";
    public static final String _NAME="name";
    public static final String _AGE="age";
    public static final String _COMPANY="company";
    public static final String _SKILLS="skills";
    private String id;
    private String name;
    private  int age;
    private Company mCompany;
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

    public Company getCompany() {
        return mCompany;
    }

    public void setCompany(Company company) {
        mCompany = company;
    }

    public ArrayList<Skill> getSkill() {
        return mSkill;
    }

    public void setSkill(ArrayList<Skill> skill) {
        mSkill = skill;
    }
}
