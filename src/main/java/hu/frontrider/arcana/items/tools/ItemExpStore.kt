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
        if(!player.isSneaking)
            return EnumActionResult.FAIL

        val tileEntity = worldIn.getTileEntity(pos)

        if (tileEntity == null || tileEntity !is TileJarBrain) {
            return EnumActionResult.FAIL
        }

        val exp: Int
        val curLevel = player.experienceLevel

        if (getExtraPlayerExperience(player) > 0) {
            exp = Math.min(getExtraPlayerExperience(player), getSpace(tileEntity))
            setPlayerExperience(player, getPlayerExperience(player) - exp)
            if (player.experienceLevel < curLevel) {
                setPlayerLevel(player, curLevel)
            }
            modifyExperience(tileEntity, exp-1)
        } else if (player.experienceLevel > 0) {
            exp = Math.min(getTotalExpForLevel(player.experienceLevel) - getTotalExpForLevel(player.experienceLevel - 1), getSpace(tileEntity))
            setPlayerExperience(player, getPlayerExperience(player) - exp)
            if (player.experienceLevel < curLevel - 1) {
                setPlayerLevel(player, curLevel - 1)
            }
            modifyExperience(tileEntity, exp-1)
        }
        return EnumActionResult.SUCCESS
    }

    /* HELPERS */
    fun getExperience(tile: TileJarBrain): Int {
        return tile.xp
    }

    fun getSpace(tile: TileJarBrain): Int {

        return getMaxExperience(tile) - getExperience(tile)
    }

    private fun getMaxExperience(tileJarBrain: TileJarBrain): Int {
        return tileJarBrain.xpMax
    }


    fun modifyExperience(tile: TileJarBrain, exp: Int): Int {

        var storedExp = getExperience(tile) + exp

        if (storedExp > getMaxExperience(tile)) {
            storedExp = getMaxExperience(tile)
        } else if (storedExp < 0) {
            storedExp = 0
        }
        tile.xp = storedExp

        return storedExp
    }

    fun getPlayerExperience(player: EntityPlayer): Int {

        return getTotalExpForLevel(player.experienceLevel) + getExtraPlayerExperience(player)
    }

    fun getLevelPlayerExperience(player: EntityPlayer): Int {

        return getTotalExpForLevel(player.experienceLevel)
    }

    fun getExtraPlayerExperience(player: EntityPlayer): Int {

        return Math.round(player.experience * player.xpBarCap())
    }

    fun setPlayerExperience(player: EntityPlayer, exp: Int) {

        player.experienceLevel = 0
        player.experience = 0.0f
        player.experienceTotal = 0

        addExperienceToPlayer(player, exp)
    }

    fun setPlayerLevel(player: EntityPlayer, level: Int) {

        player.experienceLevel = level
        player.experience = 0.0f
    }

    fun addExperienceToPlayer(player: EntityPlayer, exp: Int) {
        var exp = exp

        val i = Integer.MAX_VALUE - player.experienceTotal

        if (exp > i) {
            exp = i
        }
        player.experience += exp.toFloat() / player.xpBarCap().toFloat()
        player.experienceTotal += exp
        while (player.experience >= 1.0f) {
            player.experience = (player.experience - 1.0f) * player.xpBarCap().toFloat()
            addExperienceLevelToPlayer(player, 1)
            player.experience /= player.xpBarCap().toFloat()
        }
    }

    fun addExperienceLevelToPlayer(player: EntityPlayer, levels: Int) {

        player.experienceLevel += levels

        if (player.experienceLevel < 0) {
            player.experienceLevel = 0
            player.experience = 0.0f
            player.experienceTotal = 0
        }
    }

    fun getTotalExpForLevel(level: Int): Int {

        return if (level >= 32) (9 * level * level - 325 * level + 4440) / 2 else if (level >= 17) (5 * level * level - 81 * level + 720) / 2 else level * level + 6 * level
    }
}