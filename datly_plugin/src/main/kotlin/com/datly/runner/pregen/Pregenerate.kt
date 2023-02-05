package com.datly.runner.pregen

import com.datly.runner.config.*
import com.intellij.execution.ProgramRunnerUtil
import com.intellij.execution.RunManager
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.execution.runners.ExecutionEnvironmentBuilder
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.actionSystem.Presentation
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.keyFMap.KeyFMap
import org.apache.tools.ant.types.Commandline


class Pregenerate : AnAction() {
    override fun update(event: AnActionEvent) {
        val aFile = vFile(event) ?: return
        if (aFile.name.contains(".sql")) {
            toggleVisibility(event.presentation, true)
        } else {
            toggleVisibility(event.presentation, false)
        }
    }

    private fun vFile(event: AnActionEvent): VirtualFile? {
        return event.getData(PlatformDataKeys.VIRTUAL_FILE)
    }


    private fun toggleVisibility(presentation: Presentation, value: Boolean) {
        presentation.isVisible = value
        presentation.isEnabled = value
    }

    override fun actionPerformed(e: AnActionEvent) {
        val file = vFile(e) ?: return
        val project = e.project ?: return

        val configurator = Configurator()
        if (!configurator.showAndGet()) {
            return
        }

        val config = Config()
        val factory = ConfigFactory(config)
        val runnerConfigBase = RunnerConfigBase(project, factory, "Pregenerate " + file.name)

        val args = prepareArgs(file, configurator)
        runnerConfigBase.set(args)
        val executionEnvironment = ExecutionEnvironmentBuilder.create(DefaultRunExecutor.getRunExecutorInstance(), runnerConfigBase)
                .runProfile(Runner(args))
                .build()

        ProgramRunnerUtil.executeConfiguration(executionEnvironment, false, true)


        val configuration = RunManager.getInstance(project).createConfiguration(runnerConfigBase, factory)
        RunManager.getInstance(project).addConfiguration(configuration)
    }

    private fun prepareArgs(file: VirtualFile, configurator: Configurator): KeyFMap {
        var args = KeyFMap.EMPTY_MAP.plus(Keys.SQLX_LOCATION, file.path)
        val cmdArgs = Commandline.translateCommandline(configurator.getState())
        cmdArgs.iterator().forEachRemaining { arg ->
            val split = arg.split("=")
            if (split.size == 2) {
                args = args.plus(Key(split[0]), split[1])
            }
        }

        return args
    }
}