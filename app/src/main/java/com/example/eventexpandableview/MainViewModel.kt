package com.example.eventexpandableview

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileReader

class MainViewModel : ViewModel() {

    fun parseJson(json: String) {
        val gson = Gson()
        val eventList: EventMain = gson.fromJson(json, EventMain::class.java)
        eventList.notificationList.forEachIndexed { idx, event ->
            Log.i(
                "Event Tag", event.notificationHeader +
                        " -- " + event.notificationSubZoneName +
                        " -- " + event.notificationTimestamp
            )
        }
        prepareMapForExpandableListView(eventList.notificationList)
    }

    private fun prepareMapForExpandableListView(notificationList: List<Event>) {
        val hm: HashMap<Int, List<Event>> = HashMap()
        val keyOrder = 1
        val duplicateList = notificationList
        notificationList.forEachIndexed { index, event ->
            
        }
    }
}