package hu.frontrider.arcana.items.tools

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import thaumcraft.common.tiles.devices.TileJarBrain

class ItemExpStore : Item() {

    override fun onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {

        val tileEntity = worldIn.getTileEntity(pos)
        if (tileEntity is TileJarBrain) {

            val expDiff = tileEntity.xpMax - tileEntity.xp

            if (expDiff > 0) {
                if (player.experienceTotal < expDiff) {
                    player.addExperience(-expDiff)
                    tileEntity.xp = tileEntity.xpMax
                } else {
                    tileEntity.xp += player.experienceTotal
                    player.addExperienceLevel(-player.experienceLevel)
                }
                tileEntity.markDirty()

                return EnumActionResult.SUCCESS
            }
        }
        return EnumActionResult.FAIL
    }
}