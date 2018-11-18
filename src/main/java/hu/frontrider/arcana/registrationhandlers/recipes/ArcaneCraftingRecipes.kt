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
import hu.frontrider.arcana.ThaumicArcana.TABARCANA
import hu.frontrider.arcana.content.items.ItemInfusedSlime
import net.minecraft.block.Block
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.init.Enchantments
import net.minecraft.init.Items
import thaumcraft.api.ThaumcraftApiHelper


class ArcaneCraftingRecipes {
    private val defaultGroup = ResourceLocation(MODID, "biomancy")

    fun register() {
        initRecipes()
        initLivium()
        initSlimyTools()
        initSlimeMeat()
        initSlimeInfusion()
    }

    private fun initRecipes() {
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
                            AspectList().add(Aspect.ORDER, 2).merge(Aspect.EARTH, 1).merge(Aspect.WATER, 1),
                            ItemStack(enable_enchants!!, 4),
                            cagePrimer))
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
                            AspectList().add(Aspect.ENTROPY, 2).merge(Aspect.EARTH, 1).merge(Aspect.WATER, 1),
                            ItemStack(disable_enchants!!, 4),
                            cagePrimer))
        }
    }

    private fun initLivium() {
        val liviumGroup = ResourceLocation(MODID, "biomancy_livium")
        run {
            val fleshGroup = ResourceLocation(MODID, "biomancy_livium_flesh")

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "neutered_flesh"),
                    ShapelessArcaneRecipe(fleshGroup,
                            "",
                            30,
                            AspectList().add(Aspect.ENTROPY, 1).add(Aspect.ORDER, 1),
                            ItemStack(neutered_flesh), arrayOf("allFlesh")
                    ))
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
                            AspectList().add(Aspect.ORDER, 2).merge(Aspect.EARTH, 1).merge(Aspect.WATER, 1),
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
                            AspectList().add(Aspect.ORDER, 2).merge(Aspect.EARTH, 1).merge(Aspect.WATER, 1),
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
                            AspectList().add(Aspect.ORDER, 2).merge(Aspect.EARTH, 1).merge(Aspect.WATER, 1),
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
                            AspectList().add(Aspect.ORDER, 2).merge(Aspect.EARTH, 1).merge(Aspect.WATER, 1),
                            ItemStack(sword_livium),
                            tool))
        }

    }

    private fun initSlimyTools() {
        val slimyGroup = ResourceLocation(MODID, "biomancy_slimy")

        run {
            val tool = CraftingHelper.ShapedPrimer()
            tool.height = 3
            tool.width = 3

            tool.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromStacks(ItemStack(slime)))
                    .add(Ingredient.fromStacks(ItemStack(iron_ingot)))
                    .add(Ingredient.fromStacks(ItemStack(slime)))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .build() as NonNullList<Ingredient>

            val result = ItemStack(pickaxe_slime)
            EnchantmentHelper.setEnchantments(mapOf(
                    Pair(Enchantments.SILK_TOUCH, 1)
            ), result)

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_slimy_pickaxe"),
                    ShapedArcaneRecipe(slimyGroup,
                            "TA_SLIME@4",
                            40,
                            AspectList().add(Aspect.ORDER, 4),
                            result,
                            tool))
        }
        run {
            val tool = CraftingHelper.ShapedPrimer()
            tool.height = 3
            tool.width = 3

            tool.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromStacks(ItemStack(slime)))
                    .add(Ingredient.fromStacks(ItemStack(iron_ingot)))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromStacks(ItemStack(slime)))
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .build() as NonNullList<Ingredient>
            val result = ItemStack(axe_slime)

            EnchantmentHelper.setEnchantments(mapOf(
                    Pair(Enchantments.SILK_TOUCH, 1)
            ), result)
            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_slimy_axe"),
                    ShapedArcaneRecipe(slimyGroup,
                            "TA_SLIME@4",
                            40,
                            AspectList().add(Aspect.ORDER, 4),
                            result,
                            tool))
        }
        run {
            val tool = CraftingHelper.ShapedPrimer()
            tool.height = 3
            tool.width = 3

            tool.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromStacks(ItemStack(slime)))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.fromStacks(ItemStack(iron_ingot)))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .build() as NonNullList<Ingredient>
            val result = ItemStack(shovel_slime)
            EnchantmentHelper.setEnchantments(mapOf(
                    Pair(Enchantments.SILK_TOUCH, 1)
            ), result)
            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_slimy_shovel"),
                    ShapedArcaneRecipe(slimyGroup,
                            "TA_SLIME@4",
                            40,
                            AspectList().add(Aspect.ORDER, 4),
                            result,
                            tool))
        }

        run {
            val tool = CraftingHelper.ShapedPrimer()
            tool.height = 3
            tool.width = 3
            val result = ItemStack(hoe_slime)
            EnchantmentHelper.setEnchantments(mapOf(
                    Pair(Enchantments.SILK_TOUCH, 1)
            ), result)
            tool.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromStacks(ItemStack(iron_ingot)))
                    .add(Ingredient.fromStacks(ItemStack(slime)))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_slimy_hoe"),
                    ShapedArcaneRecipe(slimyGroup,
                            "TA_SLIME@4",
                            40,
                            AspectList().add(Aspect.ORDER, 4),
                            result,
                            tool))
        }

        run {
            val tool = CraftingHelper.ShapedPrimer()
            tool.height = 3
            tool.width = 3

            tool.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromStacks(ItemStack(slime)))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromStacks(ItemStack(iron_ingot)))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.EMPTY)
                    .build() as NonNullList<Ingredient>
            val result = ItemStack(sword_slime)
            EnchantmentHelper.setEnchantments(mapOf(
                    Pair(Enchantments.SILK_TOUCH, 1)
            ), result)
            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_slimy_sword"),
                    ShapedArcaneRecipe(slimyGroup,
                            "TA_SLIME@4",
                            40,
                            AspectList().add(Aspect.ORDER, 4),
                            result,
                            tool))
        }


    }

    private fun initSlimeMeat() {
        fun makeSlimeMeat(item: Item, tag: String) {
            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "make_slime_$tag"),
                    ShapelessArcaneRecipe(defaultGroup,
                            "TA_SLIME_MEAT",
                            30,
                            AspectList().add(Aspect.ORDER, 3).add(Aspect.EARTH, 1).add(Aspect.WATER, 1),
                            ItemStack(slimeMeat, 4),
                            arrayOf(item, Items.SLIME_BALL, Items.SLIME_BALL, Items.SLIME_BALL)))
        }
        makeSlimeMeat(Items.BEEF, "beef")
        makeSlimeMeat(Items.MUTTON, "mutton")
        makeSlimeMeat(Items.PORKCHOP, "pork")
    }

    private fun initSlimeInfusion() {
        val variants = NonNullList.create<ItemStack>()
        infused_slime.getSubItems(TABARCANA, variants)

        variants.forEach {
            val aspectTag = it.tagCompound!!.getString("aspect")

            val crystal = ThaumcraftApiHelper.makeCrystal(Aspect.getAspect(aspectTag))
            val normal = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromItem(Items.SLIME_BALL!!))
                    .add(Ingredient.fromItem(Items.SLIME_BALL!!))
                    .add(Ingredient.fromStacks(crystal))
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "slime_infuse_$aspectTag"),
                    ShapelessArcaneRecipe(defaultGroup,
                            "TA_SLIME_INFUSION",
                            5,
                            AspectList().add(Aspect.WATER, 1),
                            it,
                            normal.toArray()))

            val fabric = ItemInfusedSlime.createSlimeFor(Aspect.CRAFT,infused_slime)
            val fabrico = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromItem(Items.SLIME_BALL!!))
                    .add(Ingredient.fromStacks(crystal))
                    .add(Ingredient.fromStacks(fabric))
                    .build() as NonNullList<Ingredient>

            it.count =3
            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "slime_infuse_${aspectTag}_fabric"),
                    ShapelessArcaneRecipe(defaultGroup,
                            "TA_SLIME_INFUSION",
                            5,
                            AspectList().add(Aspect.WATER, 1),
                            it,
                            fabrico.toArray()))
        }
    }

    companion object {

        @GameRegistry.ObjectHolder("$MODID:experiment_table")
        var experiment_table: Block? = null

        @GameRegistry.ObjectHolder("$MODID:creature_enchanter")
        var enchanter: Item? = null

        @GameRegistry.ObjectHolder("$MODID:neutered_flesh")
        lateinit var neutered_flesh: Item

        @GameRegistry.ObjectHolder("$MODID:slime_meat_raw")
        lateinit var slimeMeat: Item

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
        lateinit var stick: Item

        @GameRegistry.ObjectHolder("minecraft:slime_ball")
        lateinit var slime: Item

        @GameRegistry.ObjectHolder("minecraft:iron_ingot")
        lateinit var iron_ingot: Item

        @GameRegistry.ObjectHolder("$MODID:slimy_pickaxe")
        lateinit var pickaxe_slime: Item

        @GameRegistry.ObjectHolder("$MODID:slimy_axe")
        lateinit var axe_slime: Item

        @GameRegistry.ObjectHolder("$MODID:slimy_shovel")
        lateinit var shovel_slime: Item

        @GameRegistry.ObjectHolder("$MODID:slimy_sword")
        lateinit var sword_slime: Item

        @GameRegistry.ObjectHolder("$MODID:slimy_hoe")
        lateinit var hoe_slime: Item

        @GameRegistry.ObjectHolder("$MODID:infused_slime")
        lateinit var infused_slime: Item
    }
}
