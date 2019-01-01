package com.paopao.newservlet.request;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：paopao on 2018/12/31 15:17
 * <p>
 * 作用: 获取轮播图详细信息
 */
public class GetRotationChartInfoRequest extends BaseRequest{

    private String rotation_chart_info_id;
    @Override
    public BaseRequest setParams(String[] params) {
        this.rotation_chart_info_id=params[0];
        return this;
    }

    @Override
    public String getActionName() {
        return "GetRotationChartInfo";
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("rotation_chart_info_id",this.rotation_chart_info_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return body.toString();
    }

    @Override
    public Object onParseResponse(String response) {
        try {
        String reason=new JSONObject(response).optString("reason");
        if(reason.equals("success")){

                JSONArray jsonArray=new JSONObject(response).optJSONArray("data");
                JSONObject jsonObject= (JSONObject) jsonArray.get(0);
                return jsonObject.optString("url");

        }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
