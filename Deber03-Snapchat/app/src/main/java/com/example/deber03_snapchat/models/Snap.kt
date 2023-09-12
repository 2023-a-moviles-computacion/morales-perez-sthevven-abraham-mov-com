package com.example.deber03_snapchat.models

import com.example.deber03_snapchat.utils.SerializedName
import com.google.firebase.database.Exclude

class Snap() {
    @SerializedName("path")
    var path: String? = null

    @SerializedName("sender")
    var sender: String? = null

    @SerializedName("time")
    var time: String? = null

    @get:Exclude
    var id: String? = null

    constructor(path: String, time: String, sender: String) : this() {
        this.path = path
        this.sender = sender
        this.time = time
    }
}