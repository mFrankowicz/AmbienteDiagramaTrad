package com.view

import com.control.AuthController
import com.control.TranslationController
import com.model.UserViewModel
import tornadofx.*

class CreateNewUserView : View() {

    private val authController: AuthController by inject()
    private val translationController: TranslationController by inject()
    private val userViewModel: UserViewModel by inject()

    override val root = form {

        fieldset {

            field("Login") {
                textfield(userViewModel.userLogin).required()
            }
            field("Password") {
                passwordfield(userViewModel.userPassword).required()
            }
            field("User Name") {
                textfield(userViewModel.userName).required()
            }

        }

        button("Create User") {

            isDefaultButton = true

            action {
                authController.createUser(
                        userViewModel.userLogin.value,
                        userViewModel.userPassword.value,
                        userViewModel.userName.value
                )

            }

        }

    }

}