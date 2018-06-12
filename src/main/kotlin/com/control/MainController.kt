package com.control

import com.model.*
import tornadofx.*
import java.util.*

class MainController : Controller() {

    var sightList = mutableListOf<Sight>().observable()

    fun addPerformanceAs(index: Int, text: String) {
        sightList[index].performanceAs.add(PerformanceAs(text, UUID.randomUUID().toString()))
    }

    fun addNNViewOne(index: Int, text: String) {
        sightList[index].nnViewOne.add(NNView(text, UUID.randomUUID().toString()))
    }

    fun addNNViewTwo(index: Int, text: String) {
        sightList[index].nnViewTwo.add(NNView(text, UUID.randomUUID().toString()))
    }

    fun addTheory(index: Int, text: String) {
        sightList[index].theory.add(Theory(text, UUID.randomUUID().toString()))
    }

    fun saveToLocalDb(dbName: String? = "rootDB") {

        LocalRepository.saveToDB(sightList, dbName)

    }

    fun loadFromLocalDb(dbName: String? = "rootDB") {
        LocalRepository.loadFromDB(dbName)!!.forEach { sight ->
            sightList.add(sight)
        }
        sightList.forEach {
            println(it.sightNumber)
        }
    }


    init {

        /*for(i in 0..31) {
            val sight = Sight(i,
                    mutableListOf(PerformanceAs("perf1 $i"), PerformanceAs("perf2 $i")).observable(),
                    mutableListOf(NNView("View one n $i"), NNView("View one 2 n$i")).observable(),
                    mutableListOf(NNView("View two n $i"), NNView("View two 2 n$i")).observable(),
                    mutableListOf(Theory("Therory n $i")).observable())

            sightList.add(i, sight)

            println(i)
        }*/
        //println(LocalRepository.db)
        /*LocalRepository.loadFromDB()!!.forEach { sight ->
            sightList.add(sight)
        }*/
        sightList.forEach {
            println(it.sightNumber)
        }
    }

}

