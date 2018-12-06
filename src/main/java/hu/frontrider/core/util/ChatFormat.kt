@file:Suppress("NAME_SHADOWING")

package hu.frontrider.core.util

import net.minecraft.client.resources.I18n

enum class ChatFormat(val tag: String) {

    //colors
    BLACK("§0"),
    DARK_BLUE("§1"),
    DARK_GREEN("§2"),
    DARK_AQUA("§3"),
    DARK_RED("§4"),
    DARK_PURPLE("§5"),
    GOLD("§6"),
    GRAY("§7"),
    DARK_GRAY("§8"),
    BLUE("§9"),
    GREEN("§a"),
    AQUA("§b"),
    RED("§c"),
    LIGHT_PURPLE("§d"),
    YELLOW("§e"),
    WHITE("§f"),

    //formats
    OBFUSCATED("§k"),
    BOLD("§l"),
    STRIKE_THROUGH("§m"),
    UNDERLINE("§n"),
    ITALIC("§o"),

    RESET("§r")

}

fun formatString(text:String,vararg formats : ChatFormat): String {
    var text = text;
    formats.iterator().forEachRemaining{
        text = text.prepend(it.tag)
    }
    return text+ ChatFormat.RESET.tag
}

private fun String.prepend(s:String):String{
    return s+this
}

fun formatTranslate(unlocalizedName:String,arg:String,vararg formats: ChatFormat): String{
    return formatString(I18n.format(unlocalizedName, arg), *formats)

}

fun formatTranslate(unlocalizedName:String,vararg formats: ChatFormat): String {
    return formatString(I18n.format(unlocalizedName), *formats)
}

fun parseSpecialFormatting(text: String): String {
    return text.replace("<BR>","\n")
}

fun translateFormatting(unlocalizedName:String): String {
 return "  "+parseSpecialFormatting(I18n.format(unlocalizedName))
}