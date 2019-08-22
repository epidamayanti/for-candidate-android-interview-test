package com.tokopedia.testproject.problems.algorithm.maxrectangle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.tokopedia.testproject.R
import com.tokopedia.testproject.loadFile
import kotlinx.android.synthetic.main.activity_problem.*




class MaxRectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem)
        webView.loadFile("max_rect.html")

        var result :Int = 0

        // example of how to call the function
        result = Solution.maxRect(arrayOf(
                intArrayOf(0, 0, 1, 0, 1),
                intArrayOf(0, 0, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1),
                intArrayOf(0, 1, 1, 1, 1),
                intArrayOf(1, 0, 0, 0, 1)))
        Log.d("Result :", result.toString())
        text.setText("Result : "+result)



    }

}