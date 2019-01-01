package com.paopao.newservlet.engine;

import com.paopao.newservlet.domain.NewBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：paopao on 2018/12/5 21:32
 * <p>
 * 作用:
 */
public interface Api {

    @GET("News/NewApi")
    Observable<NewBean> getNews(@Query("type")String type);
}
