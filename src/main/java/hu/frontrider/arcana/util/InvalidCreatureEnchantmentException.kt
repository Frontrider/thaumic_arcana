package hu.frontrider.arcana.util

import net.minecraft.util.ResourceLocation

class InvalidCreatureEnchantmentException(wanted: ResourceLocation, found: ResourceLocation) : RuntimeException("Problem while accessing creature enchants, wanted " + wanted + ", but found " + found + "this error is probably caused by an another mod!")
