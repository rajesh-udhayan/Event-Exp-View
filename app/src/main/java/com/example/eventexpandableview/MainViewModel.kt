package com.example.eventexpandableview

import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel : ViewModel() {

    fun prepareMapForExpandableListView(json: String): MutableList<ParentEvent> {
        val gson = Gson()
        val eventList: EventMain = gson.fromJson(json, EventMain::class.java)
        val notificationList = eventList.notificationList.toMutableList()
        val parentEventList: MutableList<ParentEvent> = mutableListOf()
        val duplicateList = notificationList.toMutableList()
        notificationList.forEachIndexed { index, event ->
            val id: String = event.notificationId
            val header: String = event.notificationHeader
            val subZone: String = event.notificationSubZoneName
            val timeStamp: String = event.notificationTimestamp
            val childEventList: MutableList<Event> = mutableListOf()

            for (i in index + 1 until notificationList.size) {
                val event1 = notificationList[i]
                val duplicateEvent1: Event? =
                    duplicateList.find { it.notificationId.equals(event1.notificationId) }
                duplicateEvent1?.let {
                    val prevEventTime = DateUtils.getDate(event.notificationTimestamp).time
                    val currEventTime = DateUtils.getDate(event1.notificationTimestamp).time
                    val diff = 2 * 60 * 1000
                    if (header.equals(event1.notificationHeader) && (prevEventTime - currEventTime) <= diff) {
                        childEventList.add(event1)
                        duplicateList.remove(event1)
                    }
                }
            }
            val duplicateEvent: Event? = duplicateList.find { it.notificationId.equals(id) }
            duplicateEvent?.let {
                var bgColor : String = ""
                if(parentEventList.size % 2 == 0){
                    bgColor = "#ffffff"
                }else{
                    bgColor = "#e9e9e9"
                }
                val parentEvent =
                    ParentEvent(id, header, subZone, timeStamp, childList = childEventList,background = bgColor)
                parentEventList.add(parentEvent)
                duplicateList.remove(event)
            }
        }
        return parentEventList
    }
}