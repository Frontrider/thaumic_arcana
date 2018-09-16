package hu.frontrider.arcana.content.golems

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.util.convertToIntColor
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import thaumcraft.api.golems.EnumGolemTrait.*
import thaumcraft.api.golems.parts.GolemMaterial
import thaumcraft.api.items.ItemsTC

class MaterialHay: GolemMaterial(
        MODID+":hay",
        arrayOf("TA_HAY_STUDIES"),
        ResourceLocation(MODID,"textures/entity/golem/mat_hay"),
        convertToIntColor(240,255,127),
        10,0,0,
        ItemStack(Items.WHEAT,10),
        ItemStack(ItemsTC.mechanismSimple,2),
        arrayOf(FRAGILE,DEFT,LIGHT)
)

class MaterialFlesh: GolemMaterial(
        MODID+":hay",
        arrayOf("TA_FLESH_STUDIES"),
        ResourceLocation(MODID,"textures/entity/golem/mat_flesh"),
        convertToIntColor(255,127,195),
        20,0,0,
        ItemStack(Items.WHEAT,10),
        ItemStack(ItemsTC.mechanismSimple,2),
        arrayOf(FRAGILE,LIGHT)
)