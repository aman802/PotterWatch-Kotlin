package com.aman802.potterwatch.characteroverview

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.aman802.potterwatch.KeyValuePairModel

@BindingAdapter("keyText")
fun TextView.setKeyText(item: KeyValuePairModel) {
    text = item.key
}

@BindingAdapter("valueText")
fun TextView.setValueText(item: KeyValuePairModel) {
    text = item.value.toString()
}