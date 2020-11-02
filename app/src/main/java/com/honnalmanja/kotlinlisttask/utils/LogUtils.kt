package com.honnalmanja.kotlinlisttask.utils

import android.util.Log
import com.honnalmanja.kotlinlisttask.BuildConfig

class LogUtils {

    companion object{

        fun logD(TAG: String, message: String) {
            if(BuildConfig.DEBUG){
                Log.d(TAG, message)
            }
        }

        fun logI(TAG: String, message: String) {
            if(BuildConfig.DEBUG){
                Log.i(TAG, message)
            }
        }

        fun logE(TAG: String, message: String) {
            if(BuildConfig.DEBUG){
                Log.e(TAG, message)
            }
        }

    }

}