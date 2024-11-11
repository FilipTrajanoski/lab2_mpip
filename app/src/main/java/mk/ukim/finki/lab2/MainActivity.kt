package mk.ukim.finki.lab2

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.lab2.viewmodels.NoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNote: EditText
    private lateinit var buttonSave: Button
    private lateinit var textViewNote: TextView
    private lateinit var noteViewModel: NoteViewModel

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editTextNote = findViewById(R.id.editTextNote)
        buttonSave = findViewById(R.id.saveButton)
        textViewNote = findViewById(R.id.textViewNote)
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]


        val sharedPreferences = getSharedPreferences("NoteApp", Context.MODE_PRIVATE)
        val savedNote = sharedPreferences.getString("note", "") ?: ""
        noteViewModel.setCurrentNote(savedNote);

        noteViewModel.getCurrentNoteValue().observe(this, Observer {newNote -> textViewNote.text = newNote})

        buttonSave.setOnClickListener{
            val note = editTextNote.text.toString()
            if(note.isNotBlank()){
                noteViewModel.setCurrentNote(note)
                sharedPreferences.edit().putString("note", note).apply()
            }
        }
    }
}