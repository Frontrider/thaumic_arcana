package hu.frontrider.arcana.util

import hu.frontrider.arcana.registrationhandlers.ItemRegistry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class CreativeTabArcana(par1: Int, par2Str: String) : CreativeTabs(par1, par2Str) {

    @SideOnly(Side.CLIENT)
    override fun getTabIconItem(): ItemStack {
        return ItemStack(ItemRegistry.creature_enchanter)
    }
}
