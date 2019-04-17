package com.pinkertone.pinkercardsmvp;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Headers originalHeaders = original.headers();

            Request.Builder builder = original.newBuilder().headers(getAuthHeaders(originalHeaders));

            Request request = builder.build();
            return chain.proceed(request);
        }

        static Headers replaceAuthToken(Headers originalHeaders) {
            Headers.Builder headersBuilder = originalHeaders.newBuilder();
            for (String name : originalHeaders.names()) {
                if ("Authorization".equalsIgnoreCase(name)) {
                    headersBuilder.set("Authorization","токен");
                }
            }
            return headersBuilder.build();
        }


        private static Headers getAuthHeaders(Headers originalHeaders) {
            Headers.Builder headersBuilder = originalHeaders.newBuilder();
            headersBuilder.set("Authorization","токен");
            return headersBuilder.build();
        }
}
