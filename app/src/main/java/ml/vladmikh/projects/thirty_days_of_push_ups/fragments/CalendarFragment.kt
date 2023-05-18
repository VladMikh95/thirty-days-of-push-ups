package ml.vladmikh.projects.thirty_days_of_push_ups.fragments

import android.R
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.CalendarWeekDay
import com.applandeo.materialcalendarview.EventDay
import ml.vladmikh.projects.thirty_days_of_push_ups.databinding.FragmentCalendarBinding
import java.util.*


class CalendarFragment : Fragment() {

    private var _binding : FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    val events = ArrayList<EventDay>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val events: MutableList<EventDay> = ArrayList()

        val calendar1 = Calendar.getInstance()
        calendar1.set(2023, 4, 5)
        val eventDay = EventDay(calendar1)
        events.add(eventDay)

        val  calendar2 = Calendar.getInstance()
        calendar2.add(Calendar.DAY_OF_MONTH, 10)
        events.add(EventDay(calendar2, R.drawable.ic_media_next))

        val calendar3 = Calendar.getInstance()
        calendar3.add(Calendar.DAY_OF_MONTH, 7)
        events.add(EventDay(calendar3, R.drawable.ic_media_next))

        val calendar4 = Calendar.getInstance()
        calendar4.add(Calendar.DAY_OF_MONTH, 13)
        events.add(EventDay(calendar4,R.drawable.ic_media_next))

        val calendarView = binding.calendar
        calendarView.setFirstDayOfWeek(CalendarWeekDay.SUNDAY)
        //calendarView.setEvents(events)

    }

}