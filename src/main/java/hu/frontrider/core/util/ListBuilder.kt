package hu.frontrider.core.util



/**
 * This class is used to make it easier to fill up lists.
 * Mostly for recipes.
 */
class ListBuilder<T>(private val list: MutableList<T>) {

    fun add(element: T): ListBuilder<T> {
        list.add(element)
        return this
    }

    fun build(): List<T> {

        return list
    }

    /**
     * Function that converts the elements to string, than prints it
     * */
    fun print(printer: (T)-> String): ListBuilder<T> {

        list.forEach { t -> println(printer(t)) }

        return this
    }
}
