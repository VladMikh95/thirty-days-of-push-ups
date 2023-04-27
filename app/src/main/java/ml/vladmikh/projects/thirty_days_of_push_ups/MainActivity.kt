package ml.vladmikh.projects.thirty_days_of_push_ups

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ml.vladmikh.projects.thirty_days_of_push_ups.databinding.ActivityMainBinding
import ml.vladmikh.projects.thirty_days_of_push_ups.utils.RemoteConfigUtils

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(5000)
        setTheme(R.style.Theme_Thirtydaysofpushups)
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Получение данных из remoteConfig firebase
        RemoteConfigUtils.init()
        val url = RemoteConfigUtils.getUrl()
        //Устанавливаем начальный пункт назначения по условию
        val navHostFragment = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        if (url.isEmpty()){
            graph.setStartDestination(R.id.calendarFragment)
        } else {
            graph.setStartDestination(R.id.buttonFragment)
        }
        navHostFragment.navController.graph = graph
    }

}