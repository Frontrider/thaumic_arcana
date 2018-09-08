package hu.frontrider.arcana.util

import com.google.common.base.Function


/**
 * This class is used to make it easier to fill up lists.
 * Mostly for reccipes.
 */
class ListBuilder<T>(private val list: MutableList<T>) {

    fun add(element: T): ListBuilder<T> {
        list.add(element)
        return this
    }

    fun build(): List<T> {

        return list
    }

    fun print(printer: Function<T, String>): ListBuilder<T> {

        list.forEach { t -> println(printer.apply(t)) }

        return this
    }
}
