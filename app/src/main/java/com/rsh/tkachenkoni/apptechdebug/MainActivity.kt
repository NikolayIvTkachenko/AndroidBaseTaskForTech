package com.rsh.tkachenkoni.apptechdebug

import android.os.*
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rsh.tkachenkoni.apptechdebug.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val TAG2 = "TEST_TEST"

    private var mainViewModel : MainViewModel? = null

    private val exampleLooperThread = ExampleLooperThread()
    private val handlerThread: ExampleHandlerThreadV2 = ExampleHandlerThreadV2("ExampleHandlerThread",5)
    private var threadHandler: Handler? = null


    private val handlerThreadV2 = HandlerThread("HandlerThreadV2")
    private var threadHandlerV2: Handler? = null

    private val token = Object()
    private val runnable01 = ExampleTestRunnable01()

    private val exampleHandlerThread: ExampleHandlerThread = ExampleHandlerThread()

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
            Log.d("TEST_TEST", "MESSAGE TEST 01")
            print("MESSAGE TEST 02")
            Log.d("TEST_TEST", "MESSAGE TEST 02")
            print("MESSAGE TEST 03")
            Log.d("TEST_TEST", "MESSAGE TEST 03")
            print("MESSAGE TEST 04")
            Log.d("TEST_TEST", "MESSAGE TEST 04")
        })

        handlerStartVersion02()


        handlerThreadV2.start()
        threadHandlerV2 = Handler(handlerThreadV2.looper)

        exampleHandlerThread.start()
    }

    public fun doWork(view: View){
        //===
        //threadHandlerV2?.postDelayed(ExampleTestRunnable01(), 1500)
        //threadHandlerV2?.postAtTime(ExampleTestRunnable01(), 1500)
        //===
        threadHandlerV2?.post(ExampleTestRunnable01())
        threadHandlerV2?.post(ExampleTestRunnable01())
        threadHandlerV2?.postAtFrontOfQueue(ExampleTestRunnable02())
        //===
    }

    public fun doWork02(view: View){
        var msg = Message.obtain()
        msg.what = 1
        msg.arg1 = 23
        msg.arg2 = 44
        msg.obj = "Message String Object"

        exampleHandlerThread.getHandler()?.sendMessage(msg)

        //exampleHandlerThread.getHandler()?.sendEmptyMessage(1)



        //exampleHandlerThread.getHandler()?.post(ExampleTestRunnable01())
        //exampleHandlerThread.getHandler()?.post(ExampleTestRunnable01())
        //exampleHandlerThread.getHandler()?.postAtFrontOfQueue(ExampleTestRunnable02())
    }

    public fun doWork03(view: View){
        var msg = Message.obtain(exampleHandlerThread.getHandler())
        msg.what = 2
        msg.arg1 = 23
        msg.arg2 = 44
        msg.obj = "Message String Object"
        //msg.data

        msg.sendToTarget()
    }

    public fun doWork04(view: View){
//        exampleHandlerThread.getHandler()?.post(ExampleTestRunnable01())
//        exampleHandlerThread.getHandler()?.post(ExampleTestRunnable01())
//        exampleHandlerThread.getHandler()?.postAtFrontOfQueue(ExampleTestRunnable02())

        var msg = Message.obtain(exampleHandlerThread.getHandler())
        msg.what = 4
        msg.arg1 = 23
        msg.arg2 = 44
        msg.obj = "Message String Object"
        //msg.data
        msg.sendToTarget()

        exampleHandlerThread.getHandler()?.postAtTime(runnable01, token, 5000)
        exampleHandlerThread.getHandler()?.post(runnable01)
    }


    public fun removeMessages(view: View){
        //exampleHandlerThread.getHandler()?.removeMessages(3)
        //exampleHandlerThread.getHandler()?.removeCallbacksAndMessages(null)
        exampleHandlerThread.getHandler()?.removeCallbacks(runnable01, token)

    }

    var mainHandler: Handler? = null
    private fun handlerStartVersion02(){
        mainHandler = Handler(this.getMainLooper())

        val myRunnable = Runnable {
            Log.d("TEST_TEST", "adsadsadasdasdasdads")
        } // This is your code
        mainHandler?.post(myRunnable)
    }


    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quit()
        handlerThreadV2.quit()
        exampleHandlerThread.quit()
    }


    inner class ExampleTestRunnable01: Runnable {
        override fun run() {
            for(i in 1..5){
                Log.d(TAG2, "Runnable 01 = $i")
                SystemClock.sleep(1000)
            }
        }
    }

    inner class ExampleTestRunnable02: Runnable {
        override fun run() {
            for(i in 1..5){
                Log.d(TAG2, "Runnable 02 = $i")
                SystemClock.sleep(1000)
            }
        }
    }

}