package com.example.sportevents.screens.sport

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportevents.R
import com.example.sportevents.databinding.ItemSportBinding
import com.example.sportevents.screens.entity.Event
import com.example.sportevents.screens.entity.Sport
import com.example.sportevents.util.loadImage


class SportAdapter : RecyclerView.Adapter<SportAdapter.ItemVH>() {
    private val sportList: MutableList<Sport> = mutableListOf()

    var onFavoriteClick: ((Event) -> Unit)? = null
    var onItemClick: ((Sport) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return ItemVH(
            ItemSportBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        val sport = sportList[position]
        holder.eventAdapter.updateItems(sport.listEvent.sortedWith(compareByDescending { it.isFavorite }))
        holder.eventAdapter.onFavoriteClick = onFavoriteClick

        with(holder.binding) {
            ivSportIcon.loadImage(sport.title)

        }
        holder.checkState(sport)
        holder.binding.tvSportName.text = sport.title

        holder.binding.tvSportName.setOnClickListener {
            onItemClick?.invoke(sport)
        }
    }

    override fun getItemCount(): Int = sportList.size

    fun updateItems(newSportList: List<Sport>) {
        sportList.clear()
        sportList.addAll(newSportList)
        notifyDataSetChanged()
    }

    class ItemVH(val binding: ItemSportBinding) : RecyclerView.ViewHolder(binding.root) {
        val eventAdapter = EventAdapter()

        init {
            binding.rvEventRecycler.layoutManager =
                LinearLayoutManager(
                    binding.rvEventRecycler.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.rvEventRecycler.adapter = eventAdapter
        }

        fun checkState(sport: Sport) {
            if (sport.isExpandEvent) {
                binding.rvEventRecycler.isVisible = true
                binding.tvSportName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_arrow_up,
                    0
                )
            } else {
                binding.rvEventRecycler.isVisible = false
                binding.tvSportName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_arrow_down,
                    0
                )
            }
        }

    }
}









