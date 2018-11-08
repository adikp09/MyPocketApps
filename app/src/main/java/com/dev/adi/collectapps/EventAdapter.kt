package com.dev.adi.collectapps

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_event.view.*
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat

class EventAdapter(val event: MutableList<Event>, val timeZone: String) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dtZoneFrom = event[position].eventForm.toDateTime(event[position].timezone)
        val dtZoneTo = event[position].eventTo.toDateTime(event[position].timezone)

        holder.itemView.event_subject.text = event[position].subject
        holder.itemView.txt_loaction.text = event[position].location
        holder.itemView.txt_form.text = dtZoneFrom.withZone(DateTimeZone.forID(timeZone)).toStringDate()
        holder.itemView.txt_to.text = dtZoneTo.withZone(DateTimeZone.forID(timeZone)).toStringDate()
        holder.itemView.txt_timezone.text = event[position].timezone
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_event, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return event.size
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView)

    private fun String.toDateTime(id: String): DateTime = DateTimeFormat.forPattern("dd MMM yyyy HH:mm").withZone(DateTimeZone.forID(id)).parseDateTime(this)
    private fun DateTime.toStringDate(): String = DateTimeFormat.forPattern("dd MMM yyyy HH:mm").print(this)
}