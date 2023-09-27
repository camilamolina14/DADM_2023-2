package com.unal.reto4

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.DialogFragment
import kotlin.system.exitProcess


class Dialogues : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogType = arguments?.getInt(ARG_DIALOG_TYPE) ?: DIALOG_TYPE_DEFAULT

        return when (dialogType) {
            DIALOG_TYPE_ONE -> createDialogOne()
            DIALOG_TYPE_TWO -> createDialogTwo()
            else -> createDefaultDialog()
        }
    }

    private fun createDialogOne(): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.go_out_dialogue, null)

        builder.setView(dialogView)
            .setPositiveButton("Yes") { _, _ ->
                // Handle positive button click
                exitProcess(1)
            }
            .setNegativeButton("No") { _, _ ->
                // Handle negative button click or dismiss
                dialog?.dismiss()
            }

        return builder.create()
    }

    private fun createDialogTwo(): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.level_dialogue, null)

        builder.setView(dialogView)

        dialogView.findViewById<Button>(R.id.option1_button).setOnClickListener {
            (activity as MainActivity).switchToFragmentA()
            dialog?.dismiss()
        }

        dialogView.findViewById<Button>(R.id.option2_button).setOnClickListener {
            (activity as MainActivity).switchToFragmentB()
            dialog?.dismiss()
        }

        dialogView.findViewById<Button>(R.id.option3_button).setOnClickListener {
            (activity as MainActivity).switchToFragmentC()
            dialog?.dismiss()
        }

        return builder.create()
    }

    private fun createDefaultDialog(): Dialog {
        // Create and return a default dialog
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Restart")
        builder.setMessage("Do you want to restart game?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            (activity as MainActivity).switchToFragmentA()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){ dialog, _ ->
            dialog.dismiss()
        }
        return builder.create()
    }

    companion object {
        const val ARG_DIALOG_TYPE = "dialog_type"
        const val DIALOG_TYPE_DEFAULT = 0
        const val DIALOG_TYPE_ONE = 1
        const val DIALOG_TYPE_TWO = 2

        // Factory method to create an instance of the fragment with a specified dialog type
        fun newInstance(dialogType: Int): Dialogues {
            val fragment = Dialogues()
            val args = Bundle()
            args.putInt(ARG_DIALOG_TYPE, dialogType)
            fragment.arguments = args
            return fragment
        }
    }
}