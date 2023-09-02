package io.gauravtak.nfc_read_demo

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.Button
import androidx.core.content.ContextCompat


fun Activity.showDialog(
    title: String = getString(R.string.app_name),
    message: String,
    btnText: String = getString(R.string.okay),
    onclick: (dialog: DialogInterface) -> Unit,
    isCancelable: Boolean = false
) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
    val dialog: AlertDialog = builder.setTitle(title)
        .setMessage(message)
        .setPositiveButton(btnText, null)
        .setCancelable(isCancelable)
        .create()
    dialog.show()
   
    val btnPositive: Button =
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
    btnPositive.setOnClickListener {
        onclick(dialog)
    }
}

fun Activity.showDialogWithTwoButtons(
    title: String = getString(R.string.app_name),
    message: String,
    btnPositiveText: String = getString(R.string.okay),
    btnNegativeText: String = getString(R.string.cancel),
    onclick: (dialog: DialogInterface) -> Unit,
    onNegativeClick: (dialog: DialogInterface) -> Unit,
    isCancelable: Boolean = false,
) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
    val dialog: AlertDialog = builder.setTitle(title)
        .setMessage(message)
        .setPositiveButton(btnPositiveText, null)
        .setNegativeButton(btnNegativeText, null)
        .setCancelable(isCancelable)
        .create()
    dialog.show()
    val btnPositive: Button =
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)

    btnPositive.setOnClickListener {
        onclick(dialog)
    }
    val btnNegative: Button =
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
    btnNegative.setOnClickListener {
        onNegativeClick(dialog)
    }

    btnNegative.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
    btnPositive.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
}