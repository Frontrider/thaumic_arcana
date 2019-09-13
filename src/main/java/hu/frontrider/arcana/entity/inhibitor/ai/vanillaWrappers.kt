package hu.frontrider.arcana.entity.inhibitor.ai

import hu.frontrider.arcana.ThaumicArcana
import hu.frontrider.arcana.api.InhibitorAiWrapper
import hu.frontrider.arcana.api.Registered
import net.minecraft.entity.EntityCreature
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.ai.*
import net.minecraft.util.ResourceLocation

/**Stores the wrappers for the vanilla tasks*/

class EntityAIAttackMeleeWrapper(val speed:Double):InhibitorAiWrapper{
    override fun get(entityCreature: EntityCreature): EntityAIBase {
        return EntityAIAttackMelee(entityCreature,speed,true)
    }

    private val id = ResourceLocation( ThaumicArcana.MODID,"attack_attacker")
    override fun getID(): ResourceLocation {
        return id
    }
}


class EntityAIBreakDoorWrapper():InhibitorAiWrapper{
    override fun get(entityCreature: EntityCreature): EntityAIBase {
        return EntityAIBreakDoor(entityCreature)
    }

    val id = ResourceLocation(ThaumicArcana.MODID,"break_door")
    override fun getID(): ResourceLocation {
        return id
    }
}


class EntityAIWanderWrapper(val speed:Double,val chance:Int):InhibitorAiWrapper{
    override fun get(entityCreature: EntityCreature?): EntityAIBase {
        return EntityAIWander(entityCreature,speed,chance)
    }

    val id = ResourceLocation(ThaumicArcana.MODID,"run_around")
    override fun getID(): ResourceLocation {
        return id
    }
}

class EntityAIPanicWrapper(val speed: Double):InhibitorAiWrapper{

    override fun get(entityCreature: EntityCreature): EntityAIBase {
            return EntityAIPanic(entityCreature,speed)
    }

    val id = ResourceLocation(ThaumicArcana.MODID,"panic")
    override fun getID(): ResourceLocation {
        return id
    }
}

class EntityAIOpenDoorWrapper:InhibitorAiWrapper {
    val id = ResourceLocation(ThaumicArcana.MODID,"open_door")
    override fun getID(): ResourceLocation {
        return id
    }

    override fun get(entityCreature: EntityCreature): EntityAIBase {
       return EntityAIOpenDoor(entityCreature,false)
    }
}

class EntityAIOpenAndCloseDoorWrapper:InhibitorAiWrapper {
    val id = ResourceLocation(ThaumicArcana.MODID,"open_close_door")
    override fun getID(): ResourceLocation {
        return id
    }

    override fun get(entityCreature: EntityCreature): EntityAIBase {
        return EntityAIOpenDoor(entityCreature,true)
    }
}