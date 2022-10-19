package com.example.starter.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starter.model.Post
import com.example.starter.model.PostUiModel
import com.google.firebase.database.FirebaseDatabase

class MainViewModel : ViewModel() {

    private val _controllPost = MutableLiveData<PostUiModel>()
    val controllPost: LiveData<PostUiModel>
        get() = _controllPost

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun getSetting() {
        database
            .getReference("controll")
            .get()
            .addOnSuccessListener {
                _controllPost.value = it.getValue(PostUiModel::class.java)
                Log.i("controllValue : ", "${controllPost.value}")
                Log.i("controllValue : ", "${it.toString()}")

            }
    }

    fun modifySetting(
        onOff: Boolean,
        kakaoRoomName: String,
        maxReceiveCount: Int,
        startReceiveHour: Int,
        endReceiveHour: Int,
        todayReceiveCount: Int,
        onSuccess  : () -> Unit,
        onFail: (Exception) -> Unit

    ) {
        database.reference.child("controll").push().key ?: return

        val post = Post(
            onOff = onOff,
            kakaoRoomName = kakaoRoomName,
            maxReceiveCount = maxReceiveCount,
            startReceiveHour = startReceiveHour,
            endReceiveHour = endReceiveHour,
            todayReceiveCount = todayReceiveCount,
        )
        val postValues = post.toMap()

        val childUpdates = hashMapOf<String, Any>(
            "/controll" to postValues,
        )

        database.reference.updateChildren(childUpdates)
            .addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener {
                onFail(it)
            }
    }
}