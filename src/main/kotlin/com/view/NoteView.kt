package com.view

import com.control.AuthHolder
import com.control.LocalAuthRepository
import com.control.LocalNoteRepository
import com.model.Note
import com.model.User
import javafx.scene.layout.AnchorPane
import tornadofx.*
import java.util.*

class NoteView : Fragment() {

    val translationNotes: User by param()
    val fromSight: Int by param()

    val notes = LocalNoteRepository.loadNotesForDB(translationNotes)

    override val root = anchorpane {

        buttonbar {

            anchorpaneConstraints {
                topAnchor = 0
                leftAnchor = 0
                rightAnchor = 0
            }


            button("add") {
                action {
                    val author = AuthHolder.userLogged?.userInternalID ?: "guest"
                    val noteID = UUID.randomUUID().toString()
                    val dbReferenceID = translationNotes.userDBName
                    val sightReference = fromSight
                    val otherNoteID = ""
                    val text = ""
                    val note = Note(author, noteID, dbReferenceID, sightReference, otherNoteID, text)
                    LocalNoteRepository.saveNoteToLocalDB(note)
                }
            }

        }


        scrollpane {

            anchorpaneConstraints {
                topAnchor = 30
                leftAnchor = 0
                rightAnchor = 0
                bottomAnchor = 0
            }

            setPrefSize(450.0,600.0)

            vbox(5) {

                runAsync {
                    return@runAsync notes
                } ui {

                    it?.filter { it.dbReferenceID == translationNotes.userDBName }?.let {

                        children.bind(it.observable()) { note ->

                            var noteView: AnchorPane by singleAssign()
                            var editNoteView: AnchorPane by singleAssign()
                            val text = note.textProperty


                            noteView = anchorpane {

                                setPrefSize(400.0, 350.0)

                                webview {

                                    anchorpaneConstraints {
                                        leftAnchor = 3
                                        rightAnchor = 3
                                        topAnchor = 3
                                        bottomAnchor = 40
                                    }

                                    this.engine.loadContent("-> n.t : ${LocalAuthRepository.findById(note.authorID)?.userName
                                            ?: "not"} \n \n Vista: ${note.sightReference + 1} \n \n ${text.value}")

                                    text.onChange {
                                        println(it)
                                        this.engine.loadContent("-> n.t : ${LocalAuthRepository.findById(note.authorID)?.userName
                                                ?: "not"} \n \n Vista: ${note.sightReference + 1} \n \n ${text.value}")
                                    }

                                }

                                buttonbar {

                                    anchorpaneConstraints {
                                        leftAnchor = 3
                                        bottomAnchor = 3
                                    }

                                    button("e") {
                                        disableWhen((note.authorID != AuthHolder.userLogged!!.userInternalID).toProperty())
                                        action {
                                            this@anchorpane.replaceWith(editNoteView,
                                                    ViewTransition.Slide(0.3.seconds,
                                                            tornadofx.ViewTransition.Direction.LEFT))
                                        }
                                    }
                                    button("n")

                                }

                            }

                            editNoteView = anchorpane {

                                setPrefSize(400.0, 350.0)

                                htmleditor {

                                    anchorpaneConstraints {
                                        leftAnchor = 3
                                        rightAnchor = 3
                                        topAnchor = 3
                                        bottomAnchor = 40
                                    }

                                    htmlText = note.text

                                    setOnKeyPressed {
                                        text.bindBidirectional(this@htmleditor.htmlText.toProperty())
                                    }

                                }

                                buttonbar {

                                    anchorpaneConstraints {
                                        leftAnchor = 3
                                        bottomAnchor = 3
                                    }

                                    button("s") {
                                        action {
                                            this@anchorpane.replaceWith(noteView,
                                                    ViewTransition.Slide(0.3.seconds,
                                                            tornadofx.ViewTransition.Direction.RIGHT))
                                        }
                                    }

                                }

                            }

                            noteView

                        }
                    }

                }

            }
        }
    }

}
