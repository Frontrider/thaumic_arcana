package hu.frontrider.arcana.core.eventhandlers

import net.minecraft.enchantment.Enchantment
import net.minecraftforge.fml.common.registry.GameRegistry

class ToolEvents {
    companion object {

        @GameRegistry.ObjectHolder("minecraft:fortune")
        lateinit var fortune:Enchantment
    }

}