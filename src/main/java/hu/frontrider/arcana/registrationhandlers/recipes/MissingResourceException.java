package hu.frontrider.arcana.registrationhandlers.recipes;

import net.minecraft.util.ResourceLocation;

public class MissingResourceException extends RuntimeException {
    public MissingResourceException(ResourceLocation resourceLocation, Class clazz, String message){
        super("Resource wasn't found at "+resourceLocation+" in "+clazz.getSimpleName()+": \n"+message);
    }
}
