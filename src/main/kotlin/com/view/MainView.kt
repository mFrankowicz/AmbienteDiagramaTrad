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

            }

            anchorpane {

                anchorpaneConstraints {

                    leftAnchor = 0.0
                    rightAnchor = 0.0
                    bottomAnchor = 0.0
                    topAnchor = 30.0

                }

                hbox {

                    button("tradução\nMargir") {

                        setPrefSize(200.0,200.0)




                    }

                }

            }

        }




    }

}