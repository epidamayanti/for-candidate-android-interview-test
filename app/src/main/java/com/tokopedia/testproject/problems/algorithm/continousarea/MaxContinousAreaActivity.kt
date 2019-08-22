package com.tokopedia.testproject.problems.algorithm.continousarea

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.tokopedia.testproject.R
import com.tokopedia.testproject.loadFile
import kotlinx.android.synthetic.main.activity_problem.*

class MaxContinousAreaActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem)
        webView.loadFile("max_continuous_area.html");

        var result :Int = 0

        // example of how to call the function
        result = com.tokopedia.testproject.problems.algorithm.maxrectangle.Solution.maxRect(arrayOf(
                intArrayOf(2, 2, 5, 0, 9),
                intArrayOf(1, 3, 5, 2, 3),
                intArrayOf(1, 5, 5, 2, 4),
                intArrayOf(2, 2, 2, 0, 4)))
        Log.d("Result :", result.toString())
        text.setText("Result : "+result)
    }
}


