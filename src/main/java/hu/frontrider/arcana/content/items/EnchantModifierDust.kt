package hu.frontrider.arcana.content.items

import hu.frontrider.arcana.ThaumicArcana
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle
import hu.frontrider.arcana.sided.network.creatureenchants.CreatureEnchantSyncMessage
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.potion.Potion
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import thaumcraft.api.aura.AuraHelper
import thaumcraft.api.items.ItemsTC

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.core.capabilities.creatureenchant.CreatureEnchantProvider.Companion.CREATURE_ENCHANT_CAPABILITY
import net.minecraft.util.EnumActionResult.FAIL
import net.minecraft.util.EnumActionResult.SUCCESS

class EnchantModifierDust(private val networkWrapper: SimpleNetworkWrapper) : ItemBase(ResourceLocation(MODID, "enchant_modifier")) {


    init {
        this.setMaxStackSize(1)
    }

    override fun isEnchantable(stack: ItemStack): Boolean {
        return false
    }

    internal fun applyEntity(entity: Entity?, playerIn: EntityPlayer?, stack: ItemStack?): Boolean {
        val tagCompound = stack!!.tagCompound ?: return false

        if (!tagCompound.hasKey("modifier")) return false

        if (entity!!.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            if (!playerIn!!.entityWorld.isRemote) {
                if (AuraHelper.drainVis(entity.world, entity.position, 30f, true) < 30)
                    return false

                val modifier = tagCompound.getString("modifier")
                val baseCircle = registry.getValue(ResourceLocation(modifier))

                val capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null)
                capability!!.circle = baseCircle!!

                stack.shrink(1)

                AuraHelper.polluteAura(entity.entityWorld, entity.position, 10f, true)

                val entityId = entity.entityId
                networkWrapper.sendTo(CreatureEnchantSyncMessage(capability, entityId), playerIn as EntityPlayerMP?)
            }
        }
        return false
    }


    override fun onItemUse(player: EntityPlayer?, worldIn: World?, pos: BlockPos?, hand: EnumHand?, facing: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        val stack = player!!.heldItemMainhand
        val gauntlet = player.heldItemOffhand
        val blockState = worldIn!!.getBlockState(pos!!)
        return if (blockState.block === Blocks.ENCHANTING_TABLE &&
                gauntlet.item == ItemsTC.casterBasic &&
                player.getActivePotionEffect(warpWard!!) != null) {
            if (applyEntity(player, player, stack)) SUCCESS else FAIL
        } else FAIL
    }

    override fun itemInteractionForEntity(stack: ItemStack?, playerIn: EntityPlayer?, entity: EntityLivingBase?, hand: EnumHand?): Boolean {
        return applyEntity(entity, playerIn, stack)
    }

    override fun addInformation(stack: ItemStack?, worldIn: World?, tooltip: MutableList<String>?, flagIn: ITooltipFlag?) {
        val tagCompound = stack!!.tagCompound
        if (tagCompound != null) {
            if (tagCompound.hasKey("modifier")) {

                val name = tagCompound.getString("modifier")
                tooltip!!.add(I18n.format("enchant.modifier." + name.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].toLowerCase()))
            }
        }
    }

    @SideOnly(Side.CLIENT)
    override fun hasEffect(stack: ItemStack): Boolean {
        val tagCompound = stack.tagCompound
        return tagCompound != null && tagCompound.hasKey("modifier")
    }

    override fun getHasSubtypes(): Boolean {
        return true
    }

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (tab === ThaumicArcana.TABARCANA) {
            items.add(ItemStack(this))
            registry.valuesCollection.forEach { enchant -> items.add(createItem(this, enchant)) }
        }
    }

    companion object {
        private val registry = GameRegistry.findRegistry(EnchantingBaseCircle::class.java)

        @GameRegistry.ObjectHolder("thaumcraft:warpward")
        val warpWard: Potion? = null

        fun createItem(baseItem: Item, modifierName: EnchantingBaseCircle): ItemStack {
            val enchanter = ItemStack(baseItem, 1)

            val compound = NBTTagCompound()
            compound.setString("modifier", modifierName.registryName!!.toString())
            enchanter.tagCompound = compound
            return enchanter
        }
    }

}
