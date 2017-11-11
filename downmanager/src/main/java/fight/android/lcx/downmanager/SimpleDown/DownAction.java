package fight.android.lcx.downmanager.SimpleDown;

import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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


    }

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
            connection.connect();
            String field= connection.getHeaderField("Content-Disposition");

            if (!TextUtils.isEmpty(filename)){
                saveFileAllName=saveDir.getAbsolutePath()+"/"+filename;
            }else{
                String netfilename=Httputil.getNetFilename(field,downurl);
                saveFileAllName=saveDir.getAbsolutePath()+"/"+netfilename;
            }
           final File newFile=Fileutil.handleFileName(saveFileAllName);
            //获取内容长度
            int contentLength = connection.getContentLength();

            inputStream = connection.getInputStream();
            fileOutputStream = new FileOutputStream(newFile);
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
                Httputil.runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mFileCallback.downloadProgress(mprogress);
                    }
                });

            }
            Httputil.runUiThread(new Runnable() {
                @Override
                public void run() {
                    mFileCallback.success(newFile);
                }
            });
            connection.disconnect();
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
