package hu.frontrider.arcana.creatureenchant.effect

import hu.frontrider.arcana.sided.network.falldamage.FalldamageSyncMessage
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.ThaumicArcana.NETWORK_WRAPPER
import hu.frontrider.arcana.creatureenchant.CreatureEnchant

class SpiderFingers : CreatureEnchant(ResourceLocation(MODID, "spider"), "spider") {

    override val research: String
        get() = "CREATURE_ENCHANT"

    @SubscribeEvent
    fun tick(event: LivingEvent.LivingUpdateEvent) {

        val entityLiving = event.entityLiving
        if (getEnchantLevel(entityLiving, this) <= 0)
            return

        if (isEntityNextToAWall(entityLiving.world, entityLiving)) {
            makeEntityClimb(entityLiving.world, entityLiving)
        }
    }

    internal fun makeEntityClimb(world: World, entityLivingBase: EntityLivingBase) {


        if (entityLivingBase.moveForward > 0) {
            entityLivingBase.motionY = (entityLivingBase.moveForward / 2).toDouble()
            entityLivingBase.moveForward = 0.0f
        }

        if (entityLivingBase.isSneaking) {
            entityLivingBase.motionY = 0.0
            if (world.isRemote) {
                NETWORK_WRAPPER.sendToServer(FalldamageSyncMessage())
            }
        }
    }

    internal fun isEntityNextToAWall(world: World, entityLivingBase: EntityLivingBase): Boolean {
        val position = entityLivingBase.position
        val facing = entityLivingBase.horizontalFacing
        return isBlockThere(world, position.offset(facing))
    }

    internal fun isBlockThere(world: World, pos: BlockPos): Boolean {
        val blockState = world.getBlockState(pos)
        return !blockState.block.isPassable(world, pos)
    }

    override fun formula(): AspectList {
        return AspectList()
                .merge(Aspect.BEAST, 50)
                .merge(Aspect.LIFE, 20)
                .merge(Aspect.SENSES, 40)
                .merge(Aspect.TRAP, 60)
    }
}
