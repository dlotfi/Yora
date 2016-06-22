package com.example.yora.infrastructure;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public abstract class RetrofitCallback<T extends ServiceResponse> implements Callback<T> {
    private static final String TAG = "RetrofitCallback";

    protected final Class<T> resultType;
    protected final Retrofit retrofit;

    public RetrofitCallback(Class<T> resultType, Retrofit retrofit) {
        this.resultType = resultType;
        this.retrofit = retrofit;
    }

    protected abstract void onResponse(T t);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onResponse(response.body());
        } else {
            Log.e(TAG, "Error " + String.valueOf(response.code())+ " : " + response.raw().message());

            try {
                Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(resultType, new Annotation[0]);
                onResponse(converter.convert(response.errorBody()));
            } catch (Exception e) {
                Log.e(TAG, "Error " + String.valueOf(response.code())+ " : " + response.raw().message());
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e(TAG, "Error sending request with " + resultType.getName() + " response", t);

        ServiceResponse errorResult;
        try {
            errorResult = resultType.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating result type " + resultType.getName(), e);
        }

        if (t instanceof IOException) {
            errorResult.setCriticalError("Unable to connect to Yora servers!");
        } else {
            errorResult.setCriticalError("Unknown error. Please try again.");
        }

        onResponse((T) errorResult);
    }
}
