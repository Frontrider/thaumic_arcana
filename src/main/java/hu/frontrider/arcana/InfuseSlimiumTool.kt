package hu.frontrider.arcana

import hu.frontrider.arcana.items.armor.makeInfusedArmor
import hu.frontrider.arcana.items.tools.makeInfusedTool
import hu.frontrider.arcana.util.IInfuseable
import hu.frontrider.arcana.util.IInfusedArmor
import net.minecraft.init.Items
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipe
import net.minecraft.potion.PotionUtils
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.crafting.IArcaneRecipe

class InfuseSlimiumTool : IArcaneRecipe {
    private lateinit var resourceLocation: ResourceLocation
    val aspects = AspectList().merge(Aspect.WATER, 1).merge(Aspect.EARTH, 1)
    override fun getResearch(): String {
        return "TA_INFUSED_SLIME_TOOL"
    }

    override fun canFit(width: Int, height: Int): Boolean {
        return width > 1 || height > 1
    }

    override fun getRegistryType(): Class<IRecipe> {
        return IRecipe::class.java
    }

    override fun getCrystals(): AspectList {
        return aspects
    }

    override fun getRecipeOutput(): ItemStack {
        return ItemStack.EMPTY
    }

    override fun getRegistryName(): ResourceLocation? {
        return resourceLocation
    }

    override fun getVis(): Int {
        return 20
    }

    override fun isDynamic(): Boolean {
        return true
    }

    override fun getCraftingResult(inv: InventoryCrafting): ItemStack {
        val items = ArrayList<ItemStack>()
        for (i in 0 until inv.sizeInventory) {
            items.add(inv.getStackInSlot(i))
        }

        val potion = items.firstOrNull {
            it.item == Items.POTIONITEM
        } ?: return ItemStack.EMPTY

        val tool = items.firstOrNull {
            it.item is IInfuseable
        } ?: return ItemStack.EMPTY
        val effectsFromStack = PotionUtils.getEffectsFromStack(potion)
        val copy = tool.copy()
        //we check if it's an armor
        return if (tool.item is IInfusedArmor)
            makeInfusedArmor(effectsFromStack.toTypedArray(), copy)
        else
            makeInfusedTool(effectsFromStack.toTypedArray(), copy)
    }

    override fun setRegistryName(name: ResourceLocation?): IRecipe {
        resourceLocation = name ?: resourceLocation
        return this
    }

    override fun matches(inv: InventoryCrafting, worldIn: World?): Boolean {
        val items = ArrayList<ItemStack>()
        for (i in 0..8) {
            val stackInSlot = inv.getStackInSlot(i)
            if (!stackInSlot.isEmpty)
                items.add(stackInSlot)
        }
        //not enough/too many items
        if (items.size != 2)
            return false

        //check if there is a potion
        val potion = items.firstOrNull {
            it.item == Items.POTIONITEM
        } ?: return false
        if (!potion.hasTagCompound()) return false
        if (potion.tagCompound!!.getString("Potion") != "minecraft:thick") return false
        //check if the correct tool is there
        items.firstOrNull {
            it.item is IInfuseable
        } ?: return false

        //recipe is correct
        return true
    }
}