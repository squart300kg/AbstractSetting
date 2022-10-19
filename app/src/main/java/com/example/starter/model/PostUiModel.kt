package com.example.starter.model

import java.io.Serializable

data class PostUiModel(
    var PROGRAM_ON_OFF: Boolean = true,
    var KAKAO_ROOM_NAME: String = "",
    var MAX_REVEIVED_COUNT: Int = 0,
    var START_RECEVED_HOUR: Int = 0,
    var END_RECEIVED_HOUR: Int = 0,
    var TODAY_RECEIVE_COUNT: Int = 0,
) : Serializable

