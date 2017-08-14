package router.android.lcx.basestudy;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import router.android.lcx.basestudy.DataBase.Company;
import router.android.lcx.basestudy.DataBase.Developer;
import router.android.lcx.basestudy.DataBase.Manager.DBManager;
import router.android.lcx.basestudy.DataBase.Skill;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("router.android.lcx.basestudy", appContext.getPackageName());
    }
     @Test
      public void add( ){
        Company company=new Company();
        company.setId("0001");
        company.setName("LCX");
        company.setUrl("http://www.baidu.com");
        company.setTel("13650527163");
        company.setAddress("一本道");
        DBManager.getinstance(InstrumentationRegistry.getTargetContext()).newOrUpdate(company);
    }
    @Test
    public void add1( ){
        Developer developer=new Developer();
        developer.setId("0001");
        developer.setName("LCX");
        developer.setAge(17);
        Skill skill=new Skill();
        skill.setName("Coder");
        skill.setDescript("Android");
        Skill skill1=new Skill();
        skill1.setName("Coder");
        skill1.setDescript("FrameWork");
        ArrayList<Skill> skills=new ArrayList<>();
        skills.add(skill);
        skills.add(skill1);
        developer.setSkill(skills);
        DBManager.getinstance(InstrumentationRegistry.getTargetContext()).newOrUpdate(developer);
        querybyid();
    }
    public  void querybyid(){
        Developer developer=DBManager.getinstance(InstrumentationRegistry.getTargetContext())
                             .queryByid(Developer.class,"0001");
        Log.d("LCX",developer.toString());
    }
    @Test
    public void droptable( ){
        DBManager.getinstance(InstrumentationRegistry.getTargetContext()).DropTable();
    }
}
