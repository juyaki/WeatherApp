package com.example.weatherapp.extensions

import android.view.View

var View.isVisible
    get() = visibility == View.VISIBLE
    set(isVisible) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }