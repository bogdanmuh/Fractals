package ru.smak.gui.graphics

import java.awt.Color
import java.awt.Graphics
import java.awt.Point

class MouseFramePainter(var g: Graphics){

    var startPoint: Point? = null
    var currentPoint: Point? = null
        set(value) {
            paint()
            field = value
            paint()
        }
    var isVisible = false
        set(value) {
            field = value
            if (value){
                currentPoint = null
                startPoint = null
            }
        }

    private fun paint(){
        if (isVisible){
            //let   блокирует потоки и и проверяет не равны ли s c null
            startPoint?.let{s ->
                currentPoint?.let {c ->
                    g.setXORMode(Color.WHITE)
                    g.color = Color.BLACK
                    if (s.x < c.x && s.y < c.y ) g.drawRect(s.x, s.y, c.x-s.x, c.y-s.y) //4 четверть
                    else if (s.x < c.x && s.y> c.y ) g.drawRect(s.x,c.y ,c.x-s.x, s.y-c.y) //1
                    else if (s.x > c.x && s.y> c.y ) g.drawRect(c.x, c.y, s.x-c.x, s.y-c.y) //2
                    else  g.drawRect(c.x, s.y, s.x-c.x, c.y-s.y)// 3
                    g.setPaintMode()
                }
            }

        }
    }

    fun repaint(graphics: Graphics) {
        this.g =graphics

    }


}