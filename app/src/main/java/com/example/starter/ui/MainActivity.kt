package com.example.starter.ui

import android.os.Bundle
import android.widget.Toast
import com.example.starter.R
import com.example.starter.base.BaseActivity
import com.example.starter.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this);

        binding {
            mainVm = mainViewModel

            tvSettingComplete.setOnClickListener {
                mainViewModel.modifySetting(
                    onOff = etOnOff.isChecked,
                    kakaoRoomName = etKakaoRoomName.text.toString(),
                    maxReceiveCount = etMaxReceiveCount.text.toString().toInt(),
                    startReceiveHour = etStartReceiveHour.text.toString().toInt(),
                    endReceiveHour = etEndReceiveHour.text.toString().toInt(),
                    todayReceiveCount = etTodayReceveCount.text.toString().toInt(),
                    {
                        Toast.makeText(this@MainActivity, "커밋 성공\n" +
                                "onOff : ${etOnOff.isChecked}\n" +
                                "kakaoRoomName : ${etKakaoRoomName.text}\n" +
                                "maxReceiveCount : ${etMaxReceiveCount.text}\n" +
                                "startReceiveHour : ${etStartReceiveHour.text}\n" +
                                "endReceiveHour : ${etEndReceiveHour.text}\n" , Toast.LENGTH_LONG).show()
                    },
                    {
                        Toast.makeText(this@MainActivity, "커밋 실패\n" +
                                it.message, Toast.LENGTH_LONG).show()
                    })
            }
        }

        mainViewModel.getSetting()

    }


    override fun onResume() {
        super.onResume()
        mainViewModel.getSetting()

    }
}