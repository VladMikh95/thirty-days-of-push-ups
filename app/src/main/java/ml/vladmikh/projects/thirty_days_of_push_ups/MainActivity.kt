package ml.vladmikh.projects.thirty_days_of_push_ups

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import ml.vladmikh.projects.thirty_days_of_push_ups.databinding.ActivityMainBinding
import ml.vladmikh.projects.thirty_days_of_push_ups.utils.AppPreferences
import ml.vladmikh.projects.thirty_days_of_push_ups.utils.RemoteConfigUtils

class MainActivity : AppCompatActivity() {

    private lateinit var graph : NavGraph
    private lateinit var navHostFragment : NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_Thirtydaysofpushups)
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RemoteConfigUtils.init()
        AppPreferences.init(this)

        navHostFragment = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        graph = inflater.inflate(R.navigation.nav_graph)

    }

    override fun onStart() {
        super.onStart()

        //Получение данных из remoteConfig firebase
        val urlRemoteConfig = RemoteConfigUtils.getUrl()

        //Проверка сохранены ли данные в SharedPreferences
        if (AppPreferences.url == AppPreferences.ABSENT) {
            //Установка  начальный пункт назначения по условию
            if (urlRemoteConfig == ""){
                Log.i("abc", urlRemoteConfig)
                Log.i("abc", "calendar")
                graph.setStartDestination(R.id.calendarFragment)
            } else {
                //Сохранение переменной в SharedPreferences
                AppPreferences.url = urlRemoteConfig
                Log.i("abc", "web1")
                graph.setStartDestination(R.id.webViewFragment)
            }
        } else {
            //условие выбора начального пункта назначения в зависимости подключен интернет или нет
            if (checkForInternet(this)) {
                Log.i("abc", "web2")
                graph.setStartDestination(R.id.webViewFragment)
            } else {
                Log.i("abc", "info")
                graph.setStartDestination(R.id.infoFragment)
            }
        }

        navHostFragment.navController.graph = graph
    }

    private fun checkForInternet(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //если версия Android равна M или больше, используется
        //NetworkCapabilities, чтобы проверить, к какому типу
        // сети подключен Интернет
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            //Возвращает сетевой объект, соответствующий текущей активной сети передачи данных по умолчанию.
            val network = connectivityManager.activeNetwork ?: return false

            //  Представление возможностей активной сети.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                //Указывает, что эта сеть использует Wi-Fi transport,или Wi-Fi подключен к сети
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Указывает, что эта сеть использует Cellular transport, или сотовая связь подключена к сети
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            // если версия Android ниже M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected

        }
    }


}