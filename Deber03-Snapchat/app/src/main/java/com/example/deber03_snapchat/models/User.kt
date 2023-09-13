package com.example.deber03_snapchat.models

import com.example.deber03_snapchat.utils.SerializedName
class User() {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("uid")
    var uid: String? = null

    constructor(uid: String, name: String) : this() {
        this.uid = uid
        this.name = name
    }
}