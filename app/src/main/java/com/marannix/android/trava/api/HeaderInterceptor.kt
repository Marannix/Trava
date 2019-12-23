package com.marannix.android.trava.api

import okhttp3.Interceptor
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.*

class HeaderInterceptor : Interceptor {

    /**
     *   Added an interceptor to add the headers necessary for making each request
     */
    
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val original = chain.request()
        val httpUrl = original.url()
        val date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
        val newHttpUrl = httpUrl.newBuilder()
            .addQueryParameter("client_id", "SWA00OHBC5NTTITYJ4IIZUD341VN3LC1SVRDYBLA2UTFLMAH")
            .addQueryParameter("client_secret", "JKB42M30XXP2MM45ALJK3XM4UT0XWUNUWAPB4UGS4OWZ5CPD")
            .addQueryParameter("v", date).build()
        proceed(
            request()
                .newBuilder()
                .url(newHttpUrl)
                .build()
        )
    }
}