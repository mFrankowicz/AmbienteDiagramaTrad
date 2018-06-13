package com.app

import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val sight by cssclass()
        val performanceAs by cssclass()
        val nnView by cssclass()
        val theory by cssclass()
    }

    init {

        sight {
            borderColor += box(Color.BLACK)
            borderWidth += box(1.pt)
            borderStyle += BorderStrokeStyle.DASHED
        }

        performanceAs {
            backgroundColor += Color.TRANSPARENT
            padding = box(5.pt)
            //fontWeight = FontWeight.BOLD
            //65fontSize = 13.pt
            textFill = Color.BLACK
        }

        nnView {
            backgroundColor += Color.TRANSPARENT
            /*borderColor += box(Color.BLACK)
            borderWidth += box(1.pt)*/
        }

        theory {
            backgroundColor += Color.TRANSPARENT
            /*borderColor += box(Color.BLACK)
            borderWidth += box(1.pt)*/

            content {
                backgroundColor += Color.LIGHTGRAY
            }
        }

    }
}