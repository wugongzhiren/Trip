package com.wufang.trip.retrofit;

import com.wufang.trip.bean.Site;
import com.wufang.trip.bean.Strategy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/3/18.
 */

public interface StrategyService {
    @GET("strategy")
    Call<List<Strategy>> getStrategy(
    );
}
