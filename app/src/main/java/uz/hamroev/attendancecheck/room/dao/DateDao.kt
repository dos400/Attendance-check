package uz.hamroev.attendancecheck.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.hamroev.attendancecheck.room.entity.Date

@Dao
interface DateDao {

    @Insert
    fun insertDate(date: Date)

    //read users
    @Query("SELECT * FROM date")
    fun getAllDates(): List<Date>

    //update user
    @Update
    fun updateDate(date: Date)

    //delete user
    @Delete
    fun deleteDate(date: Date)

    //deleteAll
    @Query("DELETE FROM date")
    fun deleteAll()

}