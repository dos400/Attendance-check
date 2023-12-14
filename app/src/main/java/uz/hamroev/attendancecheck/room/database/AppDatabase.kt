package uz.hamroev.attendancecheck.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.hamroev.attendancecheck.room.dao.CheckDao
import uz.hamroev.attendancecheck.room.dao.DataDao
import uz.hamroev.attendancecheck.room.dao.StudentDao
import uz.hamroev.attendancecheck.room.entity.Check
import uz.hamroev.attendancecheck.room.entity.Data
import uz.hamroev.attendancecheck.room.entity.Student

@Database(entities = [Student::class, Data::class, Check::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun dataDao(): DataDao
    abstract fun checkDao(): CheckDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "attendance"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

    object GET {
        fun getAppDatabase(): AppDatabase = INSTANCE!!
    }

}
