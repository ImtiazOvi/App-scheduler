package com.meimtiaz.common.extfun

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

fun Activity.showViewAlertDialog(view: View, title: String?, cancelable: Boolean): AlertDialog =
    AlertDialog.Builder(this)
        .setView(view)
        .setCancelable(cancelable)
        .setTitle(title)
        .show()

fun Fragment.showViewAlertDialog(view: View, title: String?, cancelable: Boolean): AlertDialog =
    AlertDialog.Builder(requireContext())
        .setView(view)
        .setCancelable(cancelable)
        .setTitle(title)
        .show()




