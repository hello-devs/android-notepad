package com.hellodevs.training.notepad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notes: MutableList<Note>
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        setSupportActionBar(toolbar)

        notes = mutableListOf<Note>()

        notes.add(Note("note 1", "ceci est la note numéro 1"))
        notes.add(Note("note 2", "ceci est la note numéro 2"))
        notes.add(Note("note 3", "ceci est la note numéro 3"))
        notes.add(Note("note 4", "ceci est la note numéro 4"))

        adapter = NoteAdapter(notes, this)

        notes_recycler_view.layoutManager = LinearLayoutManager(this)
        notes_recycler_view.adapter = adapter
    }

    override fun onClick(view: View) {
        if(view.tag != null){
            showNoteDetail(view.tag as Int)
        }
    }


    fun showNoteDetail(noteIndex: Int){

        val note = notes[noteIndex]

        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, noteIndex)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note)

        startActivity(intent)
    }


}
