package hu.frontrider.arcana.recipes

import hu.frontrider.arcana.AspectEffectMap
import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.items.ItemInfusedSlime
import hu.frontrider.arcana.supportedAspects
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionEffect
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
            val aspect = supportedAspects[ingredient.metadata]
            val potion = AspectEffectMap.map[aspect]

            val effectsFromStack = PotionUtils.getEffectsFromStack(input)

            val effect = if (potion != null) effectsFromStack.asSequence().filter {
                it.potion == potion.potion
            }.firstOrNull()
            else null

            val result = if (effect != null) {
                effectsFromStack.remove(effect)
                PotionUtils.appendEffects(
                        input.copy(),
                        listOf(PotionEffect(effect.potion, effect.duration + 2, effect.amplifier),
                                *effectsFromStack.toTypedArray()))

            } else {
                when (aspect) {
                    Aspect.ORDER -> {
                        val last = effectsFromStack.first() ?: return input

                        effectsFromStack.remove(last)
                        PotionUtils.appendEffects(
                                input.copy(),
                                listOf(PotionEffect(last.potion, last.duration * 2, last.amplifier), *effectsFromStack.toTypedArray()))
                    }
                    Aspect.ALCHEMY -> {
                        val last = effectsFromStack.first() ?: return input

                        if (last.amplifier > 2) {
                            return input
                        }

                        effectsFromStack.remove(last)
                        PotionUtils.appendEffects(
                                input.copy(),
                                listOf(PotionEffect(last.potion, last.duration, last.amplifier + 1), *effectsFromStack.toTypedArray()))
                    }
                    else ->
                        if (potion != null) {
                            PotionUtils.appendEffects(
                                    input.copy(),
                                    listOf(PotionEffect(potion.potion, potion.duration, 0),
                                            *effectsFromStack.toTypedArray()))
                        } else {
                            input
                        }
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
                tagCompound.setString("Potion", "minecraft:thick")
            }

            result.tagCompound = tagCompound

            return result
        }
        return ItemStack.EMPTY
    }

    override fun isInput(ingredient: ItemStack): Boolean {
        return isAwkwardPotion(ingredient) && canBeBrewed(ingredient)
    }

    private fun isAwkwardPotion(ingredient: ItemStack): Boolean {
        ingredient.tagCompound!!.apply {
            return if (hasKey("Potion")) {
                val type = getString("Potion")
                type == "minecraft:awkward" || type == "minecraft:thick"
            } else {
                false
            }
        }
        return false
    }

    private fun canBeBrewed(ingredient: ItemStack): Boolean {
        ingredient.tagCompound!!.apply {
            return if (hasKey("brewCount")) {
                getInteger("brewCount") < 5
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