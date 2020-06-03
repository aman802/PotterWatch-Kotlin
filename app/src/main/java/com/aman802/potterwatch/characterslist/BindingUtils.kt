package com.aman802.potterwatch.characterslist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.aman802.potterwatch.CharacterModel
import com.aman802.potterwatch.R

@BindingAdapter("houseImage")
fun ImageView.setHouseImage(item: CharacterModel) {
    setImageResource(when (item.getHouse()) {
        "Gryffindor" -> R.drawable.ic_gryffindor
        "Ravenclaw" -> R.drawable.ic_ravenclaw
        "Slytherin" -> R.drawable.ic_syltherin
        "Hufflepuff" -> R.drawable.ic_hufflepuff
        "Hogwarts" -> R.drawable.ic_hogwarts
        "Beauxbatons" -> R.drawable.ic_beauxbatons
        "Durmstrang" -> R.drawable.ic_durmstrang
        "Death" -> R.drawable.ic_death_eaters
        "DA" -> R.drawable.ic_dumbledore_army
        "Ministry" -> R.drawable.ic_ministry
        "Phoenix" -> R.drawable.ic_phoenix
        else -> R.drawable.ic_unknown
    })
}

@BindingAdapter("characterName")
fun TextView.setCharacterName(item: CharacterModel) {
    text = item.getName()
}