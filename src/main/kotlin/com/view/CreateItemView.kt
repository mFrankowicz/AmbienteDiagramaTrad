package com.view

import com.control.MainController
import javafx.scene.control.ToggleGroup
import tornadofx.*

class CreateItemView : View() {

    val controller: MainController by inject()

    val index: Int by param()

    //override val scope = super.scope as SightScope

    //private val model = scope.model

    private val toggleGroup = ToggleGroup()

    override val root = anchorpane {

        setPrefSize(400.0, 300.0)

        vbox(10) {

            anchorpaneConstraints {
                topAnchor = 10
                leftAnchor = 10
            }

            button("performance as") {
                action {
                    controller.addPerformanceAs(index, "performance as $index new")
                }
            }
            button("nnView 1") {
                action {
                    controller.addNNViewOne(index, "nnView1 $index new")
                }
            }
            //button("direction 1")
            button("nnView 2") {
                action {
                    controller.addNNViewTwo(index, "nnView2 $index new")
                }
            }
            //button("direction 2")
            button("theory") {
                action {
                    controller.addTheory(index, "theory $index new")
                }
            }
        }

    }

}