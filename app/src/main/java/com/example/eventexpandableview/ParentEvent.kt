package com.example.eventexpandableview

import java.util.*
import kotlin.collections.ArrayList

data class ParentEvent(
    val notificationHeader: String? = null,
    val notificationSubZoneName: String? = null,
    val notificationTimestamp: String? = null,
    var type: Int = Constants.PARENT,
    var childList: MutableList<Event> = ArrayList(),
    var isExpanded: Boolean = false,
    var background:String = "#ffffff"
)

