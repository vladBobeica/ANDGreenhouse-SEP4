package com.example.greenhouse.utils;

import java.io.IOException;

import okio.Timeout;
import retrofit2.Call;
import retrofit2.Response;

public class RetrofitUtils {
    public static <T> Call<T> createSuccessCall(T data) {
        return new Call<T>() {
            @Override
            public Response<T> execute() throws IOException {
                return Response.success(data);
            }

            @Override
            public void enqueue(retrofit2.Callback<T> callback) {
                try {
                    callback.onResponse(this, execute());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {
                // Do nothing for mock implementation
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<T> clone() {
                return this; // Do nothing for mock implementation
            }

            @Override
            public okhttp3.Request request() {
                return null; // Do nothing for mock implementation
            }

            @Override
            public Timeout timeout() {
                return null;
            }
        };
    }

    public static <T> Call<T> createErrorCall(int errorCode, Throwable error) {
        return new Call<T>() {
            @Override
            public Response<T> execute() throws IOException {
                return Response.error(errorCode, okhttp3.ResponseBody.create(null, new byte[0]));
            }

            @Override
            public void enqueue(retrofit2.Callback<T> callback) {
                callback.onFailure(this, new IOException("Request failed with error code: " + errorCode));
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {
                // Do nothing for mock implementation
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<T> clone() {
                return this; // Do nothing for mock implementation
            }

            @Override
            public okhttp3.Request request() {
                return null; // Do nothing for mock implementation
            }

            @Override
            public Timeout timeout() {
                return null;
            }
        };
    }
}
