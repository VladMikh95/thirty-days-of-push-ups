package ml.vladmikh.projects.thirty_days_of_push_ups.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import ml.vladmikh.projects.thirty_days_of_push_ups.R
import ml.vladmikh.projects.thirty_days_of_push_ups.databinding.FragmentButtonBinding
import ml.vladmikh.projects.thirty_days_of_push_ups.utils.RemoteConfigUtils

class ButtonFragment : Fragment() {

    private var _binding : FragmentButtonBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textView = binding.myMessage

        RemoteConfigUtils.init()
        getFireBaseParam(textView)
    }

    private fun getFireBaseParam(textView : TextView) {

        textView.textSize = RemoteConfigUtils.getFontSize().toFloat()
        textView.setTextColor(Color.parseColor(RemoteConfigUtils.getFontColor()))
    }

}