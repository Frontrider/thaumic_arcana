package hu.frontrider.arcana.registrationhandlers.recipes

import net.minecraft.item.EnumDyeColor
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.ThaumcraftApiHelper
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.blocks.BlocksTC
import thaumcraft.api.crafting.InfusionRecipe

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.content.items.EnchantModifierDust
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle
import hu.frontrider.arcana.registrationhandlers.ItemRegistry.Companion.enchanting_powder_advanced
import hu.frontrider.arcana.registrationhandlers.ItemRegistry.Companion.enchanting_powder_basic
import net.minecraft.init.Items.DYE
import net.minecraft.init.Items.IRON_INGOT
import net.minecraft.nbt.NBTTagCompound
import thaumcraft.api.items.ItemsTC.salisMundus

class InfusionRecipes {

    companion object {
        @GameRegistry.ObjectHolder("$MODID:enchant_modifier")
        internal lateinit var modifier: Item


        @GameRegistry.ObjectHolder("$MODID:ingot_livium")
        lateinit var ingot_livium: Item

        @GameRegistry.ObjectHolder("$MODID:neutered_flesh")
        lateinit var neutered_flesh: Item

    }

    fun register() {
        registerCreatureEnchants()
        initLivium()
    }

    internal fun registerCreatureEnchants() {

        run {
            val source = ItemStack(enchanting_powder_basic)
            source.tagCompound = null

            val itemStack = ItemStack(enchanting_powder_advanced)
            ThaumcraftApi.addInfusionCraftingRecipe(
                    ResourceLocation(MODID, "enchant_powder_advanced"),
                    InfusionRecipe("CREATURE_ENCHANT_ADVANCED",
                            itemStack,
                            5, AspectList()
                            .add(Aspect.MAGIC, 20)
                            .add(Aspect.LIFE, 50)
                            .add(Aspect.AURA, 20),
                            source,
                            ItemStack(salisMundus),
                            ItemStack(DYE, 1, 4),
                            ItemStack(salisMundus),
                            ItemStack(DYE, 1, 4),
                            ItemStack(salisMundus),
                            ItemStack(DYE, 1, 4)
                    )
            )
        }
        run {
            val itemStack = ItemStack(modifier)

            val source = ItemStack(enchanting_powder_advanced)
            source.tagCompound = null

            ThaumcraftApi.addInfusionCraftingRecipe(
                    ResourceLocation(MODID, "enchant_modifier_base"),
                    InfusionRecipe("ENCHANT_MODIFICATION",
                            itemStack,
                            5,
                            AspectList()
                                    .add(Aspect.MAGIC, 20)
                                    .add(Aspect.LIFE, 50)
                                    .add(Aspect.AURA, 20)
                                    .add(Aspect.BEAST, 100),
                            source,
                            ItemStack(BlocksTC.nitor[EnumDyeColor.WHITE]!!),
                            ItemStack(BlocksTC.nitor[EnumDyeColor.WHITE]!!),
                            ItemStack(BlocksTC.nitor[EnumDyeColor.WHITE]!!),
                            ItemStack(BlocksTC.nitor[EnumDyeColor.WHITE]!!),
                            ThaumcraftApiHelper.makeCrystal(Aspect.ORDER),
                            ThaumcraftApiHelper.makeCrystal(Aspect.ORDER),
                            ThaumcraftApiHelper.makeCrystal(Aspect.MAGIC),
                            ThaumcraftApiHelper.makeCrystal(Aspect.MAGIC)
                    )
            )
        }

    }

    fun initLivium(){
        run {
            val source = ItemStack(neutered_flesh)

            val itemStack = ItemStack(ingot_livium)
            ThaumcraftApi.addInfusionCraftingRecipe(
                    ResourceLocation(MODID, "create_livium"),
                    InfusionRecipe("LIVIUM",
                            itemStack,
                            2, AspectList()
                            .add(Aspect.METAL, 30)
                            .add(Aspect.LIFE, 50),
                            source,
                            ItemStack(IRON_INGOT),
                            ItemStack(IRON_INGOT),
                            ItemStack(IRON_INGOT)
                    )
            )
        }
    }
}
