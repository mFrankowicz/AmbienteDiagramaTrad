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
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    val controller: MainController by inject()


    override val root = anchorpane {

        scrollpane {

            setPrefSize(1000.0, 600.0)

            runAsync {
                return@runAsync controller.sightList
            } ui {

                vbox(5) {

                    children.bind(it) {

                        val editScope = SightScope()
                        editScope.model.item = it
                        val node = find(SightListFragment::class, editScope)
                        anchorpane {
                            this += node.root
                            padding = Insets(5.0, 5.0, 5.0, 5.0)
                        }

                    }

                }

            }

        }

        button("add") {
            action {
                controller.addPerformanceAs(0, "Neww")
            }
        }
    }
}

class SightListFragment : Fragment() {
    val controller: MainController by inject()
    //val model = SightItemViewModel(itemProperty)

    override val scope = super.scope as SightScope

    override val root = hbox(15) {

        runAsync {
            return@runAsync scope.model
        } ui { model ->

            println(model.sightNumber.value)
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

                    children.bind(model.nnViewOne.value) { i ->

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

            // NN View Two
            anchorpane {
                vbox(5) {

                    children.bind(model.nnViewTwo.value) { i ->

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

            // Theory
            anchorpane {
                vbox(5) {

                    children.bind(model.theory.value) {

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


            addClass(Styles.sight)


        }
    }

}