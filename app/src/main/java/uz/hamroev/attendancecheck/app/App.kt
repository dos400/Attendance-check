package uz.hamroev.attendancecheck.app

import android.app.Application
import uz.hamroev.attendancecheck.cache.Cache
import uz.hamroev.attendancecheck.room.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.getDatabaseInstance(this)
        Cache.init(this)
    }
}