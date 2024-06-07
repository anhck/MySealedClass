package com.adn.mysealedclass

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.MutableLiveData

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mTvState = findViewById(R.id.tvState)
        progress = Progress.Loading(0)
        handler.post(runnable)

        liveData.observe(this) {
            mTvState?.text = it?.state
        }
    }
    private var liveData = MutableLiveData<Progress>()

    private var mTvState: TextView? = null
    private var progress: Progress? = null
    private var handler = Handler()
    private var count = 0
    private val runnable = object :Runnable {
        override fun run() {
            ++count
            if (count == 10) {
                progress = Progress.End
            } else {
                progress = Progress.Loading(count)
            }

            liveData.value = progress

            if (count < 10) {
                handler.postDelayed(this, 500)
            }
        }
    }

    sealed class Progress(val state: String) {
        data object Start : Progress("Start")
        data class Loading(var count: Int) : Progress(count.toString())
        data object End: Progress("End")
    }

}