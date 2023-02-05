package com.datly.runner.config

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.components.BaseState
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.Nullable

class ConfigFactory(type: ConfigurationType) : ConfigurationFactory(type) {
    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        return RunnerConfigBase(project, this , Config.ID)
    }

    override fun getId(): String {
        return Config.ID
    }

    @Nullable
    override fun getOptionsClass(): Class<out BaseState> {
        return ConfigOptions::class.java
    }
}