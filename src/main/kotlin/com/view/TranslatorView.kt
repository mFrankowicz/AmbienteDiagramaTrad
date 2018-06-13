package com.view

import com.control.MainController
import com.model.SightScope
import javafx.geometry.Insets
import javafx.stage.StageStyle
import tornadofx.*

class TranslatorView : Fragment("Translate") {
    val controller: MainController by inject()

    val dbName: String by param()

    init {

        controller.loadFromLocalDb(dbName)

    }

    override val root = scrollpane {

        setMinSize(1000.0, 600.0)

        runAsync {
            return@runAsync controller.sightList
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

                            button("s") {
                                action {
                                    controller.saveToLocalDb(dbName)
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