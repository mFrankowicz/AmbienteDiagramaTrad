package com.view

import com.app.Styles
import com.control.MainController
import com.model.PerformanceAs
import com.model.Sight
import com.model.SightItemViewModel
import com.model.SightScope
import javafx.application.Platform
import javafx.geometry.Insets
import javafx.scene.control.SelectionMode
import javafx.scene.control.ToggleGroup
import javafx.stage.StageStyle
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    val controller: MainController by inject()

    override val root = anchorpane {

        scrollpane {

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
                                    leftAnchor = 2
                                }

                                button("add") {
                                    action {
                                        find<CreateItemView>(mapOf(CreateItemView::index to index)).openModal(stageStyle = StageStyle.UTILITY)
                                    }
                                }
                            }
                            padding = Insets(5.0, 5.0, 5.0, 5.0)
                        }

                    }

                }

            }

        }
    }
}

class CreateItemView: View () {

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

class SightListFragment : Fragment() {

    //val model = SightItemViewModel(itemProperty)

    override val scope = super.scope as SightScope

    override val root = hbox(15) {

        runAsync {
            return@runAsync scope.model
        } ui { model ->
            val sightIndex: Int = model.sightNumber.value as Int

            // Performance As...
            anchorpane {
                vbox(5) {

                    runAsync {
                        return@runAsync model.performanceAs
                    } ui {

                        children.bind(it.value) { i ->

                            textfield(i.textProperty) {
                                setOnKeyPressed {
                                    //commitEdit(item)
                                    commitValue()
                                    model.performanceAs.value.forEach {
                                        println(it.text)
                                    }
                                }

                                prefWidth = 250.0
                                addClass(Styles.performanceAs)

                            }
                        }
                    }
                }

            }

            // NN View One
            anchorpane {
                vbox(5) {

                    runAsync {
                        return@runAsync model.nnViewOne
                    } ui {

                        children.bind(it.value) { i ->

                            textfield(i.textProperty) {
                                setOnKeyPressed {
                                    //commitEdit(item)

                                    model.nnViewOne.value.forEach {
                                        println(it.text)
                                    }
                                }

                                setPrefSize(200.0, 60.0)
                                padding = Insets(10.0, 10.0, 10.0, 10.0)
                                addClass(Styles.nnView)

                            }
                        }
                    }
                }
            }

            // NN View Two
            anchorpane {
                vbox(5) {

                    runAsync {
                        return@runAsync model.nnViewTwo
                    } ui {

                        children.bind(it.value) { i ->

                            textfield(i.textProperty) {
                                setOnKeyPressed {
                                    //commitEdit(item)

                                    model.nnViewTwo.value.forEach {
                                        println(it.text)
                                    }
                                }

                                setPrefSize(200.0, 60.0)
                                padding = Insets(10.0, 10.0, 10.0, 10.0)
                                addClass(Styles.nnView)

                            }
                        }
                    }

                }
            }

            // Theory
            anchorpane {
                vbox(5) {

                    runAsync {
                        return@runAsync model.theory
                    } ui {


                        children.bind(it.value) {

                            textarea(it.textProperty) {
                                setOnKeyPressed {
                                    // commitEdit(item)

                                    model.theory.value.forEach {
                                        println(it.text)
                                    }
                                }

                                setPrefSize(240.0, 200.0)
                                padding = Insets(10.0, 5.0, 10.0, 5.0)
                                addClass(Styles.theory)

                            }
                        }
                    }
                }
            }


            addClass(Styles.sight)


        }
    }

}