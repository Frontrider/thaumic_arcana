package hu.frontrider.arcana.content.blocks

import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.item.EntityXPOrb
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import thaumcraft.api.ThaumcraftApiHelper
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aura.AuraHelper
import java.util.*

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
open class BlockTaintBase(materialIn: Material, name: String, private val pollutionOnBreak: Int) : BlockBase(materialIn, name) {

    override fun dropBlockAsItemWithChance(worldIn: World, pos: BlockPos, state: IBlockState, chance: Float, fortune: Int) {

    }

    override fun dropXpOnBlockBreak(worldIn: World, pos: BlockPos, amount: Int) {
        val i = worldIn.rand.nextInt(4)
        for (j in 0 until i) {
            val entityXPOrb = EntityXPOrb(worldIn)
            entityXPOrb.setPosition(pos.x + .5, pos.y + .5, pos.z + .5)
            worldIn.spawnEntity(entityXPOrb)
        }
    }

    override fun breakBlock(worldIn: World, pos: BlockPos, state: IBlockState) {
        super.breakBlock(worldIn, pos, state)
        AuraHelper.polluteAura(worldIn, pos, pollutionOnBreak.toFloat(), true)
        if (worldIn.isRemote)
            return

        val itemStack = ThaumcraftApiHelper.makeCrystal(Aspect.FLUX, worldIn.rand.nextInt(3))
        val fluxDrop = EntityItem(worldIn)
        fluxDrop.item = itemStack

        fluxDrop.posX = pos.x + .5
        fluxDrop.posY = pos.y.toDouble()
        fluxDrop.posZ = pos.z + .5
    }

    override fun randomTick(worldIn: World, pos: BlockPos, state: IBlockState, random: Random) {
        if (worldIn.rand.nextBoolean()) {
            AuraHelper.drainVis(worldIn, pos, 2f, false)
        }
    }
}
