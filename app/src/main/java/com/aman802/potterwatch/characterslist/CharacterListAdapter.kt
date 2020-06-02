package com.aman802.potterwatch.characterslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aman802.potterwatch.CharacterModel
import com.aman802.potterwatch.databinding.ItemCharacterListBinding

class CharacterListAdapter(private val clickListener: ItemClickListener): ListAdapter<CharacterModel, CharacterListAdapter.ViewHolder>(CharacterDiffCallback()) {


    class ViewHolder private constructor(private val binding: ItemCharacterListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterModel, clickListener: ItemClickListener) {
            binding.characterModel = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCharacterListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.bind(item, clickListener)
    }

}

class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.getId() == newItem.getId()
    }

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem == newItem
    }

}

class ItemClickListener(val clickListener: (characterModel: CharacterModel) -> Unit) {
    fun onClick(characterModel: CharacterModel) = clickListener(characterModel)
}