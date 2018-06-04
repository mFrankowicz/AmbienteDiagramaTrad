package com.view

import com.app.Styles
import com.control.MainController
import com.model.Sight
import com.model.SightItemViewModel
import com.model.SightScope
import javafx.application.Platform
import javafx.geometry.Insets
import javafx.scene.control.SelectionMode
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    val controller: MainController by inject()


    /*override val root = tableview(controller.sightList) {
        column("Sight nº", SightItemViewModel::sightNumberProperty)
        column("PerformanceAs", SightItemViewModel::performanceAsProperty)
        column("nnView", SightItemViewModel::nnViewOneProperty)
        column("nnViewTwo", SightItemViewModel::nnViewTwoProperty)
        column("theory", SightItemViewModel::theoryProperty)

    }*/

    override val root = scrollpane {

        runAsync {
            return@runAsync controller.sightList
        } ui {

            vbox {

                children.bind(it) {

                    val editScope = SightScope()
                    editScope.model.item = it
                    val node = find(SightListFragment::class, editScope)
                    anchorpane {
                        this += node.root
                    }

                }

            }

        }
    }

    }

/*    override val root = listview(controller.sightList) {
        selectionModel.selectionMode = SelectionMode.SINGLE

        this.items.forEach {

            val editScope = SightScope()
            editScope.model.item = it


            cellFormat {
                graphic = cache {
                    find(SightListFragment::class, editScope).root
                }
            }

        }

        //cellFragment(SightListFragment::class)

    }
}*/

class SightListFragment : Fragment() {
    val controller: MainController by inject()
    //val model = SightItemViewModel(itemProperty)

    override val scope = super.scope as SightScope

    override val root = hbox(15) {

        runAsync {
            return@runAsync scope.model
        } ui { model ->

            println(model.sightNumber)

            // Performance As...
            anchorpane {
                vbox(5) {

                    children.bind(model.performanceAs.value) { i ->

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

/*
class PerformanceAs : Fragment() {

    override val scope = super.scope as TableScope
    val model = scope.model


    override val root = anchorpane {
        textfield {

            runAsync {

                return@runAsync model.performanceAs

            } ui { loadedText ->

                this@textfield.text = loadedText

                setOnKeyPressed {
                    model.performanceAsProperty.bindBidirectional(this.textProperty())
                }

            }

        }
    }

}
        */