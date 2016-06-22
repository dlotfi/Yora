package com.example.yora.infrastructure;

import android.util.Log;

import com.squareup.otto.Bus;

import retrofit2.Retrofit;

public class RetrofitCallbackPost<T extends ServiceResponse> extends RetrofitCallback<T> {
    private static final String TAG = "RetrofitCallbackPost";

    private final Bus _bus;

    public RetrofitCallbackPost(Class<T> resultType, Retrofit retrofit, Bus bus) {
        super(resultType, retrofit);
        _bus = bus;
    }

    @Override
    protected void onResponse(T t) {
        if (t == null) {
            try {
                t = resultType.newInstance();
            } catch (Exception e) {
                Log.e(TAG, "Could not create blank result object", e);
            }
        }

        _bus.post(t);
    }
}
