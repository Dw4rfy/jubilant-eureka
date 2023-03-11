package io.github.dwarfy.utils

object IdGenerator {
    private var id = 0

    fun getNextId(): Int {
        id++
        return id
    }
}