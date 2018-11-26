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
import hu.frontrider.arcana.registrationhandlers.ItemRegistry.Companion.enchanting_powder_advanced
import hu.frontrider.arcana.registrationhandlers.ItemRegistry.Companion.enchanting_powder_basic
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.init.Items.DYE
import net.minecraft.init.Items.IRON_INGOT
import thaumcraft.api.items.ItemsTC.salisMundus

class InfusionRecipes {

    companion object {
        @GameRegistry.ObjectHolder("$MODID:enchant_modifier")
        internal lateinit var modifier: Item

        @GameRegistry.ObjectHolder("$MODID:ingot_livium")
        lateinit var ingot_livium: Item

        @GameRegistry.ObjectHolder("$MODID:neutered_flesh")
        lateinit var neutered_flesh: Item

        @GameRegistry.ObjectHolder("$MODID:infused_slimy_pickaxe")
        lateinit var pickaxe_slime_infused: Item

        @GameRegistry.ObjectHolder("$MODID:infused_slimy_axe")
        lateinit var axe_slime_infused: Item

        @GameRegistry.ObjectHolder("$MODID:infused_slimy_shovel")
        lateinit var shovel_slime_infused: Item

        @GameRegistry.ObjectHolder("$MODID:infused_slimy_sword")
        lateinit var sword_slime_infused: Item

        @GameRegistry.ObjectHolder("$MODID:infused_slimy_hoe")
        lateinit var hoe_slime_infused: Item

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

        @GameRegistry.ObjectHolder("$MODID:infused_slimy_chestplate")
        lateinit var infused_slimy_chestplate: Item

        @GameRegistry.ObjectHolder("$MODID:infused_slimy_leggings")
        lateinit var infused_slimy_leggings: Item

        @GameRegistry.ObjectHolder("$MODID:infused_slimy_helmet")
        lateinit var infused_slimy_helmet: Item

        @GameRegistry.ObjectHolder("$MODID:infused_slime")
        lateinit var infused_slime: Item
    }

    fun register() {
        registerCreatureEnchants()
        initLivium()
        initInfusedSlimeTools()
        initInfusedSlimeArmor()
    }

    fun registerCreatureEnchants() {

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

    private fun initLivium() {
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

    private fun initInfusedSlimeTools() {
        fun createTool(sourceItem:Item,targetItem:Item,name:String,aspects:AspectList){
            val source = ItemStack(sourceItem)

            val itemStack = ItemStack(targetItem)
            ThaumcraftApi.addInfusionCraftingRecipe(
                    ResourceLocation(MODID, "infuse_slime_tool_$name"),
                    InfusionRecipe("TA_INFUSE_SLIME_TOOLS",
                            itemStack,
                            1, aspects
                            .add(Aspect.LIFE, 30)
                            .add(Aspect.TOOL, 40)
                            .add(Aspect.ALCHEMY, 50),
                            source,
                            ItemStack(ingot_livium),
                            ItemStack(ingot_livium),
                            ItemStack(Items.DIAMOND),
                            ItemStack(Items.DIAMOND),
                            ItemStack(Items.BLAZE_ROD),
                            ItemStack(Items.SPECKLED_MELON),
                            ItemStack(Items.GOLDEN_APPLE),
                            ItemStack(Items.DRAGON_BREATH)
                    )
            )
        }

        createTool(axe_slime,axe_slime_infused,"axe",AspectList().merge(Aspect.METAL,10))
        createTool(hoe_slime,hoe_slime_infused,"hoe",AspectList().merge(Aspect.PLANT,10))
        createTool(shovel_slime,shovel_slime_infused,"shovel",AspectList().merge(Aspect.EARTH,10))
        createTool(pickaxe_slime,pickaxe_slime_infused,"pickaxe",AspectList().merge(Aspect.TOOL,10))
        createTool(sword_slime,sword_slime_infused,"sword",AspectList().merge(Aspect.AVERSION,10))

    }
    private fun initInfusedSlimeArmor() {
        fun createArmor(sourceItem:Item,targetItem:Item,name:String,aspects:AspectList){
            val source = ItemStack(sourceItem)

            val itemStack = ItemStack(targetItem)
            ThaumcraftApi.addInfusionCraftingRecipe(
                    ResourceLocation(MODID, "infuse_slime_armor_$name"),
                    InfusionRecipe("TA_SLIME_ARMOR",
                            itemStack,
                            1, aspects
                            .add(Aspect.LIFE, 30)
                            .add(Aspect.TOOL, 40)
                            .add(Aspect.PROTECT,50)
                            .add(Aspect.ALCHEMY, 50),
                            source,
                            ItemStack(ingot_livium),
                            ItemStack(ingot_livium),
                            ItemStack(Items.IRON_INGOT),
                            ItemStack(Items.IRON_INGOT),
                            ItemStack(Blocks.SLIME_BLOCK),
                            ItemStack(Blocks.SLIME_BLOCK),
                            ItemStack(Items.BLAZE_ROD),
                            ItemStack(Items.SPECKLED_MELON),
                            ItemStack(Items.GOLDEN_APPLE),
                            ItemStack(Items.DRAGON_BREATH)
                    )
            )
        }
        createArmor(Items.DIAMOND_HELMET, infused_slimy_helmet,"helmet",AspectList())
        createArmor(Items.DIAMOND_CHESTPLATE, infused_slimy_chestplate,"chestplate",AspectList())
        createArmor(Items.DIAMOND_LEGGINGS, infused_slimy_leggings,"leggings",AspectList())
    }
}
