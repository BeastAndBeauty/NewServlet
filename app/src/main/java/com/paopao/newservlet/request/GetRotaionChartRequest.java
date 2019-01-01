package com.paopao.newservlet.request;

import android.util.Log;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：paopao on 2018/12/30 22:00
 * <p>
 * 作用:获取轮播图 图片
 */
public class GetRotaionChartRequest extends BaseRequest{

    @Override
    public BaseRequest setParams(String[] params) {
        return this;
    }

    @Override
    public String getActionName() {
        return "GetRotationChartPicture";
    }

    @Override
    public String getRequestBody() {
        return null;
    }

    @Override
    public Object onParseResponse(String response) {
        try {
            String reason = new JSONObject(response).optString("reason");
            if(reason.equals("success")){
                List<String> list=new ArrayList<>();
                JSONArray jsonArray=new JSONObject(response).optJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                    list.add(jsonObject.optString("picture_url"));
                    Log.d("hsh","jsonObject.optString(\"picture_url\")="+jsonObject.optString("picture_url"));

                }
                return list;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
