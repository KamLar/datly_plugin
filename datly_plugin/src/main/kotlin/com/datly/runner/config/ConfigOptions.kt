package com.datly.runner.config

import com.intellij.execution.configurations.RunConfigurationOptions
import com.intellij.openapi.components.StoredProperty


class ConfigOptions : RunConfigurationOptions() {
    private val myScriptName: StoredProperty<String?> = string("").provideDelegate(this, "scriptName")
    var scriptName: String
        get() = myScriptName.getValue(this) ?: "datly"
        set(scriptName) {
            myScriptName.setValue(this, scriptName)
        }
}