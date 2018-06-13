package com.view

import com.app.Styles
import com.model.SightScope
import javafx.geometry.Insets
import tornadofx.*

class SightListFragment : Fragment() {

    //val model = SightItemViewModel(itemProperty)

    private val _w = 310.0
    private val _h = 250.0

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

                            textarea(i.textProperty) {
                                setOnKeyPressed {
                                    //commitEdit(item)
                                    commitValue()
                                    model.performanceAs.value.forEach {
                                        println(it.text)
                                    }
                                }

                                setPrefSize(_w, _h)
                                //prefHeight = 400.0
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

                            textarea(i.textProperty) {
                                setOnKeyPressed {
                                    //commitEdit(item)

                                    model.nnViewOne.value.forEach {
                                        println(it.text)
                                    }
                                }

                                setPrefSize(_w, _h)
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

                            textarea(i.textProperty) {
                                setOnKeyPressed {
                                    //commitEdit(item)

                                    model.nnViewTwo.value.forEach {
                                        println(it.text)
                                    }
                                }

                                setPrefSize(_w, _h)
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

                                setPrefSize(_w, _h)
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