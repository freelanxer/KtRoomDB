package com.freelanxer.ktroomdb

import android.app.Application
import com.freelanxer.ktroomdb.db.AppDatabase

class NoteApplication: Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }
}