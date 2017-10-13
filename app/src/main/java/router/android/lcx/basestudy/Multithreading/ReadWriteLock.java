package router.android.lcx.basestudy.Multithreading;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

/**
 * Created by lichenxi on 2017/10/10.
 */

public class ReadWriteLock {
    //仅当一个线程已经持有写锁 才允许写锁重入（再次获得写锁）
   private Map<Thread,Integer> readThread=new HashMap<>();
    private int writeAccesses    = 0;
    private int writers = 0;
    private int writeRequests = 0;
    private Thread writingThread = null;

    public synchronized void  lockRead() throws InterruptedException{
           Thread callthread=Thread.currentThread();
        while (!canGrantReadAccess(callthread)){
             wait();
        }
       readThread.put(callthread,getReadAccessCount(callthread)+1);
    }

  public synchronized  void unLockRead(){
       Thread callThread=Thread.currentThread();
       int accesscount=getReadAccessCount(callThread);
       if (accesscount==1){
           readThread.remove(callThread);
       }else {
           readThread.put(callThread,accesscount-1);
       }
      notifyAll();

  }
    //有写锁的时候 不能进行读取  优先级问题
    private boolean canGrantReadAccess(Thread callingThread) {
        if(isWriter(callingThread)) return true;
        if(writingThread != null) return false;
        if (isReader(callingThread)) return true;
         if (writeRequests>0) return false;

        return true;
    }

    private int getReadAccessCount(Thread callingThread){
        Integer accessCount = readThread.get(callingThread);
        if(accessCount == null) return 0;
        return accessCount.intValue();
    }

    private boolean isReader(Thread callingThread){
        return readThread.get(callingThread) != null;
    }

//write
//    仅当一个线程已经持有写锁，才允许写锁重入
    public synchronized  void  lockWrite() throws InterruptedException  {
         writeRequests++;
        Thread callThread=Thread.currentThread();
        while (!canGrantWriteAccess(callThread)){
            wait();
        }
        writeRequests--;
        writeAccesses++;
        writingThread = callThread;
    }
    private boolean isOnlyReader(Thread callingThread){
        return readThread.size() == 1 && readThread.get(callingThread) != null;
    }
    private boolean canGrantWriteAccess(Thread callthread) {
          //读锁升级为写锁
         if (isOnlyReader(callthread)) return true;

        if (hasReaders()) return false;
          if(writingThread == null)    return true;
          if (!isWriter(callthread)) return false; //仅当一个线程已经持有写锁，才允许写锁重入
        return true;
    }
    public synchronized void unlockWrite(){
        writeAccesses--;
        if(writeAccesses == 0){
            writingThread = null;
        }
        notifyAll();
    }



    private boolean isWriter(Thread callingThread){
        return writingThread == callingThread;
    }

    private boolean hasReaders(){
        return readThread.size() > 0;

    }

}
