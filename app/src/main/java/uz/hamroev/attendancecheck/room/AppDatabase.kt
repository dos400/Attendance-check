package uz.hamroev.attendancecheck.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.hamroev.attendancecheck.room.dao.CheckDao
import uz.hamroev.attendancecheck.room.dao.DateDao
import uz.hamroev.attendancecheck.room.dao.StudentDao
import uz.hamroev.attendancecheck.room.entity.Check
import uz.hamroev.attendancecheck.room.entity.Date
import uz.hamroev.attendancecheck.room.entity.Student

@Database(entities = [Student::class, Date::class, Check::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    abstract fun dateDao(): DateDao

    abstract fun checkDao(): CheckDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabaseInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "davomat"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    object GET {
        fun getAppDatabase(): AppDatabase = INSTANCE!!
    }


}