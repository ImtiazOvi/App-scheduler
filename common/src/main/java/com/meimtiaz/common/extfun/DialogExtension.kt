package com.meimtiaz.common.extfun

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun Context.showAlertDialog(
    positiveBtn: String,
    negativeBtn: String,
    title: String?,
    message: String?,
    cancelable: Boolean,
    positiveBtnCallback:(()->Unit)?,
    negativeBtnCallback:(()->Unit)?,
) {
    AlertDialog.Builder(this)
        .setCancelable(cancelable)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveBtn) { dialog, _ ->
            dialog.dismiss()
            positiveBtnCallback?.invoke()
        }.setNegativeButton(negativeBtn) { dialog, _ ->
            dialog.dismiss()
            negativeBtnCallback?.invoke()
        }.show()
}




