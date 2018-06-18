package com.control

import com.model.Note
import com.model.User
import javafx.collections.ObservableList
import org.dizitart.kno2.documentOf
import org.dizitart.kno2.filters.eq
import org.dizitart.kno2.nitrite
import org.dizitart.no2.Document
import org.dizitart.no2.NitriteCollection
import tornadofx.*
import java.io.File

object LocalNoteRepository {

    private val db = nitrite {

        file = File("src/main/resources/notesDB")
        autoCommitBufferSize = 2048
        compress = false
        autoCompact= false

    }

    fun createNoteInLocalDB(note: Note) {

        val noteCollection = db.getCollection(note.dbReferenceID)
        val document = createDocumentFromNote(note)
        noteCollection.insert(document)

    }

    fun saveNote(note: Note) {
        val noteCollection = db.getCollection(note.dbReferenceID)
        val document = createDocumentFromNote(note)
        noteCollection.update("thisNoteID" eq note.thisNoteID, document)
        println("saved $document")
    }

    fun loadNotesForDB(user: User?, dbName: String? = null) : ObservableList<Note>? {
        val noteCollection = db.getCollection(user?.userDBName ?: dbName)
        val list = mutableListOf<Note>().observable()
        noteCollection.find().forEach {
            val note = createNoteFromDocument(it)
            list.add(note)
        }.let {
            return list
        }
    }

    fun loadNotesForUser(user: User) : ObservableList<Note>? {
        val list = mutableListOf<Note>().observable()
        db.listCollectionNames().forEach {
            val collections = db.getCollection(it)
            collections.find("authorID" eq user.userInternalID).forEach {
                val note = createNoteFromDocument(it)
                list.add(note)
            }
        }
        return list
    }

    fun loadNotesForNote(note: Note) : ObservableList<Note>? {
        val list = mutableListOf<Note>().observable()
        val noteCollection = db.getCollection(note.dbReferenceID)
        noteCollection.find("otherNoteIDReference" eq note.otherNoteIDReference).forEach {
            val note = createNoteFromDocument(it)
            list.add(note)
        }.let {
            return list
        }
    }

    private fun createDocumentFromNote(note: Note): Document {
        val authorID = ("authorID" to note.authorID)
        val thisNoteID = ("thisNoteID" to note.thisNoteID)
        val dbReferenceID = ("dbReferenceID" to note.dbReferenceID)
        val sightReference = ("sightReference" to note.sightReference)
        val otherNoteIDReference = ("otherNoteIDReference" to note.otherNoteIDReference)
        val text = ("text" to note.text)

        return documentOf(authorID, thisNoteID, dbReferenceID, sightReference, otherNoteIDReference, text)
    }

    private fun createNoteFromDocument(document: Document): Note {
        val authorID = document["authorID"].toString()
        val thisNoteID = document["thisNoteID"].toString()
        val dbReferenceID = document["dbReferenceID"].toString()
        val sightReference = document["sightReference"].toString().toInt()
        val otherNoteIDReference = document["otherNoteIDReference"].toString()
        val text = document["text"].toString()
        return Note(authorID, thisNoteID, dbReferenceID, sightReference, text, otherNoteIDReference)
    }

}