package com.appboxo.stripepayment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.appboxo.sdk.Appboxo
import com.appboxo.stripe.AppboxoStripe
import com.appboxo.stripepayment.ui.theme.StripePaymentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StripePaymentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun Greeting(modifier: Modifier = Modifier) {
        Box(modifier = modifier) {
            val context = LocalContext.current
            Button(
                onClick = {
                    Appboxo.getMiniapp("app_id")
                        .setPaymentEventListener { appboxoActivity, miniapp, paymentData ->
                            AppboxoStripe.handleStripePayment(appboxoActivity, miniapp, paymentData)
                        }
                        .setAuthListener { appboxoActivity, miniapp ->
                            miniapp.setAuthCode("auth_code")
                        }
                        .open(context)
                },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = "Miniapp")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        StripePaymentTheme {
            Greeting()
        }
    }
}