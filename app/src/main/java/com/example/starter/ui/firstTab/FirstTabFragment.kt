package com.example.starter.ui.firstTab

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.starter.R
import com.example.starter.base.BaseFragment
import com.example.starter.databinding.FirstTabFragmentBinding

class Solution2 {

    val MIN_INPUT_NUMBER_COUNT = 2
    val MAX_INPUT_NUMBER_COUNT = 20

    val MIN_INPUT_NUMBER = 1
    val MAX_INPUT_NUMBER = 50

    val MIN_TARGET_NUMBER = 1
    val MAX_TARGET_NUMBER = 1000

    var matchedTargetCount = 0

    private fun checkArgsValidation(numbers: IntArray, target: Int) {
        require(numbers.size in MIN_INPUT_NUMBER_COUNT..MAX_INPUT_NUMBER_COUNT)
        numbers.forEach { number ->
            require(number in MIN_INPUT_NUMBER..MAX_INPUT_NUMBER)
        }
        require(target in MIN_TARGET_NUMBER..MAX_TARGET_NUMBER)
    }

    fun solution(numbers: IntArray, target: Int): Int {

        checkArgsValidation(numbers, target)

        getResultFromDSF(numbers = numbers, target = target)

        return matchedTargetCount
    }


    private fun getResultFromDSF(numbers: IntArray,
                                 target: Int,
                                 incompleteTarget: Int = 0,
                                 depthIndex: Int = 0
    ) {
        Log.i("DSF : " ,"depthIndex : $depthIndex")
        Log.i("DSF : " ,"incompleteTarget : $incompleteTarget")

        if (depthIndex == numbers.size) {
            if (incompleteTarget == target) {
                matchedTargetCount++
                Log.i("DSF : " ,"matchedTargetCount : $matchedTargetCount")
            }
            return
        }

        getResultFromDSF(numbers, target, incompleteTarget + numbers[depthIndex], depthIndex + 1)
        getResultFromDSF(numbers, target, incompleteTarget - numbers[depthIndex], depthIndex + 1)
    }
}

class Solution {

    private val MIN_COORDINATE_VALUE = 1
    private val MAX_COORDINATE_VALUE = 1000000000

    private val INPUT_COORDINATE_COUNT = 3

    private val X = 0
    private val Y = 1

    fun solution(tripleCoordinate: Array<IntArray>): IntArray {
        require(tripleCoordinate.size == INPUT_COORDINATE_COUNT)

        val noDulplicatedXCoordinateSet by lazy { mutableSetOf<Int>() }
        val noDulplicatedYCoordinateSet by lazy { mutableSetOf<Int>() }


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

    fun solution2(input: Int): String {
        var result = ""

        repeat(input) { rowIndex ->
            result += "*".repeat(rowIndex + 1).plus("\n")
        }

        return result
    }

}
class FirstTabFragment : BaseFragment<FirstTabFragmentBinding>(R.layout.first_tab_fragment) {

    private val firstTabViewModel: FirstTabViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("bottomId", "First Loaded")

//        val result = Solution().solution(arrayOf(intArrayOf(1,4), intArrayOf(3,4), intArrayOf(3,10)))
//        binding.tvTitle.text = "${result[0]}, ${result[1]}"
//        val result = Solution().solution2(12)
        val result = Solution2().solution(intArrayOf(1,1,1,1,1),3)
        Log.i("DSF : " ,"$result")
//        binding.tvTitle.text = "${result}"
    }

}