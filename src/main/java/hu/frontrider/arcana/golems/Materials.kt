package hu.frontrider.arcana.golems

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.util.convertToIntColor
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import thaumcraft.api.golems.EnumGolemTrait.*
import thaumcraft.api.golems.parts.GolemMaterial
import thaumcraft.api.items.ItemsTC

class MaterialHay: GolemMaterial(
        "$MODID.hay",
        arrayOf("TA_HAY_STUDIES"),
        ResourceLocation(MODID,"textures/entity/golem/mat_hay.png"),
        convertToIntColor(240,255,127),
        3,0,0,
        ItemStack(Items.WHEAT,10),
        ItemStack(ItemsTC.mechanismSimple,2),
        arrayOf(FRAGILE,DEFT,LIGHT)
)

class MaterialFlesh: GolemMaterial(
        "$MODID.flesh",
        arrayOf("TA_FLESH_STUDIES"),
        ResourceLocation(MODID,"textures/entity/golem/mat_flesh.png"),
        convertToIntColor(255,127,195),
        5,0,2,
        ItemStack(Items.ROTTEN_FLESH,10),
        ItemStack(ItemsTC.brain,2),

        arrayOf(FRAGILE,LIGHT,REPAIR,SMART)
)

class MaterialSlime: GolemMaterial(
        "$MODID.slime",
        arrayOf("TA_SLIME_STUDIES"),
        ResourceLocation(MODID,"textures/entity/golem/mat_slime.png"),
        convertToIntColor(24 ,160,51),
        10,10,0,
        ItemStack(Blocks.SLIME_BLOCK,1),
        ItemStack(ItemsTC.plate,2),

        arrayOf(ARMORED,CLUMSY,BLASTPROOF,HEAVY)
)

class MaterialLeather: GolemMaterial(
        "$MODID.leather",
        arrayOf("TA_LEATHER_STUDIES"),
        ResourceLocation(MODID,"textures/entity/golem/mat_leather.png"),
        convertToIntColor(198,92,53),
        10,0,0,
        ItemStack(Items.LEATHER,10),
        ItemStack(ItemsTC.mechanismComplex,2),
        arrayOf(LIGHT,HAULER)
)