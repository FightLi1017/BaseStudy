package fight.android.lcx.downmanager.SimpleDown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lichenxi on 2017/11/9.
 */

public class FileRequestOperator {
     private String url;
     private String filename;
     private String savepath=Down.path;
     private ExecutorService mExecutorService;
     private DownAction mDownAction;
    public FileRequestOperator(String url) {
        this.url=url;
        mExecutorService=Executors.newCachedThreadPool();
    }
    //getpath getname callback

    public FileRequestOperator savePath(String savepath){
        this.savepath=savepath;
        return this;
    }
    public FileRequestOperator name(String filename){
        this.filename=filename;
        return this;
    }
    public FileRequestOperator callback(FileCallback fileCallback){
        mDownAction=new DownAction(url,filename,savepath);
        mDownAction.setFileCallback(fileCallback);
        return this;
    }

    public void startdown(){
       mExecutorService.submit(mDownAction);
    }
}
