package hu.frontrider.arcana.content.blocks.effect


import hu.frontrider.arcana.core.capabilities.creatureenchant.CreatureEnchantProvider.Companion.CREATURE_ENCHANT_CAPABILITY
import net.minecraft.block.SoundType
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import thaumcraft.api.aura.AuraHelper

abstract class BlockArcaneStoneEnchants(name:String,val status:Boolean):BlockArcaneStoneBase(name) {
    init {
        this.setHardness(2.5f)
        this.soundType = SoundType.STONE
    }

    override fun activate(worldIn: World, pos: BlockPos, entityIn: Entity) {
        if(entityIn.hasCapability(CREATURE_ENCHANT_CAPABILITY,null)){
            val capability = entityIn.getCapability(CREATURE_ENCHANT_CAPABILITY, null)!!
            if(capability.hasEnchant()== status)
                return

            if(AuraHelper.drainVis(worldIn,pos,2.0f,true)<2)
                return

            AuraHelper.drainVis(worldIn,pos,2.0f,false)
            capability.setEnabledStatus(status)
        }
    }

    override fun getBoundingBox(state: IBlockState?, source: IBlockAccess?, pos: BlockPos?): AxisAlignedBB {
        return AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.9375, 1.0)
    }

    override fun isOpaqueCube(state: IBlockState?): Boolean {
        return false
    }

    override fun isFullCube(state: IBlockState?): Boolean {
        return false
    }

}

class BlockArcaneStoneDisableEnchants:BlockArcaneStoneEnchants("paving_stone_disable_enchants",false)
class BlockArcaneStoneEnableEnchants:BlockArcaneStoneEnchants("paving_stone_enable_enchants",true)