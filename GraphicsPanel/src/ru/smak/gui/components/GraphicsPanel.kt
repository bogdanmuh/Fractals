package ru.smak.gui.components

import ru.smak.gui.graphics.Painter
import java.awt.Graphics
import javax.swing.JPanel

class GraphicsPanel : JPanel(){
    private val painters: MutableList<Painter> = mutableListOf()

    //Переопределенный метод отрисовки панелей
    override fun paint(g: Graphics?) {
        //Отрисовывается пэинт "родителя"
        super.paint(g)
        //Отрисовывается пэинт каждого пэинтера
        painters.forEach{
            it.paint(g)
        }
    }
    /**
     * Метод для добавления пэинтеров
     * @param painter - пэинтер который нужно добавить
     */
    fun addPainter(painter: Painter){
        painters.add(painter)
        repaint()
    }

    /**
     * Метод для удаления пэинтеров
     * @param painter - пэинтер который нужно удалить
     */
    fun removePainter(painter: Painter){
        painters.remove(painter)
        repaint()
    }

}