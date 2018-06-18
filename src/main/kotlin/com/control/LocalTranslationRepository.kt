package com.control

import com.model.*
import com.sun.xml.internal.stream.writers.WriterUtility
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableStringValue
import javafx.collections.ObservableList
import org.dizitart.kno2.documentOf
import org.dizitart.kno2.filters.eq
import org.dizitart.kno2.getCollection
import org.dizitart.kno2.nitrite
import org.dizitart.kno2.tool.exportTo
import org.dizitart.kno2.tool.importFrom
import org.dizitart.no2.Document
import tornadofx.*
import java.io.File
import java.io.Writer

object LocalTranslationRepository {

    val classLoader = ClassLoader.getSystemClassLoader()

    fun createNewDBForUser(user: User) {

        val newDB = nitrite {
            file = File("src/main/resources/${user.userDBName}")
            autoCommitBufferSize = 2048
            compress = false
            autoCompact= false
        }

        newDB.importFrom(File("src/main/resources/rootDBFile"))

        for(i in 0..31) {
            val collection = newDB.getCollection(i.toString())
            collection.insert(documentOf("ownerID" to user.userDBName))

        }
    }

    fun saveToDB(list: MutableList<Sight>, dbName: String? = "rootDB") {

        val db = nitrite {
            file = File("src/main/resources/$dbName")
            autoCommitBufferSize = 2048
            compress = false
            autoCompact= false
        }

        //db.listCollectionNames().clear()

        list.forEach {
            val collection = db.getCollection("${it.sightNumber}")

            /*val ownerIDDoc = documentOf("ownerID" to it.ownerID)
            if(collection.find("ownerID" eq it.ownerID).any()) {
                println("found $it with ownerID: ${it.ownerID}, updating to $ownerIDDoc")
                collection.update("id" eq it.ownerID, ownerIDDoc)
            }*/

            it.performanceAs.forEach {
                val doc = documentOf("type" to "performanceAs","id" to it.id ,"text" to it.text)
                if(collection.find("id" eq it.id).any()) {
                    println("found $it with id: ${it.id}, updating to $doc")
                    if(doc["text"] == "") {
                        collection.remove("id" eq it.id)
                    } else {
                        collection.update("id" eq it.id, doc)
                    }
                } else {
                    println("did'nt found $it with id: ${it.id}, creating new $doc")
                    collection.insert(doc)
                }

            }
            it.nnViewOne.forEach {
                val doc = documentOf("type" to "nnViewOne", "id" to it.id , "text" to it.text)
                if(collection.find("id" eq it.id).any()) {
                    println("found $it with id: ${it.id}, updating to $doc")
                    if(doc["text"] == "") {
                        collection.remove("id" eq it.id)
                    } else {
                        collection.update("id" eq it.id, doc)
                    }
                } else {
                    println("did'nt found $it with id: ${it.id}, creating new $doc")
                    collection.insert(doc)
                }
            }
            it.nnViewTwo.forEach {
                val doc = documentOf("type" to "nnViewTwo", "id" to it.id , "text" to it.text)
                if(collection.find("id" eq it.id).any()) {
                    println("found $it with id: ${it.id}, updating to $doc")
                    if(doc["text"] == "") {
                        collection.remove("id" eq it.id)
                    } else {
                        collection.update("id" eq it.id, doc)
                    }
                } else {
                    println("did'nt found $it with id: ${it.id}, creating new $doc")
                    collection.insert(doc)
                }
            }
            it.theory.forEach {
                val doc = documentOf("type" to "theory", "id" to it.id , "text" to it.text)
                if(collection.find("id" eq it.id).any()) {
                    println("found $it with id: ${it.id}, updating to $doc")
                    if(doc["text"] == "") {
                        collection.remove("id" eq it.id)
                    } else {
                        collection.update("id" eq it.id, doc)
                    }
                } else {
                    println("did'nt found $it with id: ${it.id}, creating new $doc")
                    collection.insert(doc)
                }
            }
            collection.close()
        }
        db.close()
    }

    fun loadFromDB(dbName: String? = "rootDB") : ObservableList<Sight> {

        val db = nitrite {
            file = File("src/main/resources/$dbName")
            autoCommitBufferSize = 2048
            compress = false
            autoCompact= false
        }

        db.listCollectionNames().forEach {
            println("db collection name: $it")
        }

        var sightList = mutableListOf<Sight>().observable()

        for(i in 0..31) {
            db.getCollection(i.toString()) {
                println(this.toString())

                val performanceAsList = mutableListOf<PerformanceAs>()
                this.find("type" eq "performanceAs").forEach {
                    performanceAsList.add(PerformanceAs(it["text"].toString(), it["id"].toString()))
                }

                val nnViewOneList = mutableListOf<NNView>()
                this.find("type" eq "nnViewOne").forEach {
                    nnViewOneList.add(NNView(it["text"].toString(), it["id"].toString()))
                }

                val nnViewTwoList = mutableListOf<NNView>()
                this.find("type" eq "nnViewTwo").forEach {
                    nnViewTwoList.add(NNView(it["text"].toString(), it["id"].toString()))
                }

                val theoryList = mutableListOf<Theory>()
                this.find("type" eq "theory").forEach {
                    theoryList.add(Theory(it["text"].toString(), it["id"].toString()))
                }

                val sight = Sight(i,
                        performanceAsList.observable(),
                        nnViewOneList.observable(),
                        nnViewTwoList.observable(),
                        theoryList.observable())

                sightList.add(sight)

            }
        }
        db.close()
        return sightList
    }

}