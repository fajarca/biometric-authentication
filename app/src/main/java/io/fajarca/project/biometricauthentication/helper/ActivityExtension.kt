package io.fajarca.project.biometricauthentication.helper

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.toast(message : String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}