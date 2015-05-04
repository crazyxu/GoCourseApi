package net.gocourse.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import net.gocourse.api.action.UserManager;
import net.gocourse.api.bean.ActionResEntity;

import java.util.Map;


public class MainActivity extends ActionBarActivity {
    private TextView log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log=(TextView)super.findViewById(R.id.log);
        new Thread(
            new Runnable() {
                @Override
                public void run() {
                    userLoginTest();
                }
            }
        ).start();
    }

    //注册
    void userRegTest(){
        ActionResEntity resEntity= new UserManager().userReg("3844545014@qq.com", "123456");
        if(resEntity.isResState())
            Log.i("userRegTest","用户注册成功");
        else
            Log.i("userRegTest","用户注册失败");
    }

    //登录
    void userLoginTest(){
        ActionResEntity resEntity= new UserManager().userLogin("384454501@qq.com", "123456");
        if(resEntity.isResState())
            Log.i("userRegTest","用户登录成功");
        else
            Log.i("userRegTest","用户登录失败");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
