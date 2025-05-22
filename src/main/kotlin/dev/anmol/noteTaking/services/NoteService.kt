package dev.anmol.noteTaking.services

import dev.anmol.noteTaking.model.Note
import org.springframework.stereotype.Service

@Service // Marks this class as a Spring service component
class NoteService {

    // Simulating a database with a mutable list of notes
    private val notes = mutableListOf(
        Note("1", "First Note", "This is the content of my first note."),
        Note("2", "Shopping List", "Milk, Eggs, Bread, Butter, Coffee"),
        Note("3", "Meeting Agenda", "Review Q1, Plan Q2, Discuss new project ideas")
    )

    fun getAllNotes(): List<Note> {
        return notes
    }

    fun getNoteById(id: String): Note? {
        return notes.find { it.id == id } // find returns null if no match is found
    }

    fun updateNote(id: String, note: Note) : Note? {
        val index = notes.indexOfFirst { note -> note.id == id }
        notes.removeAt(index)
        notes.add(index , note)
        return notes.get(index)
    }

    fun addNote(note : Note)  : List<Note> {
        notes.add(note)
        return notes
    }

    fun remove(id: String) : List<Note> {
        val index = notes.indexOfFirst { note -> note.id == id }
        notes.removeAt(index)
        return notes
    }
}