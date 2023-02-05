package com.datly.runner.config

import com.intellij.execution.ExecutionException
import com.intellij.execution.Executor
import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.configurations.RunProfile
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessHandlerFactory
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.util.keyFMap.KeyFMap
import javax.swing.Icon

class Runner(state: KeyFMap): RunProfile {
    private val myState: KeyFMap

    init {
        myState = state
    }


    companion object {
        fun runDatly(args: KeyFMap): OSProcessHandler {
            val commandLine = GeneralCommandLine("datly")
            val keys = args.keys

            val holder = KeysHolder(args)
            keys.iterator().forEachRemaining { aKey ->
                val keyValue = aKey.toString()
                val value = aKey.get(holder).toString()
                commandLine.addParameter("$keyValue=$value")
            }

            val instance = ProcessHandlerFactory.getInstance()
            val processHandler = instance.createColoredProcessHandler(commandLine)
            ProcessTerminatedListener.attach(processHandler)
            return processHandler
        }
    }


    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState {
        return object : CommandLineState(environment) {
            @Throws(ExecutionException::class)
            override fun startProcess(): ProcessHandler {
                return runDatly(myState)
            }
        }
    }

    override fun getName(): String {
        return "Datly"
    }

    override fun getIcon(): Icon? {
        return null
    }
}