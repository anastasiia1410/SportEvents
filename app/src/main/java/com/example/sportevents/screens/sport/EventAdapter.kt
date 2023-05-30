package com.example.sportevents.screens.sport

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportevents.R
import com.example.sportevents.databinding.ItemEventBinding
import com.example.sportevents.screens.entity.Event
import com.example.sportevents.util.parseTime

class EventAdapter : RecyclerView.Adapter<EventAdapter.EventVH>() {
    private var eventList: MutableList<Event> = mutableListOf()

    var onFavoriteClick: ((Event) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventVH {
        return EventVH(ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EventVH, position: Int) {
        val event = eventList[position]
        with(holder.binding) {
            tvFirstPlayer.text = event.players.substringBefore("-")
            tvSecondPlayer.text = event.players.substringAfter("-")
            tvTime.text = event.getTimeToDate().parseTime()
            ivFavorite.setImageResource(if (event.isFavorite) R.drawable.ic_enabled_star else R.drawable.ic_unenabled_star)
            ivFavorite.setOnClickListener {
                onFavoriteClick?.invoke(event)
            }
        }
    }

    override fun getItemCount(): Int = eventList.size

    fun updateItems(newEventList: List<Event>) {
        eventList.clear()
        eventList.addAll(newEventList)
        notifyDataSetChanged()
    }

    class EventVH(val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root)
}

