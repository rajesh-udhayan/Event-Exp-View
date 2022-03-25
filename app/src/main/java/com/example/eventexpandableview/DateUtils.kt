package com.example.eventexpandableview

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {

    fun getDate(date: String): Date {

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        parser.timeZone = TimeZone.getTimeZone("UTC")
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm a")
        formatter.timeZone = TimeZone.getDefault()

        var result: Date = Date()
        try {
            val output = formatter.format(parser.parse(date))
            result = formatter.parse(output)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return result
    }

    fun getDateString(date:String) : String{
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        parser.timeZone = TimeZone.getTimeZone("UTC")
        val formatter = SimpleDateFormat("MM-dd-yy")
        formatter.timeZone = TimeZone.getDefault()

        var result: String = ""
        try {
            result = formatter.format(parser.parse(date))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return  result
    }

    fun getTimeString(date:String) : String{
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        parser.timeZone = TimeZone.getTimeZone("UTC")
        val formatter = SimpleDateFormat("hh:mm a")
        formatter.timeZone = TimeZone.getDefault()

        var result: String = ""
        try {
            result = formatter.format(parser.parse(date))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return  result.lowercase()
    }
}