package com.model

import javafx.beans.property.*
import tornadofx.*

class Note (authorID: String,
            thisNoteID: String,
            sightReference: Int,
            otherNoteIDReference: String?,
            text: String
            ) {

    val authorIDProperty = SimpleStringProperty(authorID)
    var authorID by authorIDProperty

    val thisNoteIDProperty = SimpleStringProperty(thisNoteID)
    var thisNoteID by thisNoteIDProperty

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