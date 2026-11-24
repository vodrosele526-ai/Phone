package com.ismail.phone
import android.content.Intent
import android.telecom.Call
import android.telecom.InCallService

class PhoneInCallService : InCallService() {
    override fun onCallAdded(call: Call) {
        super.onCallAdded(call)
        startActivity(Intent(this, InCallActivity::class.java).addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP))
    }
}
