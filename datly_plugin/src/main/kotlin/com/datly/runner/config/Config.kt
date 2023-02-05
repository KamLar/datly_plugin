package com.datly.runner.config

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.icons.AllIcons
import javax.swing.Icon

class Config : ConfigurationType {
    override fun getDisplayName(): String {
        return "Datly"
    }

    override fun getConfigurationTypeDescription(): String {
        return "Datly runner configuration"
    }

    override fun getIcon(): Icon {
        return AllIcons.General.Information;
    }

    override fun getId(): String {
       return ID
    }

    override fun getConfigurationFactories(): Array<ConfigurationFactory> {
        return arrayOf(ConfigFactory(this))
    }

    companion object {
        val ID = "Datly Run Configuration"
    }
}