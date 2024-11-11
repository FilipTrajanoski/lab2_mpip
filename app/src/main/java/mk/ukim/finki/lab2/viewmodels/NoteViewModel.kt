package mk.ukim.finki.lab2.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel:ViewModel() {

    private val _currentNote: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getCurrentNote(): String{
        return _currentNote.value.toString();
    }

    fun setCurrentNote(note: String){
        this._currentNote.value = note;
    }

    fun getCurrentNoteValue(): MutableLiveData<String>{
        return this._currentNote;
    }
}