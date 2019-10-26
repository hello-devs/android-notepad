package com.hellodevs.training.notepad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        create_note_fab.setOnClickListener (this)

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
        }else{
            when (view.id){
                R.id.create_note_fab -> createNewNote()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK || data == null){
            return
        }

        when (requestCode){
            NoteDetailActivity.REQUEST_EDIT_NOTE -> processEditNoteResult(data)
        }
    }

    private fun createNewNote() {
        showNoteDetail(-1)
    }

    fun showNoteDetail(noteIndex: Int){

        val note = if(noteIndex < 0) Note() else notes[noteIndex]

        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, noteIndex)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note)

        startActivityForResult(intent, NoteDetailActivity.REQUEST_EDIT_NOTE)
    }

    private fun processEditNoteResult(data: Intent) {
        val noteIndex = data.getIntExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, -1)


        when (data.action){
            NoteDetailActivity.ACTION_DELETE_NOTE -> deleteNote(noteIndex)
            NoteDetailActivity.ACTION_SAVE_NOTE -> {
                    val note = data.getParcelableExtra<Note>(NoteDetailActivity.EXTRA_NOTE)
                    saveNote(note, noteIndex)
                }
        }
    }

    private fun deleteNote(noteIndex: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun saveNote(note: Note, noteIndex: Int){
        if(noteIndex < 0){
            notes.add(0,note) //Ajout des nouvelles notes à l'index 0 pour un affichage en debut de liste
        }else{
            notes[noteIndex] = note
        }
        adapter.notifyDataSetChanged()
    }


}
