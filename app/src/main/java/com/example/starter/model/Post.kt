package com.example.starter.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Post(
    var onOff: Boolean = true,
    var kakaoRoomName: String = "",
    var maxReceiveCount: Int = 0,
    var startReceiveHour: Int = 0,
    var endReceiveHour: Int = 0,
    var todayReceiveCount: Int = 0,
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "PROGRAM_ON_OFF" to onOff,
            "KAKAO_ROOM_NAME" to kakaoRoomName,
            "MAX_REVEIVED_COUNT" to maxReceiveCount,
            "START_RECEVED_HOUR" to startReceiveHour,
            "END_RECEIVED_HOUR" to endReceiveHour,
            "TODAY_RECEIVE_COUNT" to todayReceiveCount
        )
    }
}