package com.datly.runner.config

import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.ui.LabeledComponent
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import org.jetbrains.annotations.NotNull
import javax.swing.*

class ConfigEdittor: SettingsEditor<RunnerConfigBase>() {
    private val myPanel: JPanel = JPanel()
    private var myScriptName: LabeledComponent<TextFieldWithBrowseButton>? = null
    override fun resetEditorFrom(demoRunConfiguration: RunnerConfigBase) {
        myScriptName?.component?.setText(demoRunConfiguration.scriptName)
    }

    override fun applyEditorTo(@NotNull demoRunConfiguration: RunnerConfigBase) {
        demoRunConfiguration.scriptName = myScriptName?.component?.text
    }

    @NotNull
    override fun createEditor(): JComponent {
        return myPanel
    }

    private fun createUIComponents() {
        myScriptName = LabeledComponent()
        myScriptName?.component = TextFieldWithBrowseButton()
    }
}