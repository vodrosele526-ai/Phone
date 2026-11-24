package com.ismail.phone
import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class InCallActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL; gravity = Gravity.CENTER; setPadding(24,24,24,24)
        }
        root.addView(TextView(this).apply { text = "Phone call"; textSize = 26f })
        root.addView(TextView(this).apply {
            text = "Call controls are handled by Android in this starter build."
            textSize = 16f; setPadding(0,16,0,16)
        })
        root.addView(Button(this).apply { text = "Back to Phone"; setOnClickListener { finish() } })
        setContentView(root)
    }
}
