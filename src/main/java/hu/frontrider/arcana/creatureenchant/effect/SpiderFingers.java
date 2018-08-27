package hu.frontrider.arcana.creatureenchant.effect;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.network.falldamage.FalldamageSyncMessage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.ThaumicArcana.NETWORK_WRAPPER;

public class SpiderFingers extends CreatureEnchant {
    public SpiderFingers() {
        super(new ResourceLocation(MODID, "spider"), "spider");
    }

    @SubscribeEvent
    public void tick(LivingEvent.LivingUpdateEvent event) {

        EntityLivingBase entityLiving = event.getEntityLiving();
        if (getEnchantLevel(entityLiving, this) <= 0)
            return;

        if (isEntityNextToAWall(entityLiving.world, entityLiving)) {
            makeEntityClimb(entityLiving.world, entityLiving);
        }
    }

    void makeEntityClimb(World world, EntityLivingBase entityLivingBase) {


        if(entityLivingBase.moveForward>0) {
            entityLivingBase.motionY = entityLivingBase.moveForward/2;
            entityLivingBase.moveForward =0.0f;
        }

        if (entityLivingBase.isSneaking()) {
            entityLivingBase.motionY = 0.0;
            if (world.isRemote) {
                NETWORK_WRAPPER.sendToServer(new FalldamageSyncMessage());
            }
        }
    }

    boolean isEntityNextToAWall(World world, EntityLivingBase entityLivingBase) {
        BlockPos position = entityLivingBase.getPosition();
        EnumFacing facing = entityLivingBase.getHorizontalFacing();
        return isBlockThere(world, position.offset(facing));
    }

    boolean isBlockThere(World world, BlockPos pos) {
        IBlockState blockState = world.getBlockState(pos);
        return !blockState.getBlock().isPassable(world, pos);
    }

    @Override
    public AspectList formula() {
        return new AspectList()
                .merge(Aspect.BEAST, 50)
                .merge(Aspect.LIFE, 20)
                .merge(Aspect.SENSES, 40)
                .merge(Aspect.TRAP, 60);
    }

    @Override
    public String getResearch() {
        return "CREATURE_ENCHANT_ADVANCED";
    }
}
