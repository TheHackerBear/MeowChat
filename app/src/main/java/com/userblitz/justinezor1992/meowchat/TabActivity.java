package com.userblitz.justinezor1992.meowchat;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;


public class TabActivity extends Activity {

    String TAG = "meowChat";

    ActionBar.Tab tab1, tab2, tab3;

    Fragment fragmentTab1 = new FragmentTab1();
    Fragment fragmentTab2 = new FragmentTab2();
    Fragment fragmentTab3 = new FragmentTab3();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tab1 = actionBar.newTab().setText("1");
        tab2 = actionBar.newTab().setText("2");
        tab3 = actionBar.newTab().setText("3");

        tab1.setTabListener(new MyTabListener(fragmentTab1));
        tab2.setTabListener(new MyTabListener(fragmentTab2));
        tab3.setTabListener(new MyTabListener(fragmentTab3));

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);

    }

    public class FragmentTab1 extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_tab, container, false);
            TextView textview = (TextView) view.findViewById(R.id.tabTextView);
            textview.setText(R.string.friendList);
            return view;
        }
    }
    public class FragmentTab2 extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_tab2, container, false);
            TextView textview = (TextView) view.findViewById(R.id.tabTextView2);
            textview.setText(R.string.Two);
            return view;
        }
    }
    public class FragmentTab3 extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_tab3, container, false);
            TextView textview = (TextView) view.findViewById(R.id.tabTextView3);
            textview.setText(R.string.Three);
            return view;
        }
    }

    public class MyTabListener implements ActionBar.TabListener {
        Fragment fragment;

        public MyTabListener(Fragment fragment) {
            this.fragment = fragment;
        }

        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.replace(R.id.container, fragment);
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(fragment);
            Log.i(TAG, "Unselected was called");
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            // nothing done here
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_tab, container, false);
        }
    }
}
