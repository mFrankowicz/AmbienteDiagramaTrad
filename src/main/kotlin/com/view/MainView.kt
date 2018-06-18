package com.view

import com.control.AuthController
import com.model.User
import tornadofx.*


class MainView : View ("Main") {

    private val authController: AuthController by inject()
    private val loginView: LoginView by inject()
    init {
        //find(CreateNewUserView::class).openModal(stageStyle = StageStyle.UNDECORATED)
        //find(LoginView::class).openWindow(stageStyle = StageStyle.UTILITY)
        loginView.close()
    }

    override val root = anchorpane {

        setPrefSize(900.0,700.0)

        pane {

            anchorpaneConstraints {

                leftAnchor = 50
                topAnchor = 100

            }

            hbox(10) {

                runAsync {
                    return@runAsync authController.usersMapList
                } ui {

                    children.bind(it.values.toMutableList().observable()) {
                        button(it.userName) {
                            setPrefSize(200.0, 200.0)
                            action {
                                find<TranslatorView>(mapOf(TranslatorView::user to it)).openWindow()
                            }
                        }
                    }
                }
            }
        }
    }

}