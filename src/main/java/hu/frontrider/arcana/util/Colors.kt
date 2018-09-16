package hu.frontrider.arcana.util

fun convertToIntColor(r:Int, g:Int, b:Int): Int {
    return (0xFF000000 or ((r and 0xff shl 16).toLong()) or ((g and 0xff shl 8).toLong()) or ((b and 0xff).toLong())).toInt()
}