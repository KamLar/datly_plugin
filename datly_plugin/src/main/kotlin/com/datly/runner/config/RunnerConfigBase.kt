package com.datly.runner.config

import com.intellij.execution.ExecutionException
import com.intellij.execution.Executor
import com.intellij.execution.configurations.*
import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessHandlerFactory
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.util.keyFMap.KeyFMap

class RunnerConfigBase constructor(project: Project?, factory: ConfigurationFactory?, name: String?) : RunConfigurationBase<ConfigOptions?>(project!!, factory, name) {
    override fun getOptions(): ConfigOptions {
        return super.getOptions() as ConfigOptions
    }

    var scriptName: String?
        get() = options.scriptName
        set(scriptName) {
            options.scriptName = scriptName ?: ""
        }

    override fun getConfigurationEditor(): SettingsEditor<out RunnerConfigBase?> {
        return ConfigEdittor()
    }

    override fun checkConfiguration() {}
    override fun getState(executor: Executor, executionEnvironment: ExecutionEnvironment): RunProfileState {
        val runProfile = executionEnvironment.runProfile as RunnerConfigBase
        return Runner(runProfile.get()).getState(executor, executionEnvironment)
    }
}
