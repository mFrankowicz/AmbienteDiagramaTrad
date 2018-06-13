package com.view

import javafx.stage.StageStyle
import tornadofx.*



class MainView : View ("Main") {

    override val root = anchorpane {

        setPrefSize(900.0,700.0)

        menubar {

            menu("Editor") {

                menu("abrir") {
                    action {

                        find(EditorView::class).openWindow(stageStyle = StageStyle.DECORATED)

                    }
                }

                menu("nota") {

                    menu("inserir") {

                        action {

                            find(NoteView::class).openWindow(stageStyle = StageStyle.UTILITY)
                        }

                    }
                    menu("inserir2") {

                        action {

                            find(NoteView::class).openWindow(stageStyle = StageStyle.UTILITY)
                        }

                    }
                    menu("inserir3") {

                        action {

                            find(NoteView::class).openWindow(stageStyle = StageStyle.UTILITY)
                        }

                    }

                }

            }

        }

        pane {

            anchorpaneConstraints {

                leftAnchor = 50
                topAnchor = 100

            }

            hbox {

                button("tradução\nMargit") {

                    setPrefSize(200.0,200.0)

                    action {
                        find<TranslatorView>(mapOf(TranslatorView::dbName to "margitDB")).openWindow(stageStyle = StageStyle.DECORATED)
                    }
                }

            }

        }

    }

}