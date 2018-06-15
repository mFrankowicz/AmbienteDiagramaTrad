package com.view

import com.control.AuthHolder
import com.control.TranslationController
import com.model.SightScope
import com.model.User
import javafx.geometry.Insets
import tornadofx.*

class TranslatorView : Fragment("Translate") {
    val controller: TranslationController by inject()

    val user: User by param()
    val dbName = user.userDBName

    override val root = scrollpane {

        setMinSize(1000.0, 600.0)

        runAsync {
            return@runAsync controller.loadFromLocalDb(dbName)
        } ui {

            vbox(5) {

                children.bind(it) {

                    val editScope = SightScope()
                    editScope.model.item = it
                    val index = editScope.model.sightNumber.value.toInt()
                    val node = find(SightListFragment::class, editScope)
                    anchorpane {
                        this += node.root
                        this += anchorpane {

                            anchorpaneConstraints {
                                bottomAnchor = 2
                                leftAnchor = 30
                            }

                            buttonbar {

                                button("s") {

                                    prefWidth = 30.0

                                    disableWhen((AuthHolder.userLogged?.userDBName != dbName).toProperty())
                                    hiddenWhen((AuthHolder.userLogged?.userDBName != dbName).toProperty())

                                    action {
                                        controller.saveToLocalDb(dbName)
                                    }
                                }

                                button("n.t") {

                                    prefWidth = 30.0

                                    action {

                                        find<NoteView>(mapOf(NoteView::translationNotes to user)).openWindow()

                                    }

                                }
                            }
                        }
                        this += anchorpane {

                            anchorpaneConstraints {
                                bottomAnchor = 5
                                leftAnchor = 60
                            }

                            label {
                                text = "sight nº ${index+1}"
                            }

                        }


                        padding = Insets(5.0, 5.0, 5.0, 5.0)
                    }

                }

            }

        }
    }
}