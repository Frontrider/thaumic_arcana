package hu.frontrider.arcana.registrationhandlers.recipes

import hu.frontrider.arcana.util.ListBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.crafting.CraftingHelper
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.crafting.ShapedArcaneRecipe
import thaumcraft.api.crafting.ShapelessArcaneRecipe

import hu.frontrider.arcana.ThaumicArcana.MODID
import net.minecraft.block.Block
import net.minecraft.init.Items
import thaumcraft.api.ThaumcraftApiHelper


class ArcaneCraftingRecipes {
    private val defaultGroup = ResourceLocation(MODID,"biomancy")

    fun register() {
        initRecipes()
        initLivium()
    }

    private fun initRecipes() {

        run {
            val cagePrimer = CraftingHelper.ShapedPrimer()
            cagePrimer.height = 3
            cagePrimer.width = 3

            cagePrimer.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromItem(bottle!!))
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.fromItem(bottle!!))
                    .add(Ingredient.fromItem(plank_greatwood!!))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(plank_greatwood!!))
                    .add(Ingredient.fromItem(plank_greatwood!!))
                    .add(Ingredient.fromItem(bowl!!))
                    .add(Ingredient.fromItem(plank_greatwood!!))
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_table"),
                    ShapedArcaneRecipe(defaultGroup,
                            "BIOMANCY_BASICS",
                            30,
                            AspectList().add(Aspect.WATER, 1).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1),
                            ItemStack(experiment_table!!),
                            cagePrimer))
        }

        run {
            val book = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromItem(Items.BOOK!!))
                    .add(Ingredient.fromItem(sal_mundi!!))
                    .add(Ingredient.fromItem(sal_mundi!!))
                    .add(Ingredient.fromItem(sal_mundi!!))
                    .add(Ingredient.fromStacks(ThaumcraftApiHelper.makeCrystal(Aspect.LIFE)))
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_book"),
                    ShapelessArcaneRecipe(defaultGroup,
                            "BIOMANCY_BASICS",
                            30,
                            AspectList().add(Aspect.ENTROPY, 3).add(Aspect.EARTH, 1).add(Aspect.AIR, 1),
                            ItemStack(enchanter!!),
                            book.toArray()))
        }
        run {
            val cagePrimer = CraftingHelper.ShapedPrimer()
            cagePrimer.height = 3
            cagePrimer.width = 3

            cagePrimer.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ThaumcraftApiHelper.makeCrystal(Aspect.AURA)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "arcane_stone_enable_enchants"),
                    ShapedArcaneRecipe(defaultGroup,
                            "ENCHANT_FOCUS",
                            20,
                            AspectList().add(Aspect.ORDER, 2).merge(Aspect.EARTH,1).merge(Aspect.WATER,1),
                            ItemStack(enable_enchants!!,4),
                            cagePrimer))
        }

        run {
            val cagePrimer = CraftingHelper.ShapedPrimer()
            cagePrimer.height = 3
            cagePrimer.width = 3

            cagePrimer.input =ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ThaumcraftApiHelper.makeCrystal(Aspect.FLUX)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .add(Ingredient.fromStacks(ItemStack(arcane_stone!!)))
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "arcane_stone_disable_enchants"),
                    ShapedArcaneRecipe(defaultGroup,
                            "ENCHANT_FOCUS",
                            20,
                            AspectList().add(Aspect.ENTROPY, 2).merge(Aspect.EARTH,1).merge(Aspect.WATER,1),
                            ItemStack(disable_enchants!!,4),
                            cagePrimer))
        }
    }

    private fun initLivium(){
        val liviumGroup = ResourceLocation(MODID,"biomancy_livium")
        run {
            val fleshGroup = ResourceLocation(MODID,"biomancy_livium_flesh")
            for(item in arrayOf(Items.BEEF,Items.MUTTON,Items.PORKCHOP,Items.CHICKEN,Items.FISH)) {
                val cagePrimer = CraftingHelper.ShapedPrimer()
                cagePrimer.height = 3
                cagePrimer.width = 3

                cagePrimer.input = ListBuilder(NonNullList.create<Ingredient>())
                        .add(Ingredient.fromItem(item))
                        .build() as NonNullList<Ingredient>

                ThaumcraftApi.addArcaneCraftingRecipe(
                        ResourceLocation(MODID, "neutered_flesh_${item.registryName!!.resourcePath}"),
                        ShapedArcaneRecipe(fleshGroup,
                                "LIVIUM",
                                30,
                                AspectList().add(Aspect.ENTROPY, 1).add(Aspect.ORDER, 1),
                                ItemStack(neutered_flesh),
                                cagePrimer))
            }
        }

        run {
            val tool = CraftingHelper.ShapedPrimer()
            tool.height = 3
            tool.width = 3

            tool.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromStacks(ItemStack(ingot_livium)))
                    .add(Ingredient.fromStacks(ItemStack(ingot_livium)))
                    .add(Ingredient.fromStacks(ItemStack(ingot_livium)))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_livium_pickaxe"),
                    ShapedArcaneRecipe(liviumGroup,
                            "LIVIUM",
                            10,
                            AspectList().add(Aspect.ORDER, 2).merge(Aspect.EARTH,1).merge(Aspect.WATER,1),
                            ItemStack(pickaxe_livium),
                            tool))
        }
        run {
            val tool = CraftingHelper.ShapedPrimer()
            tool.height = 3
            tool.width = 3

            tool.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromStacks(ItemStack(ingot_livium)))
                    .add(Ingredient.fromStacks(ItemStack(ingot_livium)))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromStacks(ItemStack(ingot_livium)))
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_livium_axe"),
                    ShapedArcaneRecipe(liviumGroup,
                            "LIVIUM",
                            10,
                            AspectList().add(Aspect.ORDER, 2).merge(Aspect.EARTH,1).merge(Aspect.WATER,1),
                            ItemStack(axe_livium),
                            tool))
        }
        run {
            val tool = CraftingHelper.ShapedPrimer()
            tool.height = 3
            tool.width = 3

            tool.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromStacks(ItemStack(ingot_livium)))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_livium_shovel"),
                    ShapedArcaneRecipe(liviumGroup,
                            "LIVIUM",
                            10,
                            AspectList().add(Aspect.ORDER, 2).merge(Aspect.EARTH,1).merge(Aspect.WATER,1),
                            ItemStack(shovel_livium),
                            tool))
        }
        run {
            val tool = CraftingHelper.ShapedPrimer()
            tool.height = 3
            tool.width = 3

            tool.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromStacks(ItemStack(ingot_livium)))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromStacks(ItemStack(ingot_livium)))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_livium_sword"),
                    ShapedArcaneRecipe(liviumGroup,
                            "LIVIUM",
                            10,
                            AspectList().add(Aspect.ORDER, 2).merge(Aspect.EARTH,1).merge(Aspect.WATER,1),
                            ItemStack(sword_livium),
                            tool))
        }

    }
    companion object {


        @GameRegistry.ObjectHolder("$MODID:experiment_table")
        var experiment_table: Block? = null

        @GameRegistry.ObjectHolder("$MODID:creature_enchanter")
        var enchanter: Item? = null

        @GameRegistry.ObjectHolder("$MODID:neutered_flesh")
        lateinit var neutered_flesh: Item


        @GameRegistry.ObjectHolder("$MODID:ingot_livium")
        lateinit var ingot_livium: Item


        @GameRegistry.ObjectHolder("$MODID:livium_pickaxe")
        lateinit var pickaxe_livium: Item

        @GameRegistry.ObjectHolder("$MODID:livium_axe")
        lateinit var axe_livium: Item

        @GameRegistry.ObjectHolder("$MODID:livium_shovel")
        lateinit var shovel_livium: Item

        @GameRegistry.ObjectHolder("$MODID:livium_sword")
        lateinit var sword_livium: Item

        @GameRegistry.ObjectHolder("$MODID:paving_stone_disable_enchants")
        var disable_enchants: Block? = null

        @GameRegistry.ObjectHolder("$MODID:paving_stone_enable_enchants")
        var enable_enchants: Block? = null

        @GameRegistry.ObjectHolder("thaumcraft:stone_arcane_brick")
        var arcane_stone: Block? = null


        @GameRegistry.ObjectHolder("thaumcraft:salis_mundus")
        var sal_mundi: Item? = null

        @GameRegistry.ObjectHolder("thaumcraft:plank_greatwood")
        var plank_greatwood: Item? = null



        @GameRegistry.ObjectHolder("minecraft:bowl")
        var bowl: Item? = null


        @GameRegistry.ObjectHolder("minecraft:glass_bottle")
        var bottle: Item? = null

        @GameRegistry.ObjectHolder("minecraft:stick")
        lateinit var stick:Item
    }
}
