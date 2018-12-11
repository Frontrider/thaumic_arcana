package hu.frontrider.arcana.items

import hu.frontrider.arcana.ThaumicArcana.TABARCANA
import hu.frontrider.arcana.capabilities.creatureenchant.CreatureEnchantCapability
import hu.frontrider.arcana.capabilities.creatureenchant.CreatureEnchantProvider
import hu.frontrider.arcana.capabilities.inhibitor.InhibitorProvider
import hu.frontrider.arcana.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.sided.network.creatureenchants.CreatureEnchantSyncMessage
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import thaumcraft.api.aura.AuraHelper
import thaumcraft.api.items.ItemsTC

class EnchantmentUpgradePowder(val level: Int, resourceLocation: ResourceLocation,private val networkWrapper: SimpleNetworkWrapper) : ItemBase(resourceLocation) {

    override fun isEnchantable(stack: ItemStack): Boolean {
        return false
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
                        val enchantmentData = CreatureEnchanter.nbtToEnchantment(enchant as NBTTagCompound)

                        val creatureEnchantContainer = CreatureEnchantCapability.CreatureEnchantContainer(enchantmentData.enchantment, enchantmentData.level, 0)
                        capability.putEnchant(creatureEnchantContainer)

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
                player.getActivePotionEffect(CreatureEnchanter.warpWard!!) != null) {
            if (applyEntity(player, player, stack)) EnumActionResult.SUCCESS else EnumActionResult.FAIL
        } else EnumActionResult.FAIL
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

    override fun getHasSubtypes(): Boolean {
        return true
    }

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (tab === TABARCANA) {
            items.add(ItemStack(this))
            registry.valuesCollection.forEach { enchant -> items.add(CreatureEnchanter.createEnchantedItem(this, CreatureEnchanter.EnchantmentData(enchant, level))) }
        }
    }

    fun transferEnchants(from: ItemStack, to: ItemStack) {
        if (from.hasTagCompound()) {
            val tagCompound = from.tagCompound
            to.tagCompound = tagCompound
        }
    }

    companion object {

        private val registry = GameRegistry.findRegistry(CreatureEnchant::class.java)
    }

}
