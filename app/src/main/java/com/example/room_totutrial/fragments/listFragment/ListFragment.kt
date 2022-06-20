package com.example.room_totutrial.fragments.listFragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room_totutrial.R
import com.example.room_totutrial.adapter.NoteClickListner
import com.example.room_totutrial.data.Note
import com.example.room_totutrial.viewModel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() , NoteClickListner {

    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteRecyclerView: RecyclerView
    private lateinit var noteList: List<Note>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.list, container, false)

        // recyclerView
        noteRecyclerView = view.findViewById(R.id.recycler)
        noteList = ArrayList<Note>()


        // noteViewModel
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        noteViewModel.readAllData.observe(viewLifecycleOwner, { note ->
            val adapter = com.example.room_totutrial.adapter.ListAdapter(note,this)
            noteRecyclerView.adapter = adapter
        }
        )


        floatingActionButton = view.findViewById(R.id.floatingActionButton)

        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        //Add Menu
        setHasOptionsMenu(true)

        return view

    }

    override fun clickListner(position : Note) {
        Toast.makeText(requireContext(),"Clicked ${position.id}" ,Toast.LENGTH_SHORT).show()
        val action = ListFragmentDirections.actionListFragmentToUpdateFragment(position)
        findNavController().navigate(action)
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
            noteViewModel.deleteAllNote()
            Toast.makeText(requireContext()," كله اتمسح  ",Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("لا مش همسح "){ _ , _ ->

        }
        builder.setTitle("Delete All Notes ")
        builder.setMessage("اهدى يبنى هتمسح كل حاجه ولا اى ")
        builder.create()
        builder.show()
    }

}