package com.view

import tornadofx.*

class BadAuthView : View() {

    override val root = anchorpane {

        setPrefSize(400.0,200.0)

        vbox {

            label("Login ou Senha incorretos!")

            button("Ok") {
                action {
                    this@BadAuthView.close()
                }
            }
        }
    }
}