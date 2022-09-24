package com.rsh.tkachenkoni.apptechdebug

import android.os.*
import android.util.Log

/**
 *
 * Created by Nikolay Tkachenko
 * E-Mail: tkachenni@mail.ru
 */
class ExampleHandlerThread(): HandlerThread("ExampleHandlerThread", Process.THREAD_PRIORITY_BACKGROUND) {

    private val TAG = "ExampleHandlerThread"

    private var handler: Handler? = null

    override fun onLooperPrepared() {
        handler = Handler(this.looper){msg ->
            when (msg.what){
                1 -> {
                    print("11111111111111111")
                    Log.d("TEST_TEST", TAG + " 1111111111111111111 ")
                    true
                }
                2 -> {
                    print("22222222222222222")
                    Log.d("TEST_TEST", TAG + " 22222222222222222 ")
                    true
                }
                else -> {
                    Log.d("TEST_TEST", TAG + " else ")
                    print("else")
                    false
                }
            }

        }
    }



//    override fun run() {
//        Log.d("TEST_TEST", TAG + " started ")
////        Looper.prepare()
////
////        handler = Handler(this.looper)
////
////        Looper.loop()
//
//        for (i in 1..10){
//            Log.d("TEST_TEST", TAG + " run: " + i)
//            SystemClock.sleep(1000)
//        }
//        Log.d("TEST_TEST", TAG + " ended ")
//    }

    public fun getHandler() : Handler? {
        return handler
    }

}