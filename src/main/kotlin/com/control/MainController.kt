package com.control

import com.model.*
import tornadofx.*

class MainController : Controller() {

    val sightList = mutableListOf<Sight>().observable()

    fun addPerformanceAs(index: Int, text: String) {
        sightList.get(index).performanceAs.add(PerformanceAs(text))
    }

    init {

        for(i in 0..31) {
            val sight = Sight(i,
                    mutableListOf(PerformanceAs("perf1 $i"), PerformanceAs("perf2 $i")).observable(),
                    mutableListOf(NNView("View one n $i"), NNView("View one 2 n$i")).observable(),
                    mutableListOf(NNView("View two n $i"), NNView("View two 2 n$i")).observable(),
                    mutableListOf(Theory("Therory n $i")).observable())

            sightList.add(i, sight)
            println(i)
        }

    }

}

