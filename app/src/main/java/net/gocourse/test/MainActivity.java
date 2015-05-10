package net.gocourse.test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {
    private MyApplication app;
    //drawer
    private DrawerLayout drawer;

    private ActionBar bar;
    //却换箭头
    private ActionBarDrawerToggle drawerToggle;

    //功能列表
    ListView lv;

    //
    String[] arrLv=new String[]{"用户管理","课程管理"};

    String barTitle=arrLv[0];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initApp();
        initDrawer();
        initLV();
    }

    void initApp(){
        app=(MyApplication)getApplication();
        bar=getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        //默认初试fragment
        bar.setTitle("用户管理");
        bar.show();
    }
    void initLV(){
        lv=(ListView)super.findViewById(R.id.lv_dl);
        //简单的ArrayAdapter
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrLv));
        //设置监听
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                Fragment f=null;
                switch (position){
                    case 0:
                        f=new UserTestFra();
                        break;
                    case 1:
                        f=new CourseTestFra();
                        break;
                }
                ft.replace(R.id.fragment_layout, f);
                ft.commit();
                barTitle=arrLv[position];
                bar.setTitle(barTitle);
                drawer.closeDrawer(lv);

            }
        });
    }

    /**
     * 初始化DrawerLayout
     */
    void initDrawer(){
        drawer = (DrawerLayout) super.findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawer,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ){
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                bar.setTitle(barTitle);
                // invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                bar.setTitle(getResources().getString(R.string.app_name));
                //  invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawer.setDrawerListener(drawerToggle);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_layout, new UserTestFra());
        ft.commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                break;
            case android.R.id.home:
                if(drawer.isDrawerOpen(lv))
                    drawer.closeDrawer(lv);
                else
                    drawer.openDrawer(lv);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
