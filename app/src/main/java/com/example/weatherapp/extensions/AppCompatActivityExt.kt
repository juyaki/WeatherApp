package com.example.weatherapp.extensions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weatherapp.models.Coordinates

fun AppCompatActivity.showDialog(
    title: String,
    message: String,
    positiveButtonText: String,
    onPositiveButtonClicked: () -> Unit
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton("Close") { dialog, which ->
            dialog.dismiss()
        }
        .setPositiveButton(positiveButtonText) { dialog, which ->
            onPositiveButtonClicked()
        }
        .create()
        .show()
}

fun AppCompatActivity.showPermissionDeniedDialog() {
    showDialog(
        title = "Permission denied ?",
        message = "Please change your mind",
        positiveButtonText = "Open app settings"
    ) { openSettingPermission() }
}

fun AppCompatActivity.showErrorDialog(onRetry: () -> Unit) {
    showDialog(
        title = "An error occured",
        message = "Please verify your connection and try again",
        positiveButtonText = "Try again"
    ) { onRetry.invoke() }
}

fun AppCompatActivity.openSettingPermission() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).also {
        it.data = Uri.fromParts("package", packageName, null)
    }
    startActivity(intent)
}

fun AppCompatActivity.checkForPermission(permission: String, onGranted: () -> Unit) {
    val permissionStatus = ContextCompat.checkSelfPermission(this, permission)
    when {
        permissionStatus == PackageManager.PERMISSION_GRANTED -> onGranted.invoke()
        shouldShowRequestPermissionRationale(permission) -> showPermissionDeniedDialog()
        else -> {
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    onGranted.invoke()
                } else {
                    showPermissionDeniedDialog()
                }
            }.also {
                it.launch(permission)
            }
        }
    }
}

fun AppCompatActivity.getLastKnownLocation(): Coordinates? {
    if (ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        return null
    }

    val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val criteria = Criteria().apply {
        accuracy = Criteria.ACCURACY_COARSE
        powerRequirement = Criteria.POWER_MEDIUM
        isAltitudeRequired = false
        isBearingRequired = false
        isSpeedRequired = false
        isCostAllowed = true
    }

    val provider = locationManager.getBestProvider(criteria, true) ?: return null
    val location = locationManager.getLastKnownLocation(provider) ?: return null
    return Coordinates(
        lat = location.latitude,
        lon = location.longitude
    )
}