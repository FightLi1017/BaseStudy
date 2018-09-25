package com.mainmodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mainmodule.compant.VerticalDrawerLayout;

public class ViewDragerHelperActivity extends AppCompatActivity {
    private VerticalDrawerLayout mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drager_helper);
        mView =  findViewById(R.id.layout_id);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vertical_drawer_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.press_menu_id) {
            if (mView.isDrawerOpened()) {
                mView.closeDrawer();
            }
            else {
                mView.openDrawer();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
