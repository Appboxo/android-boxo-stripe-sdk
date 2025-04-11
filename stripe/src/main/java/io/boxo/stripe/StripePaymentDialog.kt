package io.boxo.stripe

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import io.boxo.js.params.PaymentData
import io.boxo.sdk.Miniapp
import io.boxo.stripe.BoxoStripe.merchantDisplayName
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult

internal class StripePaymentDialog : DialogFragment() {
    internal var miniapp: Miniapp? = null
    internal var paymentData: PaymentData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (miniapp != null && paymentData != null) {
            val paymentSheet =
                PaymentSheet(this) { paymentSheetResult: PaymentSheetResult ->
                    when (paymentSheetResult) {
                        is PaymentSheetResult.Completed -> {
                            paymentData?.status = "success"
                            miniapp?.sendPaymentResult(paymentData!!)
                        }

                        is PaymentSheetResult.Canceled -> {
                            paymentData?.status = "cancelled"
                            miniapp?.sendPaymentResult(paymentData!!)
                        }

                        is PaymentSheetResult.Failed -> {
                            paymentData?.status = "failed"
                            miniapp?.sendPaymentResult(paymentData!!)
                        }
                    }
                    dismiss()
                }
            val configuration: PaymentSheet.Configuration =
                PaymentSheet.Configuration.Builder(merchantDisplayName)
                    .build()
            paymentSheet.presentWithPaymentIntent(paymentData!!.transactionToken, configuration)
        } else dismiss()
    }
}