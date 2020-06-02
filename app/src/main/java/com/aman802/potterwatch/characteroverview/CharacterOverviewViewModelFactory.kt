package com.aman802.potterwatch.characteroverview

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CharacterOverviewViewModelFactory(private val context: Context, private val id: String) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterOverviewViewModel::class.java)) {
            return CharacterOverviewViewModel(context, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}