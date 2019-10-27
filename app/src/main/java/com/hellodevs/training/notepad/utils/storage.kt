package com.hellodevs.training.notepad.utils

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.hellodevs.training.notepad.Note
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*

const val TAG = "storage"

fun persistNote(context: Context, note:Note) {

    if(TextUtils.isEmpty(note.filename)){
        note.filename = UUID.randomUUID().toString() + ".note"
    }

    val fileOutput = context.openFileOutput(note.filename, Context.MODE_PRIVATE)
    val outputStream = ObjectOutputStream(fileOutput)
    outputStream.writeObject(note)
    outputStream.close()
}

fun loadNotes(context: Context): MutableList<Note>{
    val notes = mutableListOf<Note>()

    val notesDir = context.filesDir
    for (fileName in notesDir.list()){
        val note = loadNote(context, fileName)
        Log.i(TAG,"Note chargé $note")

        notes.add(note)
    }

    return notes
}

private fun loadNote (context: Context, filename: String): Note{
    val fileInput = context.openFileInput(filename)
    val inputStream = ObjectInputStream(fileInput)
    val note = inputStream.readObject() as Note
    inputStream.close()

    return note
}

fun deleteNote(context:Context, note: Note){
    context.deleteFile(note.filename)
}
