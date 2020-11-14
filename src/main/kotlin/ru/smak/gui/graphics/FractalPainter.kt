package ru.smak.gui.graphics

import ru.smak.gui.graphics.convertation.CartesianScreenPlane
import ru.smak.gui.graphics.convertation.Converter
import ru.smak.math.Complex
import java.awt.Color
import java.awt.Graphics
import kotlin.math.abs
import kotlin.math.max

class FractalPainter(
    val plane: CartesianScreenPlane
    ) : Painter {

    var fractalTest : ((Complex)->Float)? = null
    var getColor: ((Float)->Color) = { x -> Color(x, x, x)}

    /**
     * Рисование фрактала
     * @param g графический контекст для рисования
     */
    override fun paint(g: Graphics?) {
        //Выход из метода,если функциональная переменная не задана
        //Или нет графического контекста для рисования
        if (fractalTest==null || g==null) return
        for (i in 0..plane.width){
            for (j in 0..plane.height){
                //Для многопоточности комплексное число может меняться
                //Поэтому выходим,если не данный поток вызывал fractalTest
                val r = fractalTest?.invoke(
                        Complex(
                                Converter.xScr2Crt(i, plane),
                                Converter.yScr2Crt(j, plane)
                        )
                ) ?: return
                //Окрашиваем точку в зависимости принадлежит она множеству или нет
                g.color = if (r == 1F) Color.BLACK else getColor(r)
                g.fillRect(i, j, 1, 1)
            }
        }
    }
}

private infix fun Float.eq(other: Float) =
        abs(this - other) < max(Math.ulp(this), Math.ulp(other)) * 2
private infix fun Float.neq(other: Float) =
        abs(this - other) > max(Math.ulp(this), Math.ulp(other)) * 2