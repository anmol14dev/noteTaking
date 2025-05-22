package dev.anmol.noteTaking.controllers

import dev.anmol.noteTaking.model.Note
import dev.anmol.noteTaking.services.NoteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController // Marks this class as a REST controller
@RequestMapping("/api/notes") // Base path for all endpoints in this controller
class NoteController(private val noteService: NoteService) {
    // GET /api/notes
    @GetMapping // Maps HTTP GET requests to this method
    fun getAllNotes(): ResponseEntity<List<Note>> {
        return ResponseEntity( noteService.getAllNotes(), HttpStatus.OK)
    }

    // GET /api/notes/{id}
    @GetMapping("/{id}") // Maps HTTP GET requests with a path variable 'id'
    fun  getNoteById(@PathVariable id: String): ResponseEntity<Note> {
        val note = noteService.getNoteById(id)
        return if (note != null) {
            ResponseEntity(note, HttpStatus.OK) // Return 200 OK with the note
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND ) // Return 404 Not Found
        }
    }

    @PutMapping("/{id}")
    fun updateNote(
        @PathVariable id: String, // Get ID from URL path
        @RequestBody updatedNote: Note // Get note data from request body
    ): ResponseEntity<Note> {
        val result = noteService.updateNote(id, updatedNote)
        return if (result != null) {
            ResponseEntity(result, HttpStatus.OK) // Return 200 OK with the updated note
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND) // Return 404 Not Found if note doesn't exist
        }
    }

    @PostMapping("/add")
    fun addNote(@RequestBody note: Note) : ResponseEntity<List<Note>> {
        val result = noteService.addNote(note)
        return ResponseEntity( result, HttpStatus.OK)
    }

    @DeleteMapping("/remove/{id}")
    fun removeNote(@PathVariable id: String) : ResponseEntity<List<Note>> {
        val result = noteService.remove(id)
        return ResponseEntity(result, HttpStatus.OK)
    }

}