package hu.frontrider.arcana.util;

import hu.frontrider.arcana.creatureenchant.backend.CEnchantment;

public class InvalidCreatureEnchantmentException extends RuntimeException {
    public InvalidCreatureEnchantmentException(CEnchantment wanted, CEnchantment found){
        super("Problem while accessing creature enchants, wanted "+wanted+", but found "+found+"this error is probably caused by an another mod!");
    }
}
