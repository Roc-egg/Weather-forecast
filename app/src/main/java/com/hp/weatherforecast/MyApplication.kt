package com.hp.weatherforecast

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.LogUtils

/**
 * 项目：    WeatherForecast
 * 类描述：  应用程序入口
 * 创建人：  hp
 * 创建时间：2020/4/14 14:31
 * 修改人：  hp
 * 修改时间：2020/4/14 14:31
 * 修改备注：
 */
class MyApplication : Application() {
    companion object {
        lateinit var context: Context

        /**
         * 彩云天气的TOKEN令牌
         */
        const val TOKEN = "vDmxv992G9JY4Uuh"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        LogUtils.getConfig()
            //设置 log 总开关
            .setLogSwitch(BuildConfig.DEBUG)
            //设置 log 边框开关
            .setBorderSwitch(false)
    }
}