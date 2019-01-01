package com.paopao.newservlet.request;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：paopao on 2018/12/18 18:10
 * <p>
 * 作用:
 */
public class BusStationRequest extends BaseRequest{
    private String busStationId;
    private static BusStationRequest request;
    public static BaseRequest getInstance(){
        if (request == null){
            request = new BusStationRequest();
        }
        return request;
    }

    @Override
    public BaseRequest setParams(String[] params) {
        this.busStationId = params[0];
        return request;
    }

    @Override
    public String getActionName() {
        return "GetBusStationInfo";
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("BusStationId",this.busStationId);
            Log.d("result","busStationId="+busStationId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("result","body.toString()="+body.toString());
        return body.toString();
    }

    @Override
    public Object onParseResponse(String response) {
        ArrayList<Integer> distance = new ArrayList<>();
        try {
            String result = new JSONObject(response).optString("ROWS_DETAIL");
            Log.d("result",result);
            if (result.contains("Distance")){
                JSONArray array = new JSONArray(result);
                for (int i =0;i<array.length();i++){
                    JSONObject boj = (JSONObject) array.get(i);
                    distance.add(boj.getInt("Distance"));
                }
                return distance;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
