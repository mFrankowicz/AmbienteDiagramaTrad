package com.model

import javafx.beans.property.*
import tornadofx.*

class Note (authorID: String,
            thisNoteID: String,
            dbReferenceID: String,
            sightReference: Int,
            text: String,
            otherNoteIDReference: String?
            ) {

    val authorIDProperty = SimpleStringProperty(authorID)
    var authorID by authorIDProperty

    val thisNoteIDProperty = SimpleStringProperty(thisNoteID)
    var thisNoteID by thisNoteIDProperty

    val dbReferenceIDProperty = SimpleStringProperty(dbReferenceID)
    var dbReferenceID by dbReferenceIDProperty

    val sightReferenceProperty = SimpleIntegerProperty(sightReference)
    var sightReference by sightReferenceProperty

    val otherNoteIDReferenceProperty = SimpleStringProperty(otherNoteIDReference)
    var otherNoteIDReference by otherNoteIDReferenceProperty

    val textProperty = SimpleStringProperty(text)
    var text by textProperty
}

class NoteViewModel : ItemViewModel<Note>() {

    val authorID = bind(autocommit = true) {
        item?.authorIDProperty
    }

    val thisNoteID = bind(autocommit = true) {
        item?.thisNoteIDProperty
    }

    val dbReferenceID = bind(autocommit = true) {
        item?.dbReferenceIDProperty
    }

    val sightReference = bind(autocommit = true) {
        item?.sightReferenceProperty
    }

    val otherNoteIDReference = bind(autocommit = true) {
        item?.otherNoteIDReferenceProperty
    }

    val text = bind(autocommit = true) {
        item?.textProperty
    }

}