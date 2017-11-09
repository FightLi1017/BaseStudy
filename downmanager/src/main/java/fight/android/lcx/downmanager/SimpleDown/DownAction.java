package fight.android.lcx.downmanager.SimpleDown;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lichenxi on 2017/11/9.
 */

public class DownAction implements Runnable {

    private String downurl;
    private String filename;
    private String savepath;
    private FileCallback mFileCallback;
    private File saveDir;
    private String saveFileAllName;
    public void setFileCallback(FileCallback fileCallback) {
        this.mFileCallback = fileCallback;
    }

    public DownAction(String url, String filename, String savepath) {
        this.downurl = url;
        this.filename = filename;
        this.savepath = savepath;
        //文件保存位置
        saveDir= new File(savepath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        saveFileAllName=saveDir.getAbsolutePath()+"/"+filename;
    }
//首先检查用户是否传入了文件名,如果传入,将以用户传入的文件名命名
//如果没有传入,那么将会检查服务端返回的响应头是否含有Content-Disposition=attachment;filename=FileName.txt该种形式的响应头,
//如果有,则按照该响应头中指定的文件名命名文件,如FileName.txt
//如果上述响应头不存在,则检查下载的文件url,例如:http://image.baidu.com/abc.jpg,那么将会自动以abc.jpg命名文件
//如果url也把文件名解析不出来,那么最终将以nofilename命名文件；
    @Override
    public void run() {
        FileOutputStream fileOutputStream=null;
        InputStream inputStream =null;
        try {
            URL url = new URL(downurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Accept-Encoding", "identity");
//            connection.setDoInput(true);
//            connection.setUseCaches(false)
//            //打开连接
            connection.connect();
            //获取内容长度
            int contentLength = connection.getContentLength();
           final File file =new File(saveFileAllName);
            if (!file.exists()){
                 file.createNewFile();
            }else{
                //根据浏览器一样 创建一个加1的同名文件
            }
            inputStream = connection.getInputStream();
            fileOutputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            long totalReaded = 0;
            int temp_Len;

            final Progress mprogress=new Progress();
            mprogress.setContentLength(contentLength);
            while ((temp_Len = inputStream.read(bytes)) != -1) {
                // bytes[index]= (byte) temp_Len;
                // index++;
                totalReaded += temp_Len;
//                long progress = totalReaded * 100 / contentLength;
                mprogress.setTotalReaded(totalReaded);
                fileOutputStream.write(bytes, 0, temp_Len);
                Handleutil.runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mFileCallback.downloadProgress(mprogress);
                    }
                });

            }
            Handleutil.runUiThread(new Runnable() {
                @Override
                public void run() {
                    mFileCallback.success(file);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            mFileCallback.error();
        }finally {
            try {
                if (fileOutputStream!=null){
                    fileOutputStream.close();}
                if (inputStream!=null){
                    inputStream.close();}
            } catch (IOException e) {
                e.printStackTrace();
                mFileCallback.error();
            }
        }

    }

//    private byte[] readInputStream(InputStream inputStream) throws IOException{
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        while ((len = inputStream.read(buffer)) != -1) {
//            bos.write(buffer, 0, len);
//        }
//        bos.close();
//        return bos.toByteArray();
//
//    }
}
