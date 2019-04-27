package hu.frontrider.arcana.registrationhandlers.recipes

import hu.frontrider.arcana.TAConfig
import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.items.*
import hu.frontrider.arcana.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.creatureenchant.EnchantingBaseCircle
import hu.frontrider.arcana.items.ItemPlantBall
import hu.frontrider.arcana.registrationhandlers.ItemRegistry
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.init.Items.*
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder
import net.minecraftforge.oredict.OreDictionary
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.ThaumcraftApiHelper
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.crafting.CrucibleRecipe

class AlchemyRecipes {

    companion object {
        @ObjectHolder("$MODID:magic_oak_sapling")
        lateinit var magic_oak_sapling: Block
        @ObjectHolder("$MODID:silver_oak_sapling")
        lateinit var silver_oak_sapling: Block
        @ObjectHolder("$MODID:tainted_oak_sapling")
        lateinit var tainted_oak_sapling: Block
        @ObjectHolder("$MODID:enchant_modifier")
        internal lateinit var modifier: Item
        @ObjectHolder("$MODID:nutrient_mix")
        internal lateinit var nutrientMix: Item

        @GameRegistry.ObjectHolder("$MODID:empty_soul_capsule")
        lateinit var empty_soul_capsule: Item


        @GameRegistry.ObjectHolder("$MODID:soul_capsule")
        lateinit var soul_capsule: Item

        @GameRegistry.ObjectHolder("$MODID:infused_slime")
        lateinit var infused_slime: Item
    }


    fun register() {
        initMetalTransmutation()
        initGrowingBasic()
        initFertilizer()
        initHardenFleshToLeather()
        initGrowingAdvanced()
        initGrowingFlesh()
        initPlantProducts()
        initEnchanting()
        initPlantExperiments()
        initSlime()
        initSouls()
    }

    private fun initMetalTransmutation() {

        run {
            val recipe = CrucibleRecipe(
                    "METAL_TRANSMUTATION",
                    ItemStack(Items.GOLD_NUGGET, 1, 0),
                    "nuggetIron",
                    AspectList().add(Aspect.DESIRE, 1)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "transmutation_gold"), recipe)
        }

        if (OreDictionary.doesOreNameExist("ingotCopper")) {
            val copper = OreDictionary.getOres("nuggetCopper")
            if (copper.size > 0) {
                val itemStack = copper[0]
                itemStack.count = 1
                val recipe = CrucibleRecipe(
                        "METAL_TRANSMUTATION",
                        itemStack,
                        "nuggetIron",
                        AspectList().add(Aspect.EXCHANGE, 1)
                )
                ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "transmutation_copper"), recipe)
            }
        }

        if (OreDictionary.doesOreNameExist("ingotLead")) {
            val lead = OreDictionary.getOres("nuggetLead")
            if (lead.size > 0) {
                val itemStack = lead[0]
                itemStack.count = 1
                val recipe = CrucibleRecipe(
                        "METAL_TRANSMUTATION",
                        itemStack,
                        "nuggetIron",
                        AspectList().add(Aspect.ORDER, 1)
                )
                ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "transmutation_lead"), recipe)
            }
        }

        if (OreDictionary.doesOreNameExist("ingotSilver")) {
            val silver = OreDictionary.getOres("nuggetSilver")
            if (silver.size > 0) {
                val itemStack = silver[0]
                itemStack.count = 1
                val recipe = CrucibleRecipe(
                        "METAL_TRANSMUTATION",
                        itemStack,
                        "nuggetIron",
                        AspectList().add(Aspect.METAL, 1)
                )
                ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "transmutation_silver"), recipe)
            }
        }

        if (OreDictionary.doesOreNameExist("ingotNickel")) {
            val nickel = OreDictionary.getOres("nuggetNickel")
            if (nickel.size > 0) {
                val itemStack = nickel[0]
                itemStack.count = 1
                val recipe = CrucibleRecipe(
                        "METAL_TRANSMUTATION",
                        itemStack,
                        "nuggetIron",
                        AspectList().add(Aspect.ALCHEMY, 1)
                )
                ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "transmutation_nickel"), recipe)
            }
        }

        if (TAConfig.enablePlatinum && OreDictionary.doesOreNameExist("ingotPlatinum")) {
            val platinum = OreDictionary.getOres("nuggetPlatinum")
            if (platinum.size > 0) {
                val itemStack = platinum[0]
                itemStack.count = 1
                val recipe = CrucibleRecipe(
                        "METAL_TRANSMUTATION",
                        itemStack,
                        "nuggetIron",
                        AspectList().add(Aspect.CRYSTAL, 1)
                                .merge(Aspect.DESIRE, 5)
                )
                ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "transmutation_platinum"), recipe)
            }
        }

        if (OreDictionary.doesOreNameExist("ingotTin")) {
            val tin = OreDictionary.getOres("nuggetTin")
            if (tin.size > 0) {
                val itemStack = tin[0]
                itemStack.count = 1
                val recipe = CrucibleRecipe(
                        "METAL_TRANSMUTATION",
                        itemStack,
                        "nuggetIron",
                        AspectList().add(Aspect.CRYSTAL, 1)
                )
                ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "transmutation_tin"), recipe)
            }
        }

    }

    private fun initGrowingBasic() {
        val KEY = "PLANT_GROWTH"
        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(WHEAT, 1),
                    ItemStack(WHEAT_SEEDS),
                    AspectList().add(Aspect.LIGHT, 2).merge(Aspect.EARTH, 2).merge(Aspect.WATER, 2)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "grow_wheat"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(CARROT, 2),
                    ItemStack(CARROT),
                    AspectList().add(Aspect.LIGHT, 2).merge(Aspect.EARTH, 2).merge(Aspect.WATER, 2)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "grow_carrot"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(Items.POTATO, 2),
                    ItemStack(POTATO),
                    AspectList().add(Aspect.LIGHT, 2).merge(Aspect.EARTH, 2).merge(Aspect.WATER, 2)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "grow_potato"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(BEETROOT, 1),
                    ItemStack(BEETROOT_SEEDS),
                    AspectList().add(Aspect.LIGHT, 2).merge(Aspect.EARTH, 2).merge(Aspect.WATER, 2)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "grow_beetroot"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(REEDS, 3),
                    ItemStack(REEDS),
                    AspectList().add(Aspect.LIGHT, 2).merge(Aspect.EARTH, 2).merge(Aspect.WATER, 3)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "grow_reeds"), recipe)
        }
        run {
            val `object` = Item.REGISTRY.getObject(ResourceLocation("minecraft:melon"))
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(`object`!!, 1),
                    ItemStack(MELON_SEEDS),
                    AspectList().add(Aspect.LIGHT, 3).merge(Aspect.EARTH, 3).merge(Aspect.WATER, 3)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "grow_melon"), recipe)
        }

        run {
            val `object` = Item.REGISTRY.getObject(ResourceLocation("minecraft:pumpkin"))
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(`object`!!, 1),
                    ItemStack(PUMPKIN_SEEDS),
                    AspectList().add(Aspect.LIGHT, 3).merge(Aspect.EARTH, 3).merge(Aspect.WATER, 3)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "grow_beetroot"), recipe)
        }
    }

    private fun initGrowingAdvanced() {
        val KEY = "PLANT_GROWTH_ADVANCED"
        val treeItems = ItemPlantBall.treeItems!!
        var index = 0
        for (treeItem in treeItems) {
            val seedItem = ItemPlantBall.getProductByIndex(treeItem, 0)

            val recipe = CrucibleRecipe(
                    KEY,
                    treeItem,
                    ItemStack(seedItem.item, 1, seedItem.metadata),
                    AspectList()
                            .add(Aspect.LIGHT, 5)
                            .merge(Aspect.EARTH, 10)
                            .merge(Aspect.WATER, 12)
                            .merge(Aspect.VOID, 5)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "tree_$index"
            ), recipe)
            index++
        }

        val seedItems = ItemPlantBall.seedItems!!
        index = 0
        for (seedlingItem in seedItems) {
            val seedItem = ItemPlantBall.getProductByIndex(seedlingItem, 0)

            val recipe = CrucibleRecipe(
                    KEY,
                    seedlingItem,
                    ItemStack(seedItem.item, 1, seedItem.metadata),
                    AspectList()
                            .add(Aspect.LIGHT, 5)
                            .merge(Aspect.EARTH, 10)
                            .merge(Aspect.WATER, 12)
                            .merge(Aspect.VOID, 5)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "seed_1$index"
            ), recipe)
            index++
        }

    }

    private fun initGrowingFlesh() {

        val KEY = "FLESH_GROWTH"
        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(Items.BEEF, 2),
                    ItemStack(Items.BEEF),
                    AspectList().add(Aspect.LIFE, 10)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "grow_beef"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(Items.MUTTON, 2),
                    ItemStack(Items.MUTTON),
                    AspectList().add(Aspect.LIFE, 10)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "grow_mutton"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(Items.PORKCHOP, 2),
                    ItemStack(Items.PORKCHOP),
                    AspectList().add(Aspect.LIFE, 10)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "grow_pork"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(Items.CHICKEN, 2),
                    ItemStack(Items.CHICKEN),
                    AspectList().add(Aspect.LIFE, 10)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "grow_chicken"), recipe)
        }

        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(nutrientMix),
                    ItemStack(Items.SUGAR),
                    AspectList().add(Aspect.LIFE, 3).add(Aspect.PLANT, 2).add(Aspect.MAGIC, 2)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "nutrient_mix"), recipe)
        }

    }

    private fun initHardenFleshToLeather() {
        run {
            val recipe = CrucibleRecipe(
                    "FLESH_TO_LEATHER",
                    ItemStack(Items.LEATHER, 1),
                    ItemStack(Items.BEEF),
                    AspectList().add(Aspect.EXCHANGE, 1).merge(Aspect.CRAFT, 1)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "beef_to_leather"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    "FLESH_TO_LEATHER",
                    ItemStack(Items.LEATHER, 1),
                    ItemStack(Items.PORKCHOP),
                    AspectList().add(Aspect.EXCHANGE, 1).merge(Aspect.CRAFT, 1)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "pork_to_leather"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    "FLESH_TO_LEATHER",
                    ItemStack(Items.LEATHER, 1),
                    ItemStack(Items.MUTTON),
                    AspectList().add(Aspect.EXCHANGE, 1).merge(Aspect.CRAFT, 1)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "mutton_to_leather"), recipe)
        }
    }

    private fun initFertilizer() {

        run {
            val recipe = CrucibleRecipe(
                    "ARCANE_FERTILIZER",
                    ItemStack(ItemRegistry.fertiliser, 16),
                    ThaumcraftApiHelper.makeCrystal(Aspect.PLANT, 1),
                    AspectList().add(Aspect.LIGHT, 4).merge(Aspect.EARTH, 4).merge(Aspect.WATER, 4).merge(Aspect.CRAFT, 4).add(Aspect.PLANT, 10)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "fertilizer_recipe"), recipe)
        }

        run {
            val recipe = CrucibleRecipe(
                    "INCUBATED_EGG",
                    ItemStack(ItemRegistry.incubated_egg, 1),
                    ItemStack(EGG),
                    AspectList().add(Aspect.FIRE, 2).merge(Aspect.LIFE, 2)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "incubated_egg_recipe"), recipe)
        }
    }

    private fun initPlantProducts() {
        run {
            val recipe = CrucibleRecipe(
                    "PLANT_PRODUCTS",
                    ItemStack(Items.PAPER, 16, 0),
                    "logWood",
                    AspectList()
                            .add(Aspect.WATER, 1)
                            .add(Aspect.ENTROPY, 1)
                            .add(Aspect.ORDER, 1)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "paper"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    "PLANT_PRODUCTS",
                    ItemStack(Items.DYE, 2, 1),
                    ItemStack(Items.DYE, 1, 1),
                    AspectList()
                            .add(Aspect.WATER, 1)
                            .add(Aspect.LIGHT, 1)
                            .add(Aspect.EARTH, 1)
                            .add(Aspect.LIFE, 1)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "dye_red"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    "PLANT_PRODUCTS",
                    ItemStack(Items.DYE, 2, 2),
                    ItemStack(Items.DYE, 1, 2),
                    AspectList()
                            .add(Aspect.WATER, 1)
                            .add(Aspect.LIGHT, 1)
                            .add(Aspect.EARTH, 1)
                            .add(Aspect.LIFE, 1)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "dye_green"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    "PLANT_PRODUCTS",
                    ItemStack(Items.DYE, 2, 11),
                    ItemStack(Items.DYE, 1, 11),
                    AspectList()
                            .add(Aspect.WATER, 1)
                            .add(Aspect.LIGHT, 1)
                            .add(Aspect.EARTH, 1)
                            .add(Aspect.LIFE, 1)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "dye_yellow"), recipe)
        }
    }

    private fun initEnchanting() {
        run {

            val recipe = CrucibleRecipe(
                    "CREATURE_ENCHANT",
                    ItemStack(ItemRegistry.enchanting_powder_basic, 1, 0),
                    ItemStack(Items.DYE, 1, 4),
                    AspectList()
                            .add(Aspect.MAGIC, 10)
                            .add(Aspect.ENTROPY, 3)
                            .add(Aspect.ORDER, 3)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "enchant_powder_basic"), recipe)
        }
        run {
            val registry = GameRegistry.findRegistry(CreatureEnchant::class.java)

            for (it in arrayOf(ItemRegistry.enchanting_powder_basic, ItemRegistry.enchanting_powder_advanced, ItemRegistry.enchanting_powder_magical)) {
                //getting all the creature enchants
                for (enchant in registry.valuesCollection) {
                    val enchantedItem = CreatureEnchanter.createEnchantedItem(it, CreatureEnchanter.EnchantmentData(enchant, (it as EnchantmentUpgradePowder).level))

                    val recipe = CrucibleRecipe(
                            enchant.research,
                            enchantedItem,
                            ItemStack(it),
                            enchant.formula().merge(Aspect.ENERGY, it.level * 10)
                    )
                    ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, enchant.registryName!!.resourcePath + "_" + it.registryName!!.resourcePath), recipe)
                }
            }
        }

        run {
            val registry = GameRegistry.findRegistry(EnchantingBaseCircle::class.java)

            //getting all the creature enchants
            for (enchant in registry.valuesCollection) {
                val enchantedItem = EnchantModifierDust.createItem(modifier, enchant)

                val recipe = CrucibleRecipe(
                        enchant.research,
                        enchantedItem,
                        ItemStack(modifier),
                        enchant.formula
                )
                ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "ce_circle_" + enchant.registryName!!.resourcePath), recipe)
            }
        }
    }

    private fun initPlantExperiments() {
        val KEY = "PLANT_EXPERIMENTS"
        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(magic_oak_sapling, 1, 0),
                    ItemStack(Blocks.SAPLING, 1, 0),
                    AspectList()
                            .add(Aspect.LIGHT, 2)
                            .merge(Aspect.EARTH, 2)
                            .merge(Aspect.WATER, 2)
                            .merge(Aspect.MAGIC, 20)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "great_oak_wood"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(silver_oak_sapling, 1, 0),
                    ItemStack(Blocks.SAPLING, 1, 0),
                    AspectList()
                            .add(Aspect.LIGHT, 2)
                            .merge(Aspect.EARTH, 2)
                            .merge(Aspect.WATER, 2)
                            .merge(Aspect.AURA, 20)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "silver_oak_wood"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    KEY,
                    ItemStack(tainted_oak_sapling, 1, 0),
                    ItemStack(Blocks.SAPLING, 1, 0),
                    AspectList()
                            .add(Aspect.LIGHT, 2)
                            .merge(Aspect.EARTH, 2)
                            .merge(Aspect.WATER, 2)
                            .merge(Aspect.FLUX, 20)
                            .merge(Aspect.LIFE, 30)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "tainted_oak_wood"), recipe)
        }
    }

    private fun initSlime(){
        run {
            val recipe = CrucibleRecipe(
                    "TA_SLIME@4",
                    ItemStack(Items.MAGMA_CREAM, 4),
                    Blocks.MAGMA,
                    AspectList().add(Aspect.ENTROPY, 4).add(Aspect.FIRE,2)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "magma_to_cream"), recipe)
        }
        run {
            val recipe = CrucibleRecipe(
                    "TA_SLIME@4",
                    ItemStack(Items.SLIME_BALL, 1),
                    Items.MAGMA_CREAM,
                    AspectList().add(Aspect.CRAFT, 2).add(Aspect.WATER,2)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "cream_to_slime"), recipe)
        }

        run {
            val recipe = CrucibleRecipe(
                    "TA_SLIME@4",
                    ItemStack(Items.BLAZE_POWDER, 1),
                    Items.MAGMA_CREAM,
                    AspectList().add(Aspect.CRAFT, 2).add(Aspect.FIRE,2)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "cream_to_powder"), recipe)
        }
        run {

            val entityTag = NBTTagCompound()
            entityTag.setString("id","minecraft:slime")

            val result = ItemStack(Items.SPAWN_EGG, 1)
            val compound = NBTTagCompound()

            compound.setTag("EntityTag",entityTag)

            result.tagCompound = compound
            val recipe = CrucibleRecipe(
                    "TA_SLIME_ANIMATION",
                    result,
                    Blocks.SLIME_BLOCK,
                    AspectList().add(Aspect.LIFE, 5).add(Aspect.ORDER,2)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "make_slime_spawner"), recipe)
        }
        run {

            val entityTag = NBTTagCompound()
            entityTag.setString("id","minecraft:magma_cube")

            val result = ItemStack(Items.SPAWN_EGG, 1)
            val compound = NBTTagCompound()

            compound.setTag("EntityTag",entityTag)

            result.tagCompound = compound
            val recipe = CrucibleRecipe(
                    "TA_SLIME_ANIMATION",
                    result,
                    Blocks.SLIME_BLOCK,
                    AspectList().add(Aspect.LIFE, 5).add(Aspect.ORDER,2).add(Aspect.FIRE,20)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "make_magma_slime_spawner"), recipe)
        }

        run {
            val fabricitem = ItemInfusedSlime.createSlimeFor(Aspect.CRAFT,infused_slime)

            val recipe = CrucibleRecipe(
                    "TA_SLIME_INFUSION",
                    fabricitem,
                    Items.SLIME_BALL,
                    AspectList().add(Aspect.CRAFT, 5)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "fabric_slime"), recipe)
        }

    }

    fun initSouls(){
        run {

            val recipe = CrucibleRecipe(
                    "TA_REVIVAL_CAPSULE",
                   ItemStack(soul_capsule),
                    empty_soul_capsule,
                    AspectList().add(Aspect.SOUL, 60)
                            .add(Aspect.AIR,10)
                            .add(Aspect.FIRE,20)
            )
            ThaumcraftApi.addCrucibleRecipe(ResourceLocation(MODID, "soul_capsule"), recipe)
        }
    }
}

