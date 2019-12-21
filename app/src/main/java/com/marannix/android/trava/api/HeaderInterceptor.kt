package com.marannix.android.trava.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    /**
     *   Added an interceptor to add the headers necessary for making each request
     */

    //TODO: Find a way to pass in todays date
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val original = chain.request()
        val httpUrl = original.url()
        val newHttpUrl = httpUrl.newBuilder()
            .addQueryParameter("client_id", "SWA00OHBC5NTTITYJ4IIZUD341VN3LC1SVRDYBLA2UTFLMAH")
            .addQueryParameter("client_secret", "JKB42M30XXP2MM45ALJK3XM4UT0XWUNUWAPB4UGS4OWZ5CPD")
            .addQueryParameter("v", "20191221").build()
        proceed(
            request()
                .newBuilder()
                .url(newHttpUrl)
                .build()
        )
    }
}