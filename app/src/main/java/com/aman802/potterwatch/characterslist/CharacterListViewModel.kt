package com.aman802.potterwatch.characterslist

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aman802.potterwatch.CharacterModel
import com.aman802.potterwatch.network.VolleyService
import com.android.volley.VolleyError
import org.json.JSONArray

class CharacterListViewModel(val context: Context) : ViewModel() {

    private companion object {
        const val TAG = "CharacterListViewModel"
    }

    private val _characterList = MutableLiveData<ArrayList<CharacterModel>>()
    val characterList: LiveData<ArrayList<CharacterModel>>
        get() = _characterList

    private val _progressBarVisible = MutableLiveData<Boolean>()
    val progressBarVisible: LiveData<Boolean>
        get() = _progressBarVisible

    private val _navigateToCharacterOverview = MutableLiveData<CharacterModel>()
    val navigateToCharacterOverview: LiveData<CharacterModel>
        get() = _navigateToCharacterOverview

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        _progressBarVisible.value = true
        VolleyService.getAllCharacters(context, TAG, object : VolleyService.JSONArrayInterface {
            override fun onJSONArraySuccess(response: JSONArray) {
                val tempList = ArrayList<CharacterModel>()
                for (i in 0 until response.length()) {
                    val currentCharacterJSON = response.getJSONObject(i)
                    tempList.add(CharacterModel(currentCharacterJSON))
                }
                _characterList.value = tempList
                _progressBarVisible.value = false
            }

            override fun onError(error: VolleyError) {
                Toast.makeText(context, error.message.toString(), Toast.LENGTH_SHORT).show()
                _progressBarVisible.value = false
            }
        })
    }

    fun onCharacterClicked(characterModel: CharacterModel) {
        _navigateToCharacterOverview.value = characterModel
    }

    fun onCharacterOverviewNavigated() {
        _navigateToCharacterOverview.value = null
    }

}