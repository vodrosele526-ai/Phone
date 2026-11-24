package com.ismail.phone

import android.Manifest
import android.app.Activity
import android.app.role.RoleManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telecom.TelecomManager
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private lateinit var number: EditText
    private val secretCode = "*#*#786#*#*"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDialer()
        readDialIntent(intent)
        requestDialerRole()
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        readDialIntent(intent)
    }
    private fun createDialer() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(22, 28, 22, 22)
            setBackgroundColor(0xFFF6F8F7.toInt())
        }
        root.addView(TextView(this).apply {
            text = "Phone"; textSize = 30f; gravity = Gravity.CENTER
            setTextColor(0xFF145A3A.toInt())
        }, fullWidth())
        root.addView(TextView(this).apply {
            text = "Dial  •  Contacts  •  Recent"; textSize = 14f
            gravity = Gravity.CENTER; setPadding(0, 8, 0, 18)
        }, fullWidth())
        number = EditText(this).apply {
            hint = "Enter phone number"; textSize = 24f; gravity = Gravity.CENTER
            inputType = android.text.InputType.TYPE_CLASS_PHONE; setSingleLine(true)
        }
        root.addView(number, fullWidth())
        val keys = listOf("1","2","3","4","5","6","7","8","9","*","0","#")
        for (r in 0..3) {
            val row = LinearLayout(this).apply { gravity = Gravity.CENTER }
            for (c in 0..2) {
                val key = keys[r * 3 + c]
                row.addView(Button(this).apply {
                    text = key; textSize = 22f; setOnClickListener { number.append(key) }
                }, LinearLayout.LayoutParams(0, 58, 1f).apply { setMargins(4, 3, 4, 3) })
            }
            root.addView(row, fullWidth())
        }
        root.addView(Button(this).apply { text = "Call"; textSize = 18f; setOnClickListener { startCall() } }, fullWidth())
        root.addView(Button(this).apply {
            text = "Delete"; setOnClickListener {
                val s = number.text.toString()
                if (s.isNotEmpty()) number.setText(s.dropLast(1))
                number.setSelection(number.text.length)
            }
        }, fullWidth())
        root.addView(Button(this).apply {
            text = "Contacts"; setOnClickListener {
                try { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"))) }
                catch (_: Exception) { toast("Contacts app not found.") }
            }
        }, fullWidth())
        root.addView(Button(this).apply {
            text = "Recent Calls"; setOnClickListener {
                try { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("content://call_log/calls"))) }
                catch (_: Exception) { toast("Call history is not available.") }
            }
        }, fullWidth())
        setContentView(root)
    }
    private fun fullWidth() = LinearLayout.LayoutParams(-1, -2)
    private fun readDialIntent(i: Intent?) {
        val data = i?.data
        if (data?.scheme == "tel") number.setText(data.schemeSpecificPart)
    }
    private fun requestDialerRole() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val rm = getSystemService(RoleManager::class.java)
            if (rm.isRoleAvailable(RoleManager.ROLE_DIALER) && !rm.isRoleHeld(RoleManager.ROLE_DIALER))
                startActivityForResult(rm.createRequestRoleIntent(RoleManager.ROLE_DIALER), 101)
        }
    }
    private fun startCall() {
        val dialed = number.text.toString().trim()
        if (dialed == secretCode) { number.setText(""); startActivity(Intent(this, SecretActivity::class.java)); return }
        if (dialed.isBlank()) { toast("আগে একটি নম্বর লিখুন।"); return }
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 102); return
        }
        try {
            val telecom = getSystemService(TELECOM_SERVICE) as TelecomManager
            telecom.placeCall(Uri.fromParts("tel", dialed, null), Bundle())
        } catch (_: Exception) {
            try { startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$dialed"))) }
            catch (_: Exception) { toast("কল করা যায়নি। SIM ও অনুমতি পরীক্ষা করুন।") }
        }
    }
    private fun toast(s: String) = Toast.makeText(this, s, Toast.LENGTH_LONG).show()
}
