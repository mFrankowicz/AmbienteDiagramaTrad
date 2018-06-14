package com.model

import javafx.beans.property.*
import javafx.collections.ObservableList
import tornadofx.*
import java.util.*


class Sight(sightNumber: Int,
            performanceAs: ObservableList<PerformanceAs>,
            nnViewOne: ObservableList<NNView>,
            nnViewTwo: ObservableList<NNView>,
            theory: ObservableList<Theory>) {


    val sightNumberProperty = SimpleIntegerProperty(sightNumber)
    var sightNumber by sightNumberProperty

    val ownerIDProperty = SimpleStringProperty()
    var ownerID by ownerIDProperty

    val performanceAsProperty = SimpleListProperty<PerformanceAs>(performanceAs)
    var performanceAs by performanceAsProperty

    val nnViewOneProperty = SimpleListProperty<NNView>(nnViewOne)
    var nnViewOne by nnViewOneProperty

    val nnViewTwoProperty = SimpleListProperty<NNView>(nnViewTwo)
    var nnViewTwo by nnViewTwoProperty

    val theoryProperty = SimpleListProperty<Theory>(theory)
    var theory by theoryProperty

}

class SightItemViewModel : ItemViewModel<Sight>() {

    val sightNumber = bind(autocommit = true) {
        item?.sightNumberProperty
    }
    val ownerID = bind(autocommit = true) {
        item?.ownerIDProperty
    }
    val performanceAs = bind(autocommit = true) {
        item?.performanceAsProperty
    }
    val nnViewOne = bind(autocommit = true) {
        item?.nnViewOneProperty
    }
    val nnViewTwo = bind(autocommit = true) {
        item?.nnViewTwoProperty
    }
    val theory = bind(autocommit = true) {
        item?.theoryProperty
    }
}

class SightScope : Scope() {
    val model = SightItemViewModel()
}

abstract class Item(text: String, id: String) {
    val textProperty = SimpleStringProperty(text)
    var text by textProperty

    val idProperty = SimpleStringProperty(id)
    var id by idProperty
}

class PerformanceAs(text: String, id: String) : Item(text, id)

class NNView(text: String, id: String) : Item(text, id)

class Theory(text: String, id: String) : Item(text, id)

/*
class GenericItemViewModel (property: ObjectProperty<Item>) : ItemViewModel<Item>(itemProperty = property) {

    val text = bind(autocommit = true) {
        item?.textProperty
    }

}
 */