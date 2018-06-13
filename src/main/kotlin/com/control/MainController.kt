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

        sightList =  mutableListOf<Sight>().observable()

        LocalRepository.loadFromDB(dbName)!!.forEach { sight ->
            sightList.add(sight)
        }
        sightList.forEach {
            println(it.sightNumber)
        }
    }

}

