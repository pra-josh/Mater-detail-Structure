package com.pra.myapplication.UI.Util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateCustomUtils {


   companion object {
       fun getDateFromStringForInvoice(
           date: String?,
       ): String {
           val sdf = SimpleDateFormat("EEE, dd MMM yyyy HH:MM:SS z", Locale.US)
           sdf.timeZone = TimeZone.getTimeZone("UTC")
           var d: Date? = null
           var formattedTime = ""
           try {
               val dateFormat = SimpleDateFormat("EEE, dd-MM-yyyy hh:mm:ss", Locale.US)
               //  dateFormat.setTimeZone(TimeZone.getTimeZone(FlourishApp.getInstance().mSelectFacilityTimeZone));
               d = sdf.parse(date)
               formattedTime = dateFormat.format(d)
           } catch (e: ParseException) {
               e.printStackTrace()
           }
           return formattedTime
       }
   }


}