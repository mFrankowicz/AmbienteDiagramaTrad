package com.view

import com.control.AuthController
import com.control.AuthHolder
import com.model.UserViewModel
import javafx.stage.StageStyle
import tornadofx.*

class LoginView : View("Login") {

    private val authController: AuthController by inject()
    private val userViewModel: UserViewModel by inject()
    private val mainView: MainView by inject()

    override val root = form {

        val authField = fieldset {

            field("Login") {
                textfield(userViewModel.userLogin) {
                    required()
                }
            }
            field("Password") {
                passwordfield(userViewModel.userPassword).required()
            }
            field("Remember me") {
                checkbox(property = userViewModel.remember)
            }

        }

        button("Login") {

            isDefaultButton = true

            action {
                authController.tryLogin(
                        userViewModel.userLogin.value,
                        userViewModel.userPassword.value,
                        userViewModel.remember.value
                ).let {
                    if(it.first) {
                        AuthHolder.userLogged = it.second!!
                        find(MainView::class).openWindow()
                        //this@LoginView.close()
                    } else {
                        find(BadAuthView::class).openModal(stageStyle = StageStyle.UNDECORATED)
                    }
                }
            }

        }

    }

}
