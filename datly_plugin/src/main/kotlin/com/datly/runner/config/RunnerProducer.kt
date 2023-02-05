package com.datly.runner.config

import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.RunConfigurationProducer
import com.intellij.openapi.util.Ref
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.util.keyFMap.KeyFMap

class RunnerProducer : RunConfigurationProducer<RunnerConfigBase>(Config()) {
    override fun setupConfigurationFromContext(configuration: RunnerConfigBase, context: ConfigurationContext, sourceElement: Ref<PsiElement>): Boolean {
        val file = vFile(context) ?: return false
        if (isJSON(file)) return false


        val path = file.path
        val args = KeyFMap.EMPTY_MAP
                .plus(Keys.CONFIG_URL, path)

        configuration.set(args)
        configuration.scriptName = "datly"

        setName(configuration, file.name)
        return true
    }

    private fun setName(config: RunnerConfigBase, name: String) {
        config.name = "Datly $name"
    }


    override fun isConfigurationFromContext(configuration: RunnerConfigBase, context: ConfigurationContext): Boolean {
        val file = vFile(context) ?: return false
        if (!isJSON(file)) {
            return false
        }

        return true
    }

    private fun vFile(context: ConfigurationContext): VirtualFile? {
        val location = context.location ?: return null
        return location.virtualFile
    }

    private fun isJSON(file: VirtualFile): Boolean {
        if (!file.name.contains(".json")) {
            return true
        }
        return false
    }
}