package io.boxo.stripe

import android.app.Application
import io.boxo.js.params.PaymentData
import io.boxo.sdk.Miniapp
import io.boxo.ui.main.BoxoActivity
import com.stripe.android.PaymentConfiguration

object BoxoStripe {
    var merchantDisplayName: String = ""

    fun init(
        application: Application,
        publishableKey: String,
        displayName: String
    ) {
        merchantDisplayName = displayName
        PaymentConfiguration.init(application, publishableKey)
    }

    fun handleStripePayment(activity: BoxoActivity, miniapp: Miniapp, paymentData: PaymentData) {
        StripePaymentDialog().apply {
            this.miniapp = miniapp
            this.paymentData = paymentData
        }.show(activity.supportFragmentManager, "boxo_stripe_payment")
    }
}