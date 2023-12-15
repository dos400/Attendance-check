package uz.hamroev.attendancecheck.cache

import android.content.Context
import android.content.SharedPreferences

object Cache {

    private const val NAME = "attendance"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var date: String?
        get() = sharedPreferences.getString("sana", "")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("sana", value)
            }
        }


    var date_id: Long?
        get() = sharedPreferences.getLong("dateid", 1)
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putLong("dateid", value)
            }
        }

    var predlog_id: Int?
        get() = sharedPreferences.getInt("predlogid", 0)
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putInt("predlogid", value)
            }
        }

    var sound: Boolean?
        get() = sharedPreferences.getBoolean("sound", true)
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putBoolean("sound", value)
            }
        }

    var music: Boolean?
        get() = sharedPreferences.getBoolean("music", true)
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putBoolean("music", value)
            }
        }


}
