package com.control

import com.model.User
import javafx.collections.ObservableMap
import org.dizitart.kno2.documentOf
import org.dizitart.kno2.filters.and
import org.dizitart.kno2.filters.eq
import org.dizitart.kno2.nitrite
import tornadofx.*
import java.io.File

object LocalAuthRepository {

    private val db = nitrite {

        file = File("src/main/resources/authDB")
        autoCommitBufferSize = 2048
        compress = false
        autoCompact= false

    }

    private const val userCollection = "userCollection"

    fun loadCredentials(login: String, password: String) : User? {

        val userList = db.getCollection(userCollection)
        var userFound: User? =  null
        userList.find(("userLogin" eq login) and ("userPassword" eq password)).forEach {
            val userLogin = (it["userLogin"]).toString()
            val userPassword = (it["userPassword"]).toString()
            val userName = (it["userName"]).toString()
            val userInternalID = (it["userInternalID"]).toString()
            userFound = User(userLogin, userPassword, userName, userInternalID)
        }
        return userFound
    }

    fun loadUsers() : ObservableMap<String, User> {
        val userList = db.getCollection(userCollection)
        var users = mutableMapOf<String, User>().observable()
        userList.find().forEach {
            val userLogin = (it["userLogin"]).toString()
            val userPassword = (it["userPassword"]).toString()
            val userName = (it["userName"]).toString()
            val userInternalID = (it["userInternalID"]).toString()
            val user = User(userLogin, userPassword, userName, userInternalID)
            users[userInternalID] = user
        }
        userList.close()
        return users
    }

    fun createCredentials(user: User) {

        val userLogin = ("userLogin" to user.userLogin)
        val userPassword = ("userPassword" to user.userPassword)
        val userName = ("userName" to user.userName)
        val userInternalID = ("userInternalID" to user.userInternalID)
        val userDBName = ("userDBName" to user.userDBName)

        val userDocument = documentOf(userLogin, userPassword, userName, userInternalID, userDBName)

        val userList = db.getCollection(userCollection)

        userList.insert(userDocument)
        userList.close()

    }

}