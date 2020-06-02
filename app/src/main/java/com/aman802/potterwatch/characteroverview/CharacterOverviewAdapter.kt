package com.aman802.potterwatch.characteroverview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aman802.potterwatch.KeyValuePairModel
import com.aman802.potterwatch.databinding.ItemCharacterOverviewListBinding

class CharacterOverviewAdapter() : ListAdapter<KeyValuePairModel, CharacterOverviewAdapter.ViewHolder>(KeyValueDiffCallback()) {

    class ViewHolder private constructor(private val binding: ItemCharacterOverviewListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: KeyValuePairModel) {
            binding.keyValuePairModel = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCharacterOverviewListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.bind(item)
    }
}

class KeyValueDiffCallback : DiffUtil.ItemCallback<KeyValuePairModel>() {
    override fun areItemsTheSame(oldItem: KeyValuePairModel, newItem: KeyValuePairModel): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: KeyValuePairModel, newItem: KeyValuePairModel): Boolean {
        return oldItem == newItem
    }

}