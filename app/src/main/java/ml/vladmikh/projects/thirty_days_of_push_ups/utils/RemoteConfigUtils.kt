package ml.vladmikh.projects.thirty_days_of_push_ups.utils


import android.util.Log
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import java.util.logging.Logger

object RemoteConfigUtils {

    private const val FONT_SIZE = "font_size"
    private const val FONT_COLOR = "font_color"

    private val defaults = mapOf(
        "font_size" to 18,
        "font_color" to "#ff0000"
    )

    private lateinit var remoteConfig: FirebaseRemoteConfig

    fun init() {
        remoteConfig =  getFirebaseRemoteConfig()
    }

    private fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {

        val remoteConfig = Firebase.remoteConfig

        //Установливаем минимальный интервал выборки, чтобы обеспечить обновления
        val configSettings = remoteConfigSettings {
            if (BuildConfig.DEBUG) {
                minimumFetchIntervalInSeconds = 0
            } else {
                minimumFetchIntervalInSeconds = 60
            }
        }

        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(defaults)

        remoteConfig.fetchAndActivate().addOnCompleteListener {
            Log.d("RemoteConfig", "addOnCompleteListener")
        }

        return remoteConfig
    }

    fun getFontSize() : Double = remoteConfig.getDouble(FONT_SIZE)

    fun getFontColor(): String = remoteConfig.getString(FONT_COLOR)

}