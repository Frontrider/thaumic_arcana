package hu.frontrider.arcana.util;

import net.minecraft.util.ResourceLocation;

public class InvalidCreatureEnchantmentException extends RuntimeException {
    public InvalidCreatureEnchantmentException(ResourceLocation wanted, ResourceLocation found){
        super("Problem while accessing creature enchants, wanted "+wanted+", but found "+found+"this error is probably caused by an another mod!");
    }
}
