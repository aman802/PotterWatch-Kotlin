package com.aman802.potterwatch.characteroverview

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aman802.potterwatch.KeyValuePairModel
import com.aman802.potterwatch.network.VolleyService
import com.android.volley.VolleyError
import org.json.JSONObject

class CharacterOverviewViewModel(val context: Context, val id: String) : ViewModel() {

    private companion object {
        const val TAG = "CharacterOverviewViewModel"
    }

    private val _keyValuePairModelList = MutableLiveData<ArrayList<KeyValuePairModel>>()
    val keyValuePairModelList: LiveData<ArrayList<KeyValuePairModel>>
        get() = _keyValuePairModelList

    private val _progressBarVisible = MutableLiveData<Boolean>()
    val progressBarVisible: LiveData<Boolean>
        get() = _progressBarVisible

    init {
        getCharacterOverview()
    }

    private fun getCharacterOverview() {
        _progressBarVisible.value = true
        VolleyService.getCharacterFromId(context, id, TAG, object : VolleyService.JSONObjectInterface {
            override fun onJSONObjectSuccess(response: JSONObject) {
                val tempList = ArrayList<KeyValuePairModel>()
                val keys = response.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    if (key != "__v") {
                        val value = response[key]
                        tempList.add(KeyValuePairModel(key, value))
                    }
                }
                _keyValuePairModelList.value = tempList
                _progressBarVisible.value = false
            }

            override fun onError(error: VolleyError) {
                Toast.makeText(context, error.message.toString(), Toast.LENGTH_SHORT).show()
                _progressBarVisible.value = false
            }

        })
    }

}