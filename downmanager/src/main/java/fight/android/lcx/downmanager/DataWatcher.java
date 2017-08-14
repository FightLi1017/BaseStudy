package fight.android.lcx.downmanager;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by lichenxi on 2017/8/14.
 */

public abstract class DataWatcher implements Observer {
    @Override
    public void update(Observable o, Object data) {
          if (data instanceof DownEntry){
              notifyUpdata((DownEntry)data);
          }
    }

    protected abstract void notifyUpdata(DownEntry data);
}
