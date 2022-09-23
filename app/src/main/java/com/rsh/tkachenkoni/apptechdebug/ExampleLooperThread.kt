package com.rsh.tkachenkoni.apptechdebug

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log


/**
 *
 * Created by Nikolay Tkachenko
 * E-Mail: tkachenni@mail.ru
 */
class ExampleLooperThread : Thread() {
    private val TAG = "ExampleLooperThread"

    private var handler: Handler? = null

    override fun run() {
        Looper.prepare()

        handler = Handler()

        Looper.loop()

        for (i in 1..5){
            Log.d(TAG, "run = " + i)
            SystemClock.sleep(1000)
        }
        Log.d(TAG, "End of run()")
    }


}