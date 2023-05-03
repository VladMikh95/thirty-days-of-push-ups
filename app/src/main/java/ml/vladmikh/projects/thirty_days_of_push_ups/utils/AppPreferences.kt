package ml.vladmikh.projects.thirty_days_of_push_ups.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private const val NAME = "AppPreferences"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val URL = Pair("url", "absent")

    val ABSENT = "absent"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    //Функция для сохранения данных в SharedPreferences
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var url: String
        get() = preferences.getString(URL.first, URL.second) ?: URL.second
        set(value) = preferences.edit {
            it.putString(URL.first, value)
        }
}