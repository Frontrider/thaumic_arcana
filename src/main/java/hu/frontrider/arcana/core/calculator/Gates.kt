package hu.frontrider.arcana.core.calculator

/**
 * Storing the components
 */
enum class Gates {
    //NOT and NOR are the minimum required gates to construct any other gates.
    NOT,NOR,
    //to connect logic nodes
    CONNECTOR,CONNECTOR_OFF
}
//side compatibility
enum class Sides {
    EAST, WEST, SOUTH, NORTH, UP, DOWN
}

open class Gate(val state:Boolean)


