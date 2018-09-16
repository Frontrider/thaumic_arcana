package hu.frontrider.arcana.sided.client.jei

import hu.frontrider.arcana.ThaumicArcana.MODID
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.ISubtypeRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

@JEIPlugin
class ThaumicArcanaJEIPlugin: IModPlugin {

    companion object {
        @GameRegistry.ObjectHolder(MODID+":formula")
        lateinit var formula: Item
    }

    override fun registerItemSubtypes(subtypeRegistry: ISubtypeRegistry) {
            subtypeRegistry.registerSubtypeInterpreter(formula) {
                if(it.hasTagCompound()) {
                    it.tagCompound!!.toString()
                }else ""
            }
    }

    override fun register(registry: IModRegistry) {

    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        registry.addRecipeCategories(
                ApplicationRecipeCategory()
        )
    }
}