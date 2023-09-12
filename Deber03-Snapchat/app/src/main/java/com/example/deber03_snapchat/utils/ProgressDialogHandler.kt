package com.example.deber03_snapchat.utils

import android.app.ProgressDialog
import android.content.Context
import android.widget.ProgressBar
import com.example.deber03_snapchat.R

class ProgressDialogHandler {
    private var progressDialog: ProgressDialog? = null

    fun toggleProgressDialog(context: Context, active: Boolean) {
        if (active) {
            progressDialog = ProgressDialog(/* context = */ context, /* theme = */ R.style.CustomProgressDialogTheme).apply {
                setCancelable(false)
                show()
                setContentView(ProgressBar(context))
            }
        } else {
            progressDialog?.dismiss()
        }
    }
}