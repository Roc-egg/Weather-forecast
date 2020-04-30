package com.hp.weatherforecast.logic.network

import com.google.gson.GsonBuilder
import com.hp.weatherforecast.BuildConfig
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * 项目：    WeatherForecast
 * 类描述：  retrofit构建器
 * 创建人：  hp
 * 创建时间：2020/4/15 18:03
 * 修改人：  hp
 * 修改时间：2020/4/15 18:03
 * 修改备注：
 */
object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"


    private val retrofit = Retrofit.Builder()
        .client(initOkHttp())
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create()
            )
        )
        .baseUrl(BASE_URL)
        .build()

    /**
     * 配置http请求的打印日志相关
     *
     * @return
     */
    private fun initOkHttp(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder().connectTimeout(3 * 1000, TimeUnit.SECONDS)
        //打印网络请求日志
        httpClientBuilder.addInterceptor(LoggerInterceptor());
//        httpClientBuilder.addInterceptor(Interceptor { chain: Interceptor.Chain? ->
//            addAuthIntercepter(
//                chain
//            )
//        }) //添加认证authIntercepter,验证调用者身份信息

        return httpClientBuilder.build()
    }

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}