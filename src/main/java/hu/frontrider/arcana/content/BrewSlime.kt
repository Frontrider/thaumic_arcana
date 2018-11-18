package hu.frontrider.arcana.content

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.content.items.ItemInfusedSlime
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionEffect
import net.minecraft.potion.PotionHelper
import net.minecraft.potion.PotionUtils
import net.minecraftforge.common.brewing.IBrewingRecipe
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.aspects.Aspect

class BrewSlime : IBrewingRecipe {
    override fun isIngredient(ingredient: ItemStack): Boolean {
        return ingredient.item == infusedSlime
    }

    override fun getOutput(input: ItemStack, ingredient: ItemStack): ItemStack {
        if (!isInput(input)) return ItemStack.EMPTY

        (ingredient.item as? ItemInfusedSlime).apply {
            val ingredientTag = ingredient.tagCompound ?: return ItemStack.EMPTY
            if (ingredientTag.hasKey("aspect")) {

                val aspect = Aspect.getAspect(ingredientTag.getString("aspect"))
                val potion = AspectEffectMap.map[aspect]!!

                val effectsFromStack = PotionUtils.getEffectsFromStack(input)

                val effect = effectsFromStack.asSequence().filter {
                    it.potion == potion.potion
                }.firstOrNull()

                val result = if (effect != null) {
                    if (aspect == Aspect.ORDER) {
                        val last = effectsFromStack.last()
                        effectsFromStack.remove(last)
                        PotionUtils.appendEffects(
                                input.copy(),
                                listOf(PotionEffect(effect.potion, effect.duration, effect.amplifier + 1), *effectsFromStack.toTypedArray()))
                    } else {

                        effectsFromStack.remove(effect)
                        PotionUtils.appendEffects(
                                input.copy(),
                                listOf(PotionEffect(effect.potion, effect.duration * 2, effect.amplifier), *effectsFromStack.toTypedArray()))
                    }

                } else {
                    if (aspect != Aspect.ORDER) {
                        PotionUtils.appendEffects(
                                input.copy(),
                                listOf(PotionEffect(potion.potion, potion.duration), *effectsFromStack.toTypedArray()))
                    } else {
                        return input
                    }
                }

                val tagCompound = result.tagCompound!!
                if (tagCompound.hasKey("brewCount")) {
                    val brewCount = tagCompound.getInteger("brewCount") + 1
                    tagCompound.setInteger("brewCount", brewCount)
                } else {
                    tagCompound.setInteger("brewCount", 1)
                }
                val string = tagCompound.getString("Potion")

                if (string == "minecraft:awkward") {

                }
                result.tagCompound = tagCompound

                return result;

            }
        }
        return ItemStack.EMPTY
    }

    override fun isInput(ingredient: ItemStack): Boolean {
        return if (!ingredient.hasTagCompound())
            false
        else
            isAwkwardPotion(ingredient) && canBeBrewed(ingredient)
    }

    private fun isAwkwardPotion(ingredient: ItemStack): Boolean {
        ingredient.tagCompound!!.apply {
            return if (hasKey("Potion")) {
                val type = getString("Potion")
                type == "minecraft:awkward"
            } else {
                false
            }
        }
        return false
    }

    private fun canBeBrewed(ingredient: ItemStack): Boolean {
        ingredient.tagCompound!!.apply {
            return if (hasKey("brewCount")) {
                getInteger("brewCount") < 3
            } else {
                true
            }
        }
        return true
    }

    companion object {
        @GameRegistry.ObjectHolder("$MODID:infused_slime")
        lateinit var infusedSlime: Item
    }
}