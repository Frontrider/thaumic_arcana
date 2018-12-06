package hu.frontrider.arcana.items.caster

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.item.*
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.text.TextFormatting
import net.minecraft.util.text.translation.I18n
import net.minecraft.world.World
import net.minecraft.world.chunk.Chunk
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import thaumcraft.api.casters.*
import thaumcraft.api.items.IArchitect
import thaumcraft.codechicken.lib.math.MathHelper
import thaumcraft.common.items.casters.CasterManager
import thaumcraft.common.items.casters.ItemCaster
import thaumcraft.common.items.casters.ItemFocus
import thaumcraft.common.lib.network.PacketHandler
import thaumcraft.common.lib.network.misc.PacketAuraToClient
import thaumcraft.common.lib.utils.BlockUtils
import thaumcraft.common.world.aura.AuraChunk
import thaumcraft.common.world.aura.AuraHandler
import java.text.DecimalFormat
import java.util.ArrayList

open class BasicBattleGauntlet(private var area: Int = 0, material:ToolMaterial) : ItemSword(material), IArchitect, ICaster {
    internal var myFormatter = DecimalFormat("#######.#")

    init {
        this.maxStackSize = 1
        this.maxDamage = 0
        this.addPropertyOverride(ResourceLocation("focus")) { stack, worldIn, entityIn ->
            val f = (stack.item as BasicBattleGauntlet).getFocus(stack)
            if (stack.item is BasicBattleGauntlet && f != null) 1.0f else 0.0f
        }
    }


    override fun shouldCauseReequipAnimation(oldStack: ItemStack, newStack: ItemStack, slotChanged: Boolean): Boolean {
        if (oldStack.item === this && newStack.item === this) {
            val oldf = (oldStack.item as ItemCaster).getFocus(oldStack)
            val newf = (newStack.item as ItemCaster).getFocus(newStack)
            var s1 = 0
            var s2 = 0
            if (oldf?.getSortingHelper((oldStack.item as ItemCaster).getFocusStack(oldStack)) != null) {
                s1 = oldf.getSortingHelper((oldStack.item as ItemCaster).getFocusStack(oldStack)).hashCode()
            }

            if (newf?.getSortingHelper((newStack.item as ItemCaster).getFocusStack(newStack)) != null) {
                s2 = newf.getSortingHelper((newStack.item as ItemCaster).getFocusStack(newStack)).hashCode()
            }

            return s1 != s2
        } else {
            return newStack.item !== oldStack.item
        }
    }

    override fun isDamageable(): Boolean {
        return false
    }

    @SideOnly(Side.CLIENT)
    override fun isFull3D(): Boolean {
        return true
    }

    private fun getAuraPool(player: EntityPlayer): Float {
        var tot = 0.0f
        var zz: Int
        when (this.area) {
            1 -> {
                tot = AuraHandler.getVis(player.world, player.position)
                val var7 = EnumFacing.HORIZONTALS
                zz = var7.size

                for (var5 in 0 until zz) {
                    val face = var7[var5]
                    tot += AuraHandler.getVis(player.world, player.position.offset(face, 16))
                }

                return tot
            }
            2 -> {
                tot = 0.0f

                for (xx in -1..1) {
                    zz = -1
                    while (zz <= 1) {
                        tot += AuraHandler.getVis(player.world, player.position.add(xx * 16, 0, zz * 16))
                        ++zz
                    }
                }

                return tot
            }
            else -> {
                tot = AuraHandler.getVis(player.world, player.position)
                return tot
            }
        }
    }

    override fun consumeVis(`is`: ItemStack, player: EntityPlayer, amount: Float, crafting: Boolean, sim: Boolean): Boolean {
        var amount = amount
        amount *= this.getConsumptionModifier(`is`, player, crafting)
        val tot = this.getAuraPool(player)
        when {
            tot < amount -> return false
            sim -> return true
            else -> {
                var i: Float
                var zz: Int
                when (this.area) {
                    1 -> {
                        i = amount / 5.0f

                        while (amount > 0.0f) {
                            if (i > amount) {
                                i = amount
                            }

                            amount -= AuraHandler.drainVis(player.world, player.position, i, sim)
                            if (amount <= 0.0f) {
                                return amount <= 0.0f
                            }

                            if (i > amount) {
                                i = amount
                            }

                            val var12 = EnumFacing.HORIZONTALS
                            zz = var12.size

                            for (var10 in 0 until zz) {
                                val face = var12[var10]
                                amount -= AuraHandler.drainVis(player.world, player.position.offset(face, 16), i, sim)
                                if (amount <= 0.0f) {
                                    return amount <= 0.0f
                                }
                            }
                        }

                        return amount <= 0.0f
                    }
                    2 -> {
                        i = amount / 9.0f

                        while (amount > 0.0f) {
                            if (i > amount) {
                                i = amount
                            }

                            for (xx in -1..1) {
                                zz = -1
                                while (zz <= 1) {
                                    amount -= AuraHandler.drainVis(player.world, player.position.add(xx * 16, 0, zz * 16), i, sim)
                                    if (amount <= 0.0f) {
                                        return amount <= 0.0f
                                    }
                                    ++zz
                                }
                            }
                        }

                        return amount <= 0.0f
                    }
                    else -> {
                        amount -= AuraHandler.drainVis(player.world, player.position, amount, sim)
                        return amount <= 0.0f
                    }
                }
            }
        }
    }

    override fun getConsumptionModifier(`is`: ItemStack, player: EntityPlayer?, crafting: Boolean): Float {
        var consumptionModifier = 1.0f
        if (player != null) {
            consumptionModifier -= CasterManager.getTotalVisDiscount(player)
        }

        return Math.max(consumptionModifier, 0.1f)
    }

    override fun getFocus(stack: ItemStack): ItemFocus? {
        if (stack.hasTagCompound() && stack.tagCompound!!.hasKey("focus")) {
            val nbt = stack.tagCompound!!.getCompoundTag("focus")
            val fs = ItemStack(nbt)
            if (!fs.isEmpty) {
                return fs.item as ItemFocus
            }
        }

        return null
    }

    override fun getFocusStack(stack: ItemStack): ItemStack? {
        if (stack.hasTagCompound() && stack.tagCompound!!.hasKey("focus")) {
            val nbt = stack.tagCompound!!.getCompoundTag("focus")
            return ItemStack(nbt)
        } else {
            return null
        }
    }

    override fun setFocus(stack: ItemStack, focus: ItemStack?) {
        if (focus != null && !focus.isEmpty) {
            stack.setTagInfo("focus", focus.writeToNBT(NBTTagCompound()))
        } else {
            stack.tagCompound!!.removeTag("focus")
        }

    }

    override fun getRarity(itemstack: ItemStack): EnumRarity {
        return EnumRarity.UNCOMMON
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack?, worldIn: World?, tooltip: MutableList<String>?, flagIn: ITooltipFlag?) {
        if (stack!!.hasTagCompound()) {
            var text = ""
            val focus = this.getFocusStack(stack)
            if (focus != null && !focus.isEmpty) {
                val amt = (focus.item as ItemFocus).getVisCost(focus)
                if (amt > 0.0f) {
                    text = "§r" + this.myFormatter.format(amt.toDouble()) + " " + I18n.translateToLocal("item.Focus.cost1")
                }
            }

            tooltip!!.add(TextFormatting.ITALIC.toString() + "" + TextFormatting.AQUA + I18n.translateToLocal("tc.vis.cost") + " " + text)
        }

        if (this.getFocus(stack) != null) {
            tooltip!!.add(TextFormatting.BOLD.toString() + "" + TextFormatting.ITALIC + "" + TextFormatting.GREEN + this.getFocus(stack)!!.getItemStackDisplayName(this.getFocusStack(stack)!!))
            this.getFocus(stack)!!.addFocusInformation(this.getFocusStack(stack), worldIn, tooltip, flagIn)
        }

    }

    override fun onArmorTick(world: World?, player: EntityPlayer?, itemStack: ItemStack?) {
        super.onArmorTick(world, player, itemStack)
    }

    override fun onUpdate(`is`: ItemStack?, w: World?, e: Entity?, slot: Int, currentItem: Boolean) {
        if (!w!!.isRemote && e!!.ticksExisted % 10 == 0 && e is EntityPlayerMP) {
            val var6 = e.heldEquipment.iterator()

            while (var6.hasNext()) {
                val h = var6.next() as ItemStack
                if (h != null && !h.isEmpty && h.item is ICaster) {
                    this.updateAura(`is`, w, e as EntityPlayerMP)
                    break
                }
            }
        }

    }

    private fun updateAura(stack: ItemStack?, world: World, player: EntityPlayerMP) {
        var cv: Float
        var cf: Float
        var bv: Int
        cv = 0.0f
        cf = 0.0f
        bv = 0
        var ac: AuraChunk?
        var zz: Int
        when (this.area) {
            1 -> {
                ac = AuraHandler.getAuraChunk(world.provider.dimension, player.posX.toInt() shr 4, player.posZ.toInt() shr 4)
                if (ac != null) {
                    cv = ac.vis
                    cf = ac.flux
                    bv = ac.base.toInt()
                    val var12 = EnumFacing.HORIZONTALS
                    zz = var12.size

                    for (var10 in 0 until zz) {
                        val face = var12[var10]
                        ac = AuraHandler.getAuraChunk(world.provider.dimension, (player.posX.toInt() shr 4) + face.frontOffsetX, (player.posZ.toInt() shr 4) + face.frontOffsetZ)
                        if (ac != null) {
                            cv += ac.vis
                            cf += ac.flux
                            bv += ac.base
                        }
                    }
                }
            }
            2 -> {
                var xx = -1

                while (true) {
                    if (xx > 1) {
                        break
                    }

                    zz = -1
                    while (zz <= 1) {
                        ac = AuraHandler.getAuraChunk(world.provider.dimension, (player.posX.toInt() shr 4) + xx, (player.posZ.toInt() shr 4) + zz)
                        if (ac != null) {
                            cv += ac.vis
                            cf += ac.flux
                            bv += ac.base
                        }
                        ++zz
                    }

                    ++xx
                }
                ac = AuraHandler.getAuraChunk(world.provider.dimension, player.posX.toInt() shr 4, player.posZ.toInt() shr 4)
                if (ac != null) {
                    cv = ac.vis
                    cf = ac.flux
                    bv = ac.base.toInt()
                }
            }
            else -> {
                ac = AuraHandler.getAuraChunk(world.provider.dimension, player.posX.toInt() shr 4, player.posZ.toInt() shr 4)
                if (ac != null) {
                    cv = ac.vis
                    cf = ac.flux
                    bv = ac.base.toInt()
                }
            }
        }

        PacketHandler.INSTANCE.sendTo(PacketAuraToClient(AuraChunk(null as Chunk?, bv.toShort(), cv, cf)), player)
    }

    override fun onItemUseFirst(player: EntityPlayer?, world: World?, pos: BlockPos?, side: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float, hand: EnumHand?): EnumActionResult {
        val bs = world!!.getBlockState(pos!!)
        if (bs.block is IInteractWithCaster && (bs.block as IInteractWithCaster).onCasterRightClick(world, player!!.getHeldItem(hand), player, pos, side, hand)) {
            return EnumActionResult.PASS
        } else {
            val tile = world.getTileEntity(pos)
            if (tile != null && tile is IInteractWithCaster && (tile as IInteractWithCaster).onCasterRightClick(world, player!!.getHeldItem(hand), player, pos, side, hand)) {
                return EnumActionResult.PASS
            } else if (CasterTriggerRegistry.hasTrigger(bs)) {
                return if (CasterTriggerRegistry.performTrigger(world, player!!.getHeldItem(hand), player, pos, side, bs)) EnumActionResult.SUCCESS else EnumActionResult.FAIL
            } else {
                val fb = this.getFocusStack(player!!.getHeldItem(hand))
                if (fb != null && !fb.isEmpty) {
                    val core = ItemFocus.getPackage(fb)
                    val var13 = core.nodes.iterator()

                    while (var13.hasNext()) {
                        val fe = var13.next() as IFocusElement
                        if (fe is IFocusBlockPicker && player.isSneaking && world.getTileEntity(pos) == null) {
                            if (!world.isRemote) {
                                var isout = ItemStack(bs.block, 1, bs.block.getMetaFromState(bs))

                                try {
                                    if (bs !== Blocks.AIR) {
                                        val `is` = BlockUtils.getSilkTouchDrop(bs)
                                        if (`is` != null && !`is`.isEmpty) {
                                            isout = `is`.copy()
                                        }
                                    }
                                } catch (var17: Exception) {
                                }

                                this.storePickedBlock(player.getHeldItem(hand), isout)
                                return EnumActionResult.SUCCESS
                            }

                            player.swingArm(hand!!)
                            return EnumActionResult.PASS
                        }
                    }
                }

                return EnumActionResult.PASS
            }
        }
    }

    private fun generateSourceVector(e: Entity): RayTraceResult {
        var v = e.positionVector
        var mainhand = true
        if (e is EntityPlayer) {
            if (e.heldItemMainhand != null && e.heldItemMainhand.item is ICaster) {
                mainhand = true
            } else if (e.heldItemOffhand != null && e.heldItemOffhand.item is ICaster) {
                mainhand = false
            }
        }

        val posX = -MathHelper.cos(((e.rotationYaw - 0.5f) / 180.0f * 3.141593f).toDouble()) * 0.20000000298023224 * (if (mainhand) 1 else -1).toDouble()
        val posZ = -MathHelper.sin(((e.rotationYaw - 0.5f) / 180.0f * 3.141593f).toDouble()) * 0.30000001192092896 * (if (mainhand) 1 else -1).toDouble()
        val vl = e.lookVec
        v = v.addVector(posX, e.eyeHeight.toDouble() - 0.4000000014901161, posZ)
        v = v.add(vl)
        return RayTraceResult(e, v)
    }

    override fun onItemRightClick(world: World?, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
        val focusStack = this.getFocusStack(player.getHeldItem(hand))
        val focus = this.getFocus(player.getHeldItem(hand))
        if (focus != null && CasterManager.getCooldown(player)>0) {
            CasterManager.setCooldown(player, focus.getActivationTime(focusStack))
            val core = ItemFocus.getPackage(focusStack)
            if (player.isSneaking) {
                val var7 = core.nodes.iterator()

                while (var7.hasNext()) {
                    val fe = var7.next() as IFocusElement
                    if (fe is IFocusBlockPicker && player.isSneaking) {
                        return ActionResult(EnumActionResult.PASS, player.getHeldItem(hand))
                    }
                }
            }

            if (world!!.isRemote) {
                return ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand))
            } else if (this.consumeVis(player.getHeldItem(hand), player, focus.getVisCost(focusStack), false, false)) {
                FocusEngine.castFocusPackage(player, core)
                player.swingArm(hand)
                return ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand))
            } else {
                return ActionResult(EnumActionResult.FAIL, player.getHeldItem(hand))
            }
        } else {
            return super.onItemRightClick(world, player, hand)
        }
    }

    override fun getMaxItemUseDuration(itemstack: ItemStack?): Int {
        return 72000
    }

    override fun getItemUseAction(stack1: ItemStack?): EnumAction {
        return EnumAction.BOW
    }

    override fun getArchitectBlocks(stack: ItemStack, world: World, pos: BlockPos, side: EnumFacing, player: EntityPlayer): ArrayList<BlockPos>? {
        val focus = this.getFocus(stack)
        if (focus != null) {
            val fp = ItemFocus.getPackage(this.getFocusStack(stack))
            if (fp != null) {
                val var8 = fp.nodes.iterator()

                while (var8.hasNext()) {
                    val fe = var8.next() as IFocusElement
                    if (fe is IArchitect) {
                        return (fe as IArchitect).getArchitectBlocks(stack, world, pos, side, player)
                    }
                }
            }
        }

        return null
    }

    override fun showAxis(stack: ItemStack, world: World, player: EntityPlayer, side: EnumFacing, axis: IArchitect.EnumAxis): Boolean {
        val focus = this.getFocus(stack)
        if (focus != null) {
            val fp = ItemFocus.getPackage(this.getFocusStack(stack))
            if (fp != null) {
                val var8 = fp.nodes.iterator()

                while (var8.hasNext()) {
                    val fe = var8.next() as IFocusElement
                    if (fe is IArchitect) {
                        return (fe as IArchitect).showAxis(stack, world, player, side, axis)
                    }
                }
            }
        }

        return false
    }

    override fun getArchitectMOP(stack: ItemStack, world: World, player: EntityLivingBase): RayTraceResult? {
        val focus = this.getFocus(stack)
        if (focus != null) {
            val fp = ItemFocus.getPackage(this.getFocusStack(stack))
            if (fp != null && FocusEngine.doesPackageContainElement(fp, "thaumcraft.PLAN")) {
                return (FocusEngine.getElement("thaumcraft.PLAN") as IArchitect).getArchitectMOP(this.getFocusStack(stack), world, player)
            }
        }

        return null
    }

    override fun useBlockHighlight(stack: ItemStack): Boolean {
        return false
    }

    fun storePickedBlock(stack: ItemStack, stackout: ItemStack) {
        val item = NBTTagCompound()
        stack.setTagInfo("picked", stackout.writeToNBT(item))
    }

    override fun getPickedBlock(stack: ItemStack?): ItemStack? {
        if (stack != null && !stack.isEmpty) {
            var out: ItemStack? = null
            val focus = this.getFocus(stack)
            if (focus != null && stack.hasTagCompound() && stack.tagCompound!!.hasKey("picked")) {
                val fp = ItemFocus.getPackage(this.getFocusStack(stack))
                if (fp != null) {
                    val var5 = fp.nodes.iterator()

                    while (var5.hasNext()) {
                        val fe = var5.next() as IFocusElement
                        if (fe is IFocusBlockPicker) {
                            out = ItemStack(Blocks.AIR)

                            try {
                                out = ItemStack(stack.tagCompound!!.getCompoundTag("picked"))
                            } catch (var8: Exception) {
                            }

                            break
                        }
                    }
                }
            }

            return out
        } else {
            return ItemStack.EMPTY
        }
    }
}
