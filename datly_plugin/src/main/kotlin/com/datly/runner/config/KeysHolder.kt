package com.datly.runner.config

import com.intellij.openapi.util.Key
import com.intellij.openapi.util.UserDataHolder
import com.intellij.util.keyFMap.KeyFMap

class KeysHolder(fMap: KeyFMap) : UserDataHolder {
    private val fMap: KeyFMap

    init {
        this.fMap = fMap
    }

    override fun <T : Any?> getUserData(key: Key<T>): T? {
        return this.fMap.get(key)
    }

    override fun <T : Any?> putUserData(key: Key<T>, value: T?) {
        if (value == null) {
            return
        }

        this.fMap.plus(key, value)
    }

}
