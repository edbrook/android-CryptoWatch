package uk.co.dekoorb.android.cryptowatch.ui

import android.databinding.BindingAdapter
import android.widget.TextView
import java.util.*


/**
 * Created by edbrook on 22/01/2018.
 */

@BindingAdapter("intval")
fun setIntText(view: TextView, value: Int) {
    view.text = String.format(Locale.UK, "%d", value)
}

@BindingAdapter("currencyVal")
fun setDoubleText(view: TextView, value: Double) {
    view.text = String.format(Locale.UK, "£%.2f", value)
}

@BindingAdapter("floatval")
fun setFloatText(view: TextView, value: Float) {
    view.text = String.format(Locale.UK, "%.2f", value)
}