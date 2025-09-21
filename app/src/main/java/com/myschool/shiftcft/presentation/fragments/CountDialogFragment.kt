package com.myschool.shiftcft.presentation.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.myschool.shiftcft.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext


class CountDialogFragment : DialogFragment() {

    interface CountInputListener {
        fun onCountInput(count: Int)
    }

    lateinit var listener: CountInputListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listener = activity as? CountInputListener
            ?: throw ClassCastException("$activity must implement CountInputListener")

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_dialog, null, false)

        val edTextCount = view.findViewById<EditText>(R.id.ed_text_count)

        builder.setView(view)
            .setTitle(context?.getString(R.string.number_user_message))
            .setPositiveButton(context?.getString(R.string.ok_message)) { _, _ ->

                val countString = edTextCount.text.toString()
                val count = countString.toIntOrNull() ?: 1

                listener.onCountInput(count)

            }.setNegativeButton(context?.getString(R.string.cancel_message)) { dialog, _ ->
                dialog.dismiss()
            }


        return builder.create()
    }


}