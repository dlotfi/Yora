package com.example.yora.views;

import android.view.View;
import android.widget.Toast;

import com.example.yora.R;
import com.example.yora.activities.BaseActivity;
import com.example.yora.activities.MainActivity;

public class MainNavDrawer extends NavDrawer {
    public MainNavDrawer(final BaseActivity activity) {
        super(activity);

        addItem(new ActivityNavDrawerItem(MainActivity.class, "Inbox", null, R.drawable.ic_action_unread, R.id.include_main_nav_drawer_topItems));

        addItem(new BasicNavDrawerItem("Logout", null, R.drawable.ic_action_backspace, R.id.include_main_nav_drawer_bottomItems) {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "You have logged out!", Toast.LENGTH_SHORT).show();
                navDrawer.setOpen(false);
            }
        });
    }
}
