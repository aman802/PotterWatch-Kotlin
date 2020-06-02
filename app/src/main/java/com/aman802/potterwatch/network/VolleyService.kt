package com.aman802.potterwatch.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

object VolleyService {

    private const val BASE_URL = "https://www.potterapi.com/v1"
    private const val apiKey = "\$2a\$10\$Yf3r1J5pqs7KMEgqfDAdlOAIx28ALo0RGhauErnYrd6Q1gqyg7Cxu"

    interface JSONObjectInterface {
        fun onJSONObjectSuccess(response: JSONObject)
        fun onError(error: VolleyError)
    }

    interface JSONArrayInterface {
        fun onJSONArraySuccess(response: JSONArray)
        fun onError(error: VolleyError)
    }

    private var requestQueue: RequestQueue? = null

    fun getAllCharacters(context: Context, TAG: String, mCallback: JSONArrayInterface) {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context)

        val url = "$BASE_URL/characters?key=$apiKey"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                mCallback.onJSONArraySuccess(response)
            },
            Response.ErrorListener { error ->
                mCallback.onError(error)
            })

        jsonArrayRequest.tag = TAG
        requestQueue?.add(jsonArrayRequest)
    }

    fun getCharacterFromId(context: Context, id: String, TAG: String, mCallback: JSONObjectInterface) {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context)

        val url = "$BASE_URL/characters/$id?key=$apiKey"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                mCallback.onJSONObjectSuccess(response)
            },
            Response.ErrorListener { error ->
                mCallback.onError(error)
            })

        jsonObjectRequest.tag = TAG
        requestQueue?.add(jsonObjectRequest)
    }

    fun cancelAllCalls(tag: String) {
        requestQueue?.cancelAll(tag)
    }
}