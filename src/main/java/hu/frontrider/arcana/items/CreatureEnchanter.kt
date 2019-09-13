package hu.frontrider.arcana.items

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.capabilities.creatureenchant.CreatureEnchantProvider
import hu.frontrider.arcana.capabilities.inhibitor.InhibitorProvider
import hu.frontrider.arcana.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.sided.network.creatureenchants.CreatureEnchantSyncMessage
import hu.frontrider.arcana.registrationhandlers.ItemRegistry
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.potion.Potion
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import thaumcraft.api.aura.AuraHelper
import thaumcraft.api.items.ItemsTC

class CreatureEnchanter(private val networkWrapper: SimpleNetworkWrapper) : ItemBase(ResourceLocation(MODID, "creature_enchanter")) {

    init {
        this.setMaxStackSize(1)
    }

    fun applyEntity(entity: Entity?, playerIn: EntityPlayer?, stack: ItemStack?): Boolean {
        val tagCompound = stack!!.tagCompound ?: return false

        if (!tagCompound.hasKey("creature_enchants")) return false

        if (entity!!.hasCapability(CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY, null)) {
            if (!playerIn!!.entityWorld.isRemote) {
                val capability = entity.getCapability(CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY, null) ?: return false

                val creature_enchants = tagCompound.getTag("creature_enchants")

                (creature_enchants as NBTTagList).iterator().forEachRemaining { enchant ->
                    if (capability.store.size <= 6 && AuraHelper.drainVis(entity.world, entity.position, 30f, true) >= 30) {
                        AuraHelper.drainVis(entity.world, entity.position, 30f, false)
                        val enchantmentData = nbtToEnchantment(enchant as NBTTagCompound)

                        capability.removeEnchant(enchantmentData.enchantment)

                        AuraHelper.polluteAura(entity.entityWorld, entity.position, 5f, true)
                        if (entity is EntityPlayer) {
                            AuraHelper.polluteAura(entity.entityWorld, entity.position, 20f, true)
                        }
                        stack.shrink(1)

                        val entityId = entity.entityId
                        networkWrapper.sendTo(CreatureEnchantSyncMessage(capability, entityId), playerIn as EntityPlayerMP?)
                    }
                }
            }
            return true
        }
        return false
    }

    override fun itemInteractionForEntity(stack: ItemStack?, playerIn: EntityPlayer?, entity: EntityLivingBase?, hand: EnumHand?): Boolean {

        return applyEntity(entity, playerIn, stack)

    }

    override fun onItemUse(player: EntityPlayer?, worldIn: World?, pos: BlockPos?, hand: EnumHand?, facing: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        val stack = player!!.heldItemMainhand
        val gauntlet = player.heldItemOffhand
        val blockState = worldIn!!.getBlockState(pos!!)
        return if (blockState.block === Blocks.ENCHANTING_TABLE &&
                gauntlet.item == ItemsTC.casterBasic &&
                player.getActivePotionEffect(warpWard!!) != null) {
            if (applyEntity(player, player, stack)) EnumActionResult.SUCCESS else EnumActionResult.FAIL
        } else EnumActionResult.FAIL
    }



    override fun isEnchantable(stack: ItemStack): Boolean {
        return false
    }


    override fun addInformation(stack: ItemStack?, worldIn: World?, tooltip: MutableList<String>?, flagIn: ITooltipFlag?) {
        val tagCompound = stack!!.tagCompound
        if (tagCompound != null) {
            if (tagCompound.hasKey("creature_enchants")) {
                val creature_enchants = tagCompound.getTag("creature_enchants") as NBTTagList

                creature_enchants.iterator().forEachRemaining { enchant ->
                    val name = (enchant as NBTTagCompound).getString("name")
                    val level = enchant.getInteger("level")
                    tooltip!!.add(I18n.format("enchant.creature_enchant." + name.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].toLowerCase()) + " " + level)
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    override fun hasEffect(stack: ItemStack): Boolean {
        val tagCompound = stack.tagCompound
        return tagCompound != null && tagCompound.hasKey("creature_enchants")
    }

    class EnchantmentData(val enchantment: CreatureEnchant, val level: Int)

    companion object {

        @GameRegistry.ObjectHolder("thaumcraft:warpward")
        val warpWard: Potion? = null

        fun createEnchantedItem(baseItem: Item, vararg enchantmentDatas: EnchantmentData): ItemStack {
            val enchanter = ItemStack(baseItem, 1)

            val tagList = NBTTagList()

            for (enchantmentData in enchantmentDatas) {
                val enchantmentTag = NBTTagCompound()
                enchantmentTag.setString("name", enchantmentData.enchantment.registryName!!.toString())
                enchantmentTag.setInteger("level", enchantmentData.level)

                tagList.appendTag(enchantmentTag)
            }
            val compound = NBTTagCompound()
            compound.setTag("creature_enchants", tagList)
            enchanter.tagCompound = compound
            return enchanter
        }

        fun createEnchantedItem(vararg enchantmentDatas: EnchantmentData): ItemStack {
            return createEnchantedItem(ItemRegistry.creature_enchanter, *enchantmentDatas)
        }

        internal fun nbtToEnchantment(nbt: NBTTagCompound): EnchantmentData {
            val name = nbt.getString("name")
            val level = nbt.getInteger("level")
            return EnchantmentData(GameRegistry.findRegistry(CreatureEnchant::class.java).getValue(ResourceLocation(name))!!, level)
        }
    }
}
