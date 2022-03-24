package com.example.eventexpandableview

import java.util.*

data class ChildEvent(
    val notificationId: String,
    val notificationHeader: String,
    val notificationSubZoneName: String,
    val notificationTimestamp: Date
)
