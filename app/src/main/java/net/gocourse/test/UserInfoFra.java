package net.gocourse.test;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.gocourse.api.action.UserAccountAction;
import net.gocourse.api.action.UserInfoAction;
import net.gocourse.api.bean.ActionResEntity;

import java.util.HashMap;
import java.util.Map;


public class UserInfoFra extends Fragment implements View.OnClickListener{
    //功能按钮
    private Button btnUserInfo;

    //结果输出
    private TextView etInfo;
    MyApplication app;
    private View rootView;

    //等待
    private ProgressDialog loading;

    //操作结果
    private ActionResEntity resEntity;

    //MyHandler
    MyHandler myHandler;

    //操作code
    private final static int ACTION_GET_INFO=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_user_info, container, false);
        initView();
        app=(MyApplication)getActivity().getApplication();
        myHandler=new MyHandler();
        return rootView;
    }

    void initView(){
        btnUserInfo=(Button)rootView.findViewById(R.id.btn_get_info);
        //结果输出
        etInfo=(TextView)rootView.findViewById(R.id.tv_info);
        loading=new ProgressDialog(getActivity());
        loading.setMessage("loading...");
        //设置监听
        btnUserInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get_info:
                getUserInfo();
                break;

        }
    }

    //用户信息
    void getUserInfo(){
        //如果token为空，表示尚未登录或者过期
        if(TextUtils.isEmpty(app.getToken())){
            Toast.makeText(getActivity(),"尚未登录或者过期，请先登陆",Toast.LENGTH_LONG).show();
            return;
        }
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("token",app.getToken());
        new MyThread(ACTION_GET_INFO,params).start();
        loading.show();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            //输出到EditText
            if (resEntity!=null)
                etInfo.setText(resEntity.toString());
            else
                etInfo.setText("没有输出");
            loading.cancel();
            super.handleMessage(msg);
        }
    }

    class MyThread extends Thread{
        //操作code
        int code;
        //传递数据
        Map<String,Object> params;
        public MyThread(int code,Map<String,Object> params){
            this.code=code;
            this.params=params;
        }
        @Override
        public void run() {
            switch (code) {
                //获取用户信息
                case ACTION_GET_INFO:
                    resEntity = new UserInfoAction().getUserInfo((String)params.get("token"));
                    myHandler.sendEmptyMessage(ACTION_GET_INFO);
                    break;
            }
        }

    }

}
