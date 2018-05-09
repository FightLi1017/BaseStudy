package fight.android.lcx.downmanager.SimpleDown;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


/**
 * Created by lichenxi on 2017/10/10.
 */

public class Httputil {

    public static void runUiThread(Runnable runnable){
        FileDownManager.getinstance().getDelivery().post(runnable);
    }
    //1首先检查用户是否传入了文件名,如果传入,将以用户传入的文件名命名
//2如果没有传入,那么将会检查服务端返回的响应头是否含有Content-Disposition=attachment;filename=FileName.txt该种形式的响应头,
//如果有,则按照该响应头中指定的文件名命名文件,如FileName.txt
//3如果上述响应头不存在,则检查下载的文件url,例如:http://image.baidu.com/abc.jpg,那么将会自动以abc.jpg命名文件
//4如果url也把文件名解析不出来,那么最终将以nofilename命名文件；
    public static String getNetFilename(String content_Disposition,String url){
       String fileName=getHeadFilename(content_Disposition);
       if (TextUtils.isEmpty(fileName)){
           fileName=getUrlName(url);
       }
        try {
            fileName = URLDecoder.decode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
        }

        return fileName;
    }

    private static String getUrlName(String url){
        String filename=null;
        String[] strings=url.split("/");
        for (String string: strings){
             if (string.contains("?")){
                 int endindex=string.indexOf("?");
                 if (endindex!=-1){
                     return  string.substring(0,endindex);
                 }
             }
        }
        if (strings.length>0){
            filename=strings[strings.length - 1];
        }
        return filename;
    }
    private static String getHeadFilename(String content_Disposition) {
        if (content_Disposition!=null){
            //文件名可能包含双引号
            content_Disposition=content_Disposition.replaceAll("\"", "");
            String split="filename=";
            int indexOf=content_Disposition.indexOf(split);
            if (indexOf!=-1){
                String temp =content_Disposition.substring(indexOf + split.length(),content_Disposition.length());
                return temp.split(";")[0];
            }
            split = "filename*=";
            indexOf = content_Disposition.indexOf(split);
            if (indexOf!=-1){
                String temp=content_Disposition.substring(indexOf + split.length(), content_Disposition.length());
                String fileName=temp.split(";")[0];
                String encode = "UTF-8''";
                if (fileName.startsWith(encode)){
                    fileName = fileName.substring(encode.length(), fileName.length());
                }
                return fileName;
            }
        }
        return null;
    }

}
