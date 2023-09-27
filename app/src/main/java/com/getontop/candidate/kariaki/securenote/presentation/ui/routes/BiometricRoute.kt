package com.getontop.candidate.kariaki.securenote.presentation.ui.routes

import android.content.Context
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.util.Log
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.getontop.candidate.kariaki.securenote.MainActivity
import com.getontop.candidate.kariaki.securenote.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiometricRoute(navController: NavController, activity: FragmentActivity) {
    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Biometric login for my app")
        .setSubtitle("Log in using your biometric credential")
        .setAllowedAuthenticators(BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
        .build()

    val biometricPrompt =
        BiometricPrompt(activity, ContextCompat.getMainExecutor(navController.context),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                }
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    navController.navigate(Routes.home)
                }
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            })
    val biometricManager = BiometricManager.from(navController.context)
    val canAuthenticate = biometricManager.canAuthenticate(BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
    if(canAuthenticate!=BiometricManager.BIOMETRIC_SUCCESS){
        navController.navigate(Routes.home)
        return
    }

    Scaffold (
        topBar = {
                 TopAppBar(title = { Text(text = "Biometric Required", style = MaterialTheme.typography.titleLarge)})
        },
        content = {
            it.calculateBottomPadding()
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = {
                    biometricPrompt.authenticate(promptInfo)
                }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_fingerprint_24), contentDescription = null)
                }
            }
        }
    )
    // manager.
}
