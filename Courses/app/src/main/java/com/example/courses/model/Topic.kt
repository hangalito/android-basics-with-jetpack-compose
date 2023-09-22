package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourcedId: Int,
    val associatedCourses: Int,
    @DrawableRes val imageResourceId: Int
)