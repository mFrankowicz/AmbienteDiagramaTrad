package com.view

import com.control.AuthHolder
import com.control.LocalAuthRepository
import com.model.Note
import com.model.User
import javafx.scene.layout.AnchorPane
import tornadofx.*
import java.util.*

class NoteView : Fragment() {

    val translationNotes: User by param()

    val notes = mutableListOf(
            Note("28197552-fa5f-4a45-8d76-b66536611b7b",
                    UUID.randomUUID().toString(),
                    "28197552-fa5f-4a45-8d76-b66536611b7b-userDB" ,
                    0, "nota 1",
                    null),
            Note("2543a234-0bab-44ee-bfd7-922d4b4de8da",
                    UUID.randomUUID().toString(),
                    "2543a234-0bab-44ee-bfd7-922d4b4de8da-userDB" ,
                    0, "nota 2",
                    null),
            Note("2543a234-0bab-44ee-bfd7-922d4b4de8da",
                    UUID.randomUUID().toString(),
                    "28197552-fa5f-4a45-8d76-b66536611b7b-userDB" ,
                    0, "nota 3",
                    null),
            Note("28197552-fa5f-4a45-8d76-b66536611b7b",
                    UUID.randomUUID().toString(),
                    "2543a234-0bab-44ee-bfd7-922d4b4de8da-userDB" ,
                    0, "nota 4",
                    null)

            ).observable()

    override val root = vbox(5) {

        runAsync {
            return@runAsync notes
        } ui {

            it.filter { it.dbReferenceID == translationNotes.userDBName }.let {

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
                                    ?: "not"} \n ${text.value}")

                            text.onChange {
                                println(it)
                                this.engine.loadContent("-> n.t : ${LocalAuthRepository.findById(note.authorID)?.userName
                                        ?: "not"} \n ${text.value}")
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
