package com.leeyunbo.myrealtrip.data

import android.os.Parcel
import android.os.Parcelable

data class News(var title: String?, var description: String?, var keywords: ArrayList<String>?, var imageUrl: String?, var link: String?) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.createStringArrayList(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(description)
        writeStringList(keywords)
        writeString(imageUrl)
        writeString(link)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<News> = object : Parcelable.Creator<News> {
            override fun createFromParcel(source: Parcel): News = News(source)
            override fun newArray(size: Int): Array<News?> = arrayOfNulls(size)
        }
    }
}