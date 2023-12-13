package com.freelanxer.ktroomdb

import android.util.Log

class Logger {

    companion object {
        private const val TAG = "KtRoomDB"
        fun logD(msg: String?) = Log.d(TAG, msg ?: "null")
    }

}