package router.android.lcx.basestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import fight.android.lcx.downmanager.DataWatcher;
import fight.android.lcx.downmanager.DownEntry;
import fight.android.lcx.downmanager.DownManager;
import fight.android.lcx.downmanager.TLog;

public class ListActivity extends AppCompatActivity {

    private DownManager mDownloadManager;
    private ArrayList<DownEntry> mDownloadEntries = new ArrayList<>();
    private DataWatcher watcher = new DataWatcher() {
        @Override
        public void notifyUpdata(DownEntry data) {
            int index = mDownloadEntries.indexOf(data);
            if (index != -1){
                mDownloadEntries.remove(index);
                mDownloadEntries.add(index,data);
                adapter.notifyDataSetChanged();
            }
            TLog.e(data.toString());
        }
    };
    private ListView mDownloadLsv;
    private DownloadAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDownloadManager = DownManager.getInstance(this);
        setContentView(R.layout.activity_list);
        mDownloadEntries.add(new DownEntry("http://api.stay4it.com/uploads/test0.jpg"));
        mDownloadEntries.add(new DownEntry("http://api.stay4it.com/uploads/test1.jpg"));
        mDownloadEntries.add(new DownEntry("http://api.stay4it.com/uploads/test2.jpg"));
        mDownloadEntries.add(new DownEntry("http://api.stay4it.com/uploads/test3.jpg"));
        mDownloadEntries.add(new DownEntry("http://api.stay4it.com/uploads/test4.jpg"));
        mDownloadEntries.add(new DownEntry("http://api.stay4it.com/uploads/test5.jpg"));
        mDownloadEntries.add(new DownEntry("http://api.stay4it.com/uploads/test6.jpg"));
        mDownloadEntries.add(new DownEntry("http://api.stay4it.com/uploads/test7.jpg"));
        mDownloadEntries.add(new DownEntry("http://api.stay4it.com/uploads/test8.jpg"));
        mDownloadEntries.add(new DownEntry("http://api.stay4it.com/uploads/test9.jpg"));
        mDownloadLsv = (ListView) findViewById(R.id.mDownloadLsv);
        adapter = new DownloadAdapter();
        mDownloadLsv.setAdapter(adapter);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDownloadManager.pauseAll();
            }
        });
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDownloadManager.resumeAll();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDownloadManager.addObserver(watcher);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mDownloadManager.removeObserver(watcher);
    }

    class DownloadAdapter extends BaseAdapter {

        private ViewHolder holder;

        @Override
        public int getCount() {
            return mDownloadEntries != null ? mDownloadEntries.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return mDownloadEntries.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null || convertView.getTag() == null) {
                convertView = LayoutInflater.from(ListActivity.this).inflate(R.layout.activity_list_item, null);
                holder = new ViewHolder();
                holder.mDownloadBtn = (Button) convertView.findViewById(R.id.mDownloadBtn);
                holder.mDownloadLabel = (TextView) convertView.findViewById(R.id.mDownloadLabel);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final DownEntry entry = mDownloadEntries.get(position);
            holder.mDownloadLabel.setText(entry.name + " is " + entry.mDownloadStatus + " " + entry.currentlength + "/" + entry.totallength);
            holder.mDownloadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (entry.mDownloadStatus == DownEntry.DownloadStatus.idle) {
                        mDownloadManager.add(entry);
                    } else if (entry.mDownloadStatus == DownEntry.DownloadStatus.downloading ||entry.mDownloadStatus == DownEntry.DownloadStatus.waiting  ) {
                        mDownloadManager.pause(entry);
                    } else if (entry.mDownloadStatus == DownEntry.DownloadStatus.paused) {
                        mDownloadManager.resume(entry);
                    }
                }
            });
            return convertView;
        }
    }

    static class ViewHolder {
        TextView mDownloadLabel;

        Button mDownloadBtn;
    }
}
