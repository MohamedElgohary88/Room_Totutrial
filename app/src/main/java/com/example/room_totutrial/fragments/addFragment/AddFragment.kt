package com.example.room_totutrial.fragments.addFragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room_totutrial.R
import com.example.room_totutrial.data.Note
import com.example.room_totutrial.viewModel.NoteViewModel

class AddFragment : Fragment() {

    lateinit var noteViewModel: NoteViewModel
    lateinit var addButton: Button
    lateinit var titleText: EditText
    lateinit var discreptionText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.add, container, false)


        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        titleText = view.findViewById(R.id.title)
        discreptionText = view.findViewById(R.id.description)
        addButton = view.findViewById(R.id.update_note)

        addButton.setOnClickListener {
            addNotesToDataBase()
        }


        return view
    }

    private fun addNotesToDataBase() {
        val title = titleText.text.toString()
        val description = discreptionText.text.toString()
        if (inputCheck(title, description)) {

            val note = Note(0, title, description)

            noteViewModel.addNote(note)

            Toast.makeText(requireContext(), " Added Successful :) ", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Please fill the fields :) ", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }

}