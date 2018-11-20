package hu.frontrider.arcana.items.armor

import hu.frontrider.arcana.ThaumicArcana.MODID
import net.minecraft.entity.Entity
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack

open class ArmorBase(material:ArmorMaterial, renderindex:Int, slot:EntityEquipmentSlot):ItemArmor(material,renderindex,slot) {
    override fun getArmorTexture(stack: ItemStack?, entity: Entity?, slot: EntityEquipmentSlot?, type: String?): String? {
        return if(slot != EntityEquipmentSlot.LEGS)
            MODID+":textures/models/armor/${armorMaterial.getName()}_layer_1.png"
        else
            MODID+":textures/models/armor/${armorMaterial.getName()}_layer_2.png"
    }
}