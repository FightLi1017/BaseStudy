package router.android.lcx.basestudy.DataBase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by lichenxi on 2017/4/10.
 */

public class SerializeUtil {

    public static Object deserialize(byte[] bytes){
      if (null==bytes||bytes.length==0){
          return null;
      }
      ObjectInputStream objectInputStream=null;
      try {
          objectInputStream=new ObjectInputStream(new ByteArrayInputStream(bytes));
          return  objectInputStream.readObject();
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }finally {
          if (objectInputStream!=null){
              try {
                  objectInputStream.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
  }

    public static  byte[] serialize(Object object){
        if (null==object){
            return null;
        }
        ObjectOutputStream objectOutputStream=null;
        try {
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            objectOutputStream=new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            objectOutputStream=null;
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
