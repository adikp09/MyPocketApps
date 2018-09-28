package com.dev.adi.collectapps

object Network {
    private const val API_KEY = "32473132698ffee5229f49be6155ddf6"
    private const val API_LINK = "http://api.openweathermap.org/data/2.5/weather"
    private const val API_LINK_ICON = "http://api.openweathermap.org/img/w/"
    private const val API_FORCAST_LINK = "http://api.openweathermap.org/data/2.5/forecast"
///    private const val API_FORCAST_LINK = "http://api.openweathermap.org/data/2.5/forecast?units=metric&id=1621177&appid=32473132698ffee5229f49be6155ddf6"

    fun apiRequest (): String {
        val sb = StringBuilder(API_LINK)
        sb.append("?units=metric&q=Yogyakarta&appid=$API_KEY")
        return sb.toString()
    }

    fun apiForcastRequest (): String {
        val sb = StringBuilder(API_FORCAST_LINK)
        sb.append("?units=metric&id=1621177&appid=$API_KEY")
        return sb.toString()
    }

    fun getIcon ( icon : String ): String {
        return API_LINK_ICON+{icon}+".png"
    }
}