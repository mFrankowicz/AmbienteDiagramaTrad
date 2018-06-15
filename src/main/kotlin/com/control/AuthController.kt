package com.control

import com.model.User
import tornadofx.*
import java.util.*

class AuthController : Controller() {

    var usersMapList = mutableMapOf<String, User>().observable()

    fun tryLogin(userlogin: String, userPassword: String, remember: Boolean) : Pair<Boolean, User?> {

        val successLogin =  LocalAuthRepository.loadCredentials(userlogin, userPassword)
        return if(successLogin != null) {
            loadUsersMapList()
            println("""Success login user:
                            | name: ${successLogin.userName}
                            | login: ${successLogin.userLogin}
                            | password: ${successLogin.userPassword}
                            | UUID: ${successLogin.userInternalID}
                            | dbName: ${successLogin.userDBName}
                        """.trimMargin())
            Pair(true, successLogin)
        } else {
            Pair(false, null)
        }

    }

    fun createUser(userlogin: String, userPassword: String, userName: String) {

        val newUser = User(userlogin, userPassword, userName, UUID.randomUUID().toString())
        LocalAuthRepository.createCredentials(newUser)
        LocalTranslationRepository.createNewDBForUser(newUser)
    }

    fun loadUsersMapList(){
        usersMapList = mutableMapOf<String, User>().observable()
        usersMapList = LocalAuthRepository.loadUsers()
    }

    companion object {
        const val USERLOGIN = "userlogin"
        const val USERPASSWORD = "userpassword"
    }

}