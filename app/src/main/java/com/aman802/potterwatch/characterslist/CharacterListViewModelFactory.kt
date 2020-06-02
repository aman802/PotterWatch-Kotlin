package com.aman802.potterwatch.characterslist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CharacterListViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {
            return CharacterListViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}