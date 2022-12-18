package com.example.starter.ui.firstTab

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.starter.R
import com.example.starter.base.BaseFragment
import com.example.starter.databinding.FirstTabFragmentBinding
import kotlinx.coroutines.*
import java.util.*

class Solution {

    private val MIN_COORDINATE_VALUE = 1
    private val MAX_COORDINATE_VALUE = 1000000000

    private val INPUT_COORDINATE_COUNT = 3

    private val X = 0
    private val Y = 1

    fun solution(tripleCoordinate: Array<IntArray>): IntArray {
        require(tripleCoordinate.size == INPUT_COORDINATE_COUNT)

        val noDulplicatedXCoordinateSet = mutableSetOf<Int>()
        val noDulplicatedYCoordinateSet = mutableSetOf<Int>()


        tripleCoordinate.forEachIndexed { index, coordinate ->
            require(coordinate.all { it in MIN_COORDINATE_VALUE..MAX_COORDINATE_VALUE })

            if (!noDulplicatedXCoordinateSet.add(coordinate[X])) {
                noDulplicatedXCoordinateSet.remove(coordinate[X])
            }
            if (!noDulplicatedYCoordinateSet.add(coordinate[Y])) {
                noDulplicatedYCoordinateSet.remove(coordinate[Y])
            }

        }

        return noDulplicatedXCoordinateSet.toIntArray() + noDulplicatedYCoordinateSet.toIntArray()
    }

}
class FirstTabFragment : BaseFragment<FirstTabFragmentBinding>(R.layout.first_tab_fragment) {

    private val firstTabViewModel: FirstTabViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("bottomId", "First Loaded")

        val result = Solution().solution(arrayOf(intArrayOf(1,4), intArrayOf(3,4), intArrayOf(3,10)))
        binding.tvTitle.text = "${result[0]}, ${result[1]}"
        Log.i("result : " ,"$result")
    }

}