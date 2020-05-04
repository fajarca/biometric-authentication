package io.fajarca.project.biometricauthentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import io.fajarca.project.biometricauthentication.helper.AuthenticationError
import io.fajarca.project.biometricauthentication.helper.navigateTo
import io.fajarca.project.biometricauthentication.helper.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var biometricPrompt : BiometricPrompt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBiometricAuthentication()

        ivAuthenticate.setOnClickListener {
            if (isBiometricFeatureAvailable()) {
                biometricPrompt.authenticate(buildBiometricPrompt())
            }
        }

    }

    private fun setupBiometricAuthentication() {
        val executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor, biometricCallback)
    }

    private fun buildBiometricPrompt(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("Verify your identity")
            .setDescription("Confirm your identity so we can verify it's you")
            .setNegativeButtonText("Cancel")
            .setConfirmationRequired(false) //Allows user to authenticate without performing an action, such as pressing a button, after their biometric credential is accepted.
            .build()
    }

    private fun isBiometricFeatureAvailable(): Boolean {
        val biometricManager = BiometricManager.from(this)
        return biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS
    }

    private val biometricCallback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            navigateTo<HomeActivity>()
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)

            if (errorCode != AuthenticationError.AUTHENTICATION_DIALOG_DISMISSED.errorCode && errorCode != AuthenticationError.CANCELLED.errorCode) {
                tvErrorNotice.text = errString
            }
        }
    }
}

