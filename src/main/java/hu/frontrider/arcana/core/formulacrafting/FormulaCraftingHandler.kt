package hu.frontrider.arcana.core.formulacrafting

import hu.frontrider.arcana.ThaumicArcana.MODID
import net.minecraft.entity.item.EntityItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.eventhandler.Event
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.aura.AuraHelper
import thaumcraft.api.capabilities.ThaumcraftCapabilities

class FormulaCraftingHandler {


    @SubscribeEvent
    fun doCraft(event: PlayerInteractEvent.RightClickBlock) {
        val world = event.world
        if (world.isRemote)
            return

        val player = event.entityPlayer

        val itemMainhand = player.heldItemMainhand
        val itemOffhand = player.heldItemOffhand
        val recipeHashMap = FormulaRecipes.recipes
        val formulaStack = if (itemOffhand.item === formula) itemOffhand else if (itemMainhand.item === formula) itemMainhand else null
        val resourceStack = if (itemOffhand.item !== formula) itemOffhand else if (itemMainhand.item !== formula) itemMainhand else null
        if (formulaStack != null) {
            if (formulaStack.tagCompound == null)
                return

            val aspectFormula = AspectList()
            aspectFormula.readFromNBT(formulaStack.tagCompound!!)

            for (block in recipeHashMap.keys) {
                if (world.getBlockState(event.pos).block !== block)
                    continue

                val formulaApplicationRecipes = recipeHashMap[block]
                for (formulaApplicationRecipe in formulaApplicationRecipes!!) {
                    if (formulaApplicationRecipe.canCraft(block, aspectFormula, resourceStack!!)) {
                        if (!ThaumcraftCapabilities.knowsResearch(player, formulaApplicationRecipe.research))
                            return

                        if (craft(world, formulaApplicationRecipe, event.pos, formulaStack, resourceStack))
                            event.useBlock = Event.Result.DENY
                    }
                }
            }
        }
    }

    @Suppress("NAME_SHADOWING")
    private fun craft(world: World, recipe: FormulaApplicationRecipe, pos: BlockPos, formulaStack: ItemStack, usedItem: ItemStack?): Boolean {
        var pos = pos

        if (recipe.vis > 0)
            if (AuraHelper.drainVis(world, pos, recipe.vis.toFloat(), true) != recipe.vis.toFloat()) {
                return false
            }

        AuraHelper.drainVis(world, pos, recipe.vis.toFloat(), false)
        AuraHelper.polluteAura(world, pos, recipe.pollution.toFloat(), true)

        if(recipe.isConsumeBlock)
            world.setBlockToAir(pos)

        pos = pos.up()
        val entityItem = EntityItem(world)
        entityItem.item = recipe.result!!
        entityItem.setPosition(pos.x + .5, pos.y + .5, pos.z + .5)
        world.spawnEntity(entityItem)
        formulaStack.shrink(1)

        usedItem?.shrink(1)
        val sound = SoundEvent(ResourceLocation("thaumcraft:dust"))
        world.playSound(null, pos, sound, SoundCategory.AMBIENT, 1f, 1.5f)
        return true
    }

    companion object {

        @GameRegistry.ObjectHolder("$MODID:formula")
        private val formula: Item? = null
    }
}
