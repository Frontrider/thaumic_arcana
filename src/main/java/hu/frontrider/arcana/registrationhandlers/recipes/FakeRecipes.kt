package hu.frontrider.arcana.registrationhandlers.recipes

import hu.frontrider.arcana.ThaumicArcana.MODID
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.crafting.RecipeMisc

class FakeRecipes {

    fun init() {
        initSmelting()
    }

    fun initSmelting(){
        ThaumcraftApi.addFakeCraftingRecipe(ResourceLocation("$MODID:smelt_slime_meat"),RecipeMisc(ItemStack(rawSlimeMeat),ItemStack(cookedSlimeMeat),RecipeMisc.MiscRecipeType.SMELTING))
    }

    companion object {
        @GameRegistry.ObjectHolder("$MODID:slime_meat_raw")
        lateinit var rawSlimeMeat: Item

        @GameRegistry.ObjectHolder("$MODID:slime_meat_cooked")
        lateinit var cookedSlimeMeat: Item
    }

}