package com.hellodevs.training.notepad

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ConfirDeleteNoteDialogFragment(val noteTitle: String = "") : DialogFragment() {

    interface confirmDeleteDialogListener {
        fun onDialogPositiveClick()

        fun onDialogNegativeClick()
    }

    var listener: confirmDeleteDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)

        builder
            .setMessage("Êtes-vous sur de vouloir supprimer la note \"$noteTitle\" ?")
            .setPositiveButton("Supprimer",
                DialogInterface.OnClickListener { dialog, id -> listener?.onDialogPositiveClick() })
            .setNegativeButton("Annuler",
                DialogInterface.OnClickListener { dialog, id -> listener?.onDialogNegativeClick() })
        return builder.create()
    }
}
