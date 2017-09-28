package com.example.lin.rx.img.net;

import com.example.lin.rx.img.ImageItemVo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lin on 17/9/27.
 */

public interface ImageApi {
    @GET("search")
    Observable<List<ImageItemVo>> search(@Query("q") String query);
}
