package net.gocourse.api.util;

import android.util.Log;

import net.gocourse.api.bean.HttpResEntity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Created by 徐灿 on 2015/5/4
 * Contact 384454501@qq.com
 * Introduce 网络请求处理组件
 */
public class HttpReqUtil {
    //默认超时
    private final static int defTimeOut=3*1000;
    //默认字符集
    private final static String defCharSetName= "utf-8";
    //操作结果
    private static HttpResEntity resEntity=new HttpResEntity();

    /**
     *处理get请求
     * @param url
     * @param charSetName
     * @param timeOut
     * @return HttpResEntity
     */
    public static HttpResEntity doGet(String url,String charSetName,int timeOut){
        HttpGet httpGet=new HttpGet(url);
        //添加字符集
        httpGet.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,charSetName);

        //设置请求超时
        httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,timeOut);

        try{
            //连接请求
            HttpResponse httpResponse=new DefaultHttpClient().execute(httpGet);
            //连接响应码
            if (httpResponse.getStatusLine().getStatusCode()==200){
                //请求操作成功
                resEntity.setReqState(true);
                //获取请求内容
                resEntity.setReqInfo(EntityUtils.toString(httpResponse.getEntity()));
            }else{
                //请求操作失败
                resEntity.setReqState(false);
                //失败内容
                resEntity.setErrInfo("连接状态码："+httpResponse.getStatusLine().getStatusCode());
            }
        }catch (Exception e){
            //请求操作失败
            resEntity.setReqState(false);
            //失败内容
            resEntity.setErrInfo(e.toString());
            //输出日志
            Log.e("HttpReqUtil/doGet",e.toString());
        }
        return resEntity;
    }
    //重构doGet函数，使用默认超时和字符集
    public static HttpResEntity doGet(String url){
        return doGet(url,defCharSetName,defTimeOut);
    }

    /**
     * 处理post请求
     * @param url
     * @param charSetName
     * @param timeOut
     * @return HttpResEntity
     */
    public static HttpResEntity doPost(String url,Map<String,Object> map,String charSetName,int timeOut){
        HttpPost httpPost=new HttpPost(url);
        //请求参数
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        for(Map.Entry<String,Object> entry : map.entrySet()){
            params.add(new BasicNameValuePair(entry.getKey(),String.valueOf(entry.getValue())));
        }
        try{
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            //设置超时
            httpPost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,timeOut);
            //执行请求
            HttpResponse httpResponse=new DefaultHttpClient().execute(httpPost);
            //请求状态码
            if (httpResponse.getStatusLine().getStatusCode()==200){
                //请求操作成功
                resEntity.setReqState(true);
                //获取请求内容
                resEntity.setReqInfo(EntityUtils.toString(httpResponse.getEntity()));
            }else {
                //请求操作失败
                resEntity.setReqState(false);
                //失败内容
                resEntity.setErrInfo("连接状态码："+httpResponse.getStatusLine().getStatusCode());
            }
        }catch (Exception e){
            //请求操作失败
            resEntity.setReqState(false);
            //失败内容
            resEntity.setErrInfo(e.toString());
            Log.e("HttpReqUtil/doPost",e.toString());
        }
        return resEntity;
    }

    //重构doPost函数，使用默认超时和字符集
    public static HttpResEntity doPost(String url,Map<String,Object> params){
        return doPost(url,params,defCharSetName,defTimeOut);
    }
}