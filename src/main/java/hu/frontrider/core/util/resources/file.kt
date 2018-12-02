package hu.frontrider.core.util.resources

import java.io.File
import java.util.*

fun File.readToString(): String {
    val scanner = Scanner(this.inputStream())
    var result = ""
    while(scanner.hasNextLine()){
        result +=scanner.nextLine()
    }
    return result
}