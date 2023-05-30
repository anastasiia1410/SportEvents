package com.example.sportevents.util

import androidx.appcompat.widget.AppCompatImageView
import com.example.sportevents.R
import java.text.SimpleDateFormat
import java.util.Locale

fun AppCompatImageView.loadImage(sportTitle: String) {
    when (sportTitle) {
        "SOCCER" -> this.setImageResource(R.drawable.ic_soccer)
        "BASKETBALL" -> this.setImageResource(R.drawable.ic_basketball)
        "TENNIS" -> this.setImageResource(R.drawable.ic_tennis)
        "TABLE TENNIS" -> this.setImageResource(R.drawable.ic_table_tennis)
        "ESPORTS" -> this.setImageResource(R.drawable.ic_esports)
        "HANDBALL" -> this.setImageResource(R.drawable.ic_handball)
        "BEACH VOLLEYBALL" -> this.setImageResource(R.drawable.ic_beach_volleyball)
    }
}

fun Long.parseTime(): String {
    val format = SimpleDateFormat("HH:MM:ss", Locale.getDefault())
    return format.format(this)
}


