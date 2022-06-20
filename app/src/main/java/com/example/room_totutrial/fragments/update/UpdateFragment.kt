package com.example.room_totutrial.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room_totutrial.R
import com.example.room_totutrial.data.Note
import com.example.room_totutrial.viewModel.NoteViewModel


class UpdateFragment : Fragment() {

    lateinit var noteViewModel : NoteViewModel
    lateinit var updateButton: Button
    lateinit var titleText: EditText
    lateinit var discreptionText: EditText

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        titleText = view.findViewById(R.id.update_title)
        discreptionText = view.findViewById(R.id.update_description)
        updateButton = view.findViewById(R.id.update_note)

        titleText.setText(args.customObject.title)
        discreptionText.setText(args.customObject.description)

        updateButton.setOnClickListener {
            updateNotesToDataBase()
        }

        //Add Menu
        setHasOptionsMenu(true)

        return view
    }


    private fun updateNotesToDataBase() {

        val title = titleText.text.toString()
        val description = discreptionText.text.toString()
        if (inputCheck(title, description)) {


            val note = Note(args.customObject.id , title , description)
            noteViewModel.updateNote(note)


            Toast.makeText(requireContext(), " Updated :) ", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Please fill the fields :(", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.delete){
            deleteNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNotes(){
        // Add AlertDialog

        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton(" امسح " ){ _ , _ ->
            noteViewModel.deleteSingleNote(args.customObject)
            Toast.makeText(requireContext()," تم المسح ",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton("لا مش همسح "){ _ , _ ->

        }
        builder.create()
        builder.show()
    }

}