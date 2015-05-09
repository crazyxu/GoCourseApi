package net.gocourse.test;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.gocourse.api.action.UserManager;
import net.gocourse.api.bean.ActionResEntity;

import java.util.HashMap;
import java.util.Map;


public class UserTestFra extends Fragment implements View.OnClickListener{
    //功能按钮
    private Button btnLogin;
    private Button btnLogout;
    private Button btnReg;
    private Button btnChangPwd;

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
    private final static int ACTION_LOGIN=0;
    private final static int ACTION_REGISTER=1;
    private final static int ACTION_LOGOUT=2;
    private final static int ACTION_CHANG_PWD=3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_user_test, container, false);
        initView();
        app=(MyApplication)getActivity().getApplication();
        myHandler=new MyHandler();
        return rootView;
    }

    void initView(){
        btnLogin=(Button)rootView.findViewById(R.id.btn_login);
        btnLogout=(Button)rootView.findViewById(R.id.btn_logout);
        btnReg=(Button)rootView.findViewById(R.id.btn_reg);
        btnChangPwd=(Button)rootView.findViewById(R.id.btn_chang_pwd);
        //结果输出
        etInfo=(TextView)rootView.findViewById(R.id.tv_info);
        loading=new ProgressDialog(getActivity());
        loading.setMessage("loading...");
        //设置监听
        btnLogin.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnReg.setOnClickListener(this);
        btnChangPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                userLoginTest();
                break;
            case R.id.btn_logout:
                userLogoutTest();
                break;
            case R.id.btn_chang_pwd:
                userChangPwdTest();
                break;
            case R.id.btn_reg:
                userRegTest();
                break;
        }
    }

    //用户登录
    void userLoginTest(){
        final View layout = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_login, null);
        new AlertDialog.Builder(getActivity()).setTitle("登录").setView(layout).
                setNegativeButton("取消", null).
                setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etName = (EditText) layout.findViewById(R.id.et_user_name);
                        EditText etPwd = (EditText) layout.findViewById(R.id.et_user_pwd);
                        Map<String,Object> params=new HashMap<String, Object>();
                        params.put("userName",etName.getText().toString());
                        params.put("userPwd",etPwd.getText().toString());
                        new MyThread(ACTION_LOGIN,params).start();
                        loading.show();
                    }
                }).show();
    }

    //用户注册
    void userRegTest(){
        final View layout = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_register,null);
        new AlertDialog.Builder(getActivity()).setTitle("登录").setView(layout).
                setNegativeButton("取消", null).
                setPositiveButton("注册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etName = (EditText) layout.findViewById(R.id.et_user_name);
                        EditText etPwd = (EditText) layout.findViewById(R.id.et_user_pwd);
                        Map<String,Object> params=new HashMap<String, Object>();
                        params.put("userName",etName.getText().toString());
                        params.put("userPwd",etPwd.getText().toString());
                        new MyThread(ACTION_REGISTER,params).start();
                        loading.show();
                    }
                }).show();
    }

    //用户注销
    void userLogoutTest(){
        //如果token为空，表示尚未登录或者过期
        if(TextUtils.isEmpty(app.getToken())){
            Toast.makeText(getActivity(),"尚未登录或者过期，请重新登陆",Toast.LENGTH_LONG).show();
            return;
        }
        new AlertDialog.Builder(getActivity()).setTitle("注销").
                setNegativeButton("取消", null).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String,Object> params=new HashMap<String, Object>();
                        params.put("token",app.getToken());
                        new MyThread(ACTION_LOGOUT,params).start();
                        loading.show();
                    }
                }).show();
    }

    //修改密码
    void userChangPwdTest(){
        //如果token为空，表示尚未登录或者过期
        if(TextUtils.isEmpty(app.getToken())){
            Toast.makeText(getActivity(),"尚未登录或者过期，请先登陆",Toast.LENGTH_LONG).show();
            return;
        }
        final View layout = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_chang_pwd,null);
        new AlertDialog.Builder(getActivity()).setTitle("修改密码").setView(layout).
                setNegativeButton("取消", null).
                setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etOldPwd = (EditText) layout.findViewById(R.id.et_old_pwd);
                        EditText etNewPwd = (EditText) layout.findViewById(R.id.et_new_pwd);
                        Map<String,Object> params=new HashMap<String, Object>();
                        params.put("oldPwd",etOldPwd.getText().toString());
                        params.put("newPwd",etNewPwd.getText().toString());
                        params.put("token",app.getToken());
                        new MyThread(ACTION_CHANG_PWD,params).start();
                        loading.show();
                    }
                }).show();
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
            switch (code){
                //登录
                case ACTION_LOGIN:
                    resEntity = new UserManager().userLogin((String)params.get("userName"),(String)params.get("userPwd"));
                    //如果登录成功，将token和expire保存到Application中作为全局量
                    if (resEntity.isResState()) {
                        app.setToken((String)resEntity.getResData().get("token"));
                        app.setExpire((Integer) resEntity.getResData().get("expire"));
                    }
                    myHandler.sendEmptyMessage(ACTION_LOGIN);
                    break;
                //注册
                case ACTION_REGISTER:
                    resEntity = new UserManager().userReg((String) params.get("userName"), (String) params.get("userPwd"));
                    myHandler.sendEmptyMessage(ACTION_REGISTER);
                    break;
                //注销
                case ACTION_LOGOUT:
                    resEntity = new UserManager().userLogout((String)params.get("token"));
                    app.setToken("");
                    app.setExpire(0);
                    myHandler.sendEmptyMessage(ACTION_LOGOUT);
                    break;
                //修改密码
                case ACTION_CHANG_PWD:
                    resEntity = new UserManager().changPwd((String) params.get("token"),(String) params.get("oldPwd"), (String) params.get("newPwd"));
                    //如果登录成功，将token和expire保存到Application中作为全局量
                    if (resEntity.isResState()) {
                        app.setToken((String)resEntity.getResData().get("token"));
                        app.setExpire((Integer) resEntity.getResData().get("expire"));
                    }
                    myHandler.sendEmptyMessage(ACTION_CHANG_PWD);
                    break;
            }

        }
    }

}
