package com.ahmedtikiwa.felinelove.domain

import android.os.Parcel
import android.os.Parcelable

data class ImageSearchItem(
    val id: String?,
    val url: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageSearchItem> {
        override fun createFromParcel(parcel: Parcel): ImageSearchItem {
            return ImageSearchItem(parcel)
        }

        override fun newArray(size: Int): Array<ImageSearchItem?> {
            return arrayOfNulls(size)
        }
    }

}
