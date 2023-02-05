package com.datly.runner.pregen

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.uiDesigner.core.AbstractLayout
import com.intellij.util.ui.GridBag
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import java.awt.Dimension
import java.awt.GridBagLayout
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField

class Configurator : DialogWrapper(true) {
    val panel = JPanel(GridBagLayout())
    val txtMode = JTextField()

    fun getState(): String {
        return txtMode.text
    }

    init {
        init()
        title = "Pregenerate Datly Eule"
    }

    override fun createCenterPanel(): JComponent? {
        val gb = GridBag()
                .setDefaultInsets(JBUI.insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBag.HORIZONTAL)

        panel.preferredSize = Dimension(400, 200)
        panel.add(label("args"), gb.nextLine().next().weightx(0.2))
        panel.add(txtMode, gb.nextLine().next().weightx(0.8))

        return panel
    }

    fun label(text: String): JComponent {
        var label = JBLabel(text)
        label.componentStyle = UIUtil.ComponentStyle.SMALL
        label.fontColor = UIUtil.FontColor.BRIGHTER
        label.border = JBUI.Borders.empty(0, 5, 2, 0)
        return label
    }
}