package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.dev.adi.collectapps.event.Event
import com.dev.adi.collectapps.event.EventAdapter
import kotlinx.android.synthetic.main.activity_meetup.*
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

class MeetupActivity : AppCompatActivity() {

    internal lateinit var adapter: EventAdapter
    var listEvent = arrayListOf<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meetup)

        list_event.layoutManager = LinearLayoutManager(this)
        list_event.itemAnimator = DefaultItemAnimator()

        bt_create_event.setOnClickListener {
            createMeetup()
            view_create_event.visibility = View.GONE
            view_home.visibility = View.VISIBLE
        }

        start_event.setOnClickListener {
            view_create_event.visibility = View.VISIBLE
            view_list_event.visibility = View.GONE
            list_event.visibility = View.GONE
            view_home.visibility = View.GONE
        }

        see_event.setOnClickListener {
            view_create_event.visibility = View.GONE
            view_list_event.visibility = View.VISIBLE
            list_event.visibility = View.GONE
            view_home.visibility = View.GONE
        }

        bt_see_event.setOnClickListener {
            list_event.visibility = View.VISIBLE
            adapter = EventAdapter(listEvent, et_timezone_user.text.toString())
            list_event.adapter = adapter
            refeseshData()
        }
    }

    private fun createMeetup () {
        val subjectName = et_subject.text.toString()
        val location = et_location.text.toString()
        val timezone = et_timezone.text.toString()
        val form = et_form.text.toString()
        val to = et_to.text.toString()

        val currentTime = DateTime(2015, 1, 23, 2, 0, 0, DateTimeZone.UTC)
        val tokyo = currentTime.withZone(DateTimeZone.forID("Asia/Tokyo"))

        listEvent.add(Event(subjectName, location, form, to, timezone))

        adapter = EventAdapter(listEvent, timezone)
        list_event.adapter = adapter

        refeseshData()
    }

    private fun refeseshData () {
        adapter.notifyDataSetChanged()
    }
}
