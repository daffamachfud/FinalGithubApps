package com.onoh.finalgithubapps.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailUser(
    var username:String? = null,
    var avatar:String? =null,
    var company:String? =null,
    var location:String?="null",
    var repository:Int = 0,
    var followers:Int = 0,
    var following:Int = 0
) : Parcelable