package hu.frontrider.core.util.resources

import net.minecraft.util.ResourceLocation
import java.io.File

fun ResourceLocation.toFile(extension:String=""): File {

    var path = "/assets/$resourceDomain/$resourcePath"

    if(extension.isNotEmpty())
    {
        path =  "$path.$extension"
    }

    return File(javaClass.getResource(path).file)
}

