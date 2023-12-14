package uz.hamroev.attendancecheck.app

import android.app.Application
import uz.hamroev.attendancecheck.room.database.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.getDatabase(this)
    }
}