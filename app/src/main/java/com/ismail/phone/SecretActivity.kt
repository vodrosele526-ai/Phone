package com.ismail.phone

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class SecretActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL; gravity = Gravity.CENTER
            setPadding(24, 24, 24, 24); setBackgroundColor(0xFF14231C.toInt())
        }
        root.addView(TextView(this).apply { text = "Private Panel"; textSize = 27f; setTextColor(-1) })
        root.addView(TextView(this).apply {
            text = "Starter panel — privacy features are not enabled yet."
            textSize = 15f; setTextColor(0xFFD7E5DC.toInt()); setPadding(0, 14, 0, 18)
        })
        root.addView(EditText(this).apply {
            hint = "Create a PIN (next build)"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD
        }, LinearLayout.LayoutParams(-1, -2))
        root.addView(Button(this).apply {
            text = "App Lock / Hide Apps"
            setOnClickListener { Toast.makeText(this@SecretActivity,
                "Starter screen only. Lock and hide functions need the next development stage.", Toast.LENGTH_LONG).show() }
        })
        root.addView(Button(this).apply { text = "Close"; setOnClickListener { finish() } })
        setContentView(root)
    }
}
