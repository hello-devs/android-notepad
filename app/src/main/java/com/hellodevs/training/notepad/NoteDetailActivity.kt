package com.hellodevs.training.notepad

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_note_detail.*

class NoteDetailActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_EDIT_NOTE = 1
        const val EXTRA_NOTE = "note"
        const val EXTRA_NOTE_INDEX = "noteIndex"
        const val ACTION_SAVE_NOTE = "com.hellodevs.training.notepad.actions.ACTION_SAVE_NOTE"
        const val ACTION_DELETE_NOTE = "com.hellodevs.training.notepad.actions.ACTION_DELETE_NOTE"
    }

    lateinit var note: Note
    var noteIndex: Int = -1

    lateinit var titleView: TextView
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        note = intent.getParcelableExtra(EXTRA_NOTE)!!
        noteIndex = intent.getIntExtra(EXTRA_NOTE_INDEX, -1)


        titleView = noteTitle
        textView = text

        titleView.text = note.title
        textView.text = note.text
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_note_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                saveNote()
                true
            }R.id.action_delete -> {
                ShowDeleteNoteDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun ShowDeleteNoteDialog() {
        val confirmFragment = ConfirDeleteNoteDialogFragment(note.title)
        confirmFragment.listener = object : ConfirDeleteNoteDialogFragment.confirmDeleteDialogListener{
            override fun onDialogPositiveClick() {
                deleteNote()
            }

            override fun onDialogNegativeClick() {}

        }

        confirmFragment.show(supportFragmentManager,"confirmDeleteDialog")
    }

    private fun deleteNote() {
        intent = Intent(ACTION_DELETE_NOTE)
        intent.putExtra(EXTRA_NOTE_INDEX, noteIndex)
        setResult(Activity.RESULT_OK,  intent)
        finish()

    }

    private fun saveNote(){
        note.title = titleView.text.toString()
        note.text = textView.text.toString()

        intent = Intent(ACTION_SAVE_NOTE)
        intent.putExtra(EXTRA_NOTE_INDEX, noteIndex)
        intent.putExtra(EXTRA_NOTE, note)
        setResult(Activity.RESULT_OK,  intent)
        finish()
    }
}
