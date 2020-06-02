package com.aman802.potterwatch

import org.json.JSONObject

class CharacterModel(response: JSONObject) {
    private var id: String = ""
    private var name: String = ""
    private var house: String = ""

    init {
        id = response.getString("_id")
        name = response.getString("name")
        if (response.has("house")) {
            house = response.getString("house")
        }
        else {
            if (response.has("school")) {
                val schoolName = response.getString("school")
                if (schoolName == "Hogwarts School of Witchcraft and Wizardry") {
                    house = "Hogwarts"
                }
            }
        }
    }

    fun getId(): String {
        return id
    }

    fun getName(): String {
        return name
    }

    fun getHouse(): String {
        return house
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharacterModel

        if (id != other.id) return false
        if (name != other.name) return false
        if (house != other.house) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + house.hashCode()
        return result
    }


}