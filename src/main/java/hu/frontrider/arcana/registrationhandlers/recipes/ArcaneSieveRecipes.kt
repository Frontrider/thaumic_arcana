package hu.frontrider.arcana.registrationhandlers.recipes

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.api.ArcaneSieveRecipeRegistryEvent
import hu.frontrider.arcana.api.SimpleArcaneSieveRecipe
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry

class ArcaneSieveRecipes {

    companion object {
        @GameRegistry.ObjectHolder("$MODID:empty_soul_capsule")
        lateinit var emplty_soul_capsule: Item
        @GameRegistry.ObjectHolder("$MODID:soul_capsule")
        lateinit var soul_capsule:Item
    }

    @SubscribeEvent
    fun registerRecipes(event:ArcaneSieveRecipeRegistryEvent){
        event.registerRecipes(
                SimpleArcaneSieveRecipe(
                        ItemStack(Items.GHAST_TEAR,5),
                        ItemStack(emplty_soul_capsule),
                        ItemStack(Blocks.SOUL_SAND),
                        ItemStack(soul_capsule)),
                SimpleArcaneSieveRecipe(
                        ItemStack(Blocks.DIRT,20),
                        null,
                        ItemStack(Blocks.WEB),
                        ItemStack(Items.WHEAT_SEEDS))
        )
    }
}