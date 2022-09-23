package com.rsh.tkachenkoni.apptechdebug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rsh.tkachenkoni.apptechdebug.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mainViewModel : MainViewModel? = null

    private val exampleLooperThread = ExampleLooperThread()
    private val handlerThread: ExampleHandlerThread = ExampleHandlerThread("ExampleHandlerThread",5)
    private var threadHandler: Handler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this)
            .get(MainViewModel::class.java)

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply {
            this.lifecycleOwner = this@MainActivity
            this.viewmodel = mainViewModel
        }

        mainViewModel?.editTextContent?.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        //exampleLooperThread.start()
        handlerThread.start()
        threadHandler = Handler(handlerThread.looper)


        //======
        val handlerThread: HandlerThread = HandlerThread("TestHandlerThread")
        handlerThread.start()
        val looper: Looper = handlerThread.looper
        val handler: Handler = Handler(looper)

        handler.post(Runnable {
            print("MESSAGE TEST 01")
            print("MESSAGE TEST 02")
            print("MESSAGE TEST 03")
            print("MESSAGE TEST 04")
        })


    }

    public fun doWork(view: View){

    }

    public fun removeMessages(view: View){

    }


    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quit()
    }



}