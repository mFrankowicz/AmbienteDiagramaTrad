package com.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class User(userLogin: String,
           userPassword: String,
           userName: String,
           userInternalID: String
           ) {

    val userLoginProperty = SimpleStringProperty(userLogin)
    var userLogin by userLoginProperty

    val userPasswordProperty = SimpleStringProperty(userPassword)
    var userPassword by userPasswordProperty

    val userNameProperty = SimpleStringProperty(userName)
    var userName by userNameProperty

    val userInternalIDProperty = SimpleStringProperty(userInternalID)
    var userInternalID by userInternalIDProperty

    val rememberProperty = SimpleBooleanProperty(false)
    var remember by rememberProperty

    val userDBNameProperty = SimpleStringProperty("$userInternalID-userDB")
    val userDBName by userDBNameProperty

}

class UserViewModel : ItemViewModel<User>() {

    val userLogin = bind(autocommit = true) {
        item?.userLoginProperty
    }

    val userPassword = bind(autocommit = true) {
        item?.userPasswordProperty
    }

    val userName = bind(autocommit = true) {
        item?.userNameProperty
    }

    val userInternalID = bind(autocommit = true) {
        item?.userInternalIDProperty
    }

    val remember = bind(autocommit = true) {
        item?.rememberProperty
    }

    val userDBName = bind(autocommit = true) {
        item?.userDBNameProperty
    }

}