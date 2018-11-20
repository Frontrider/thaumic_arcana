package hu.frontrider.arcana.research

import net.minecraft.entity.player.EntityPlayer
import thaumcraft.api.research.IScanThing

class BiomancyScaneable : IScanThing {
    override fun checkThing(entityPlayer: EntityPlayer, o: Any): Boolean {
        return false
    }

    override fun getResearchKey(entityPlayer: EntityPlayer, o: Any): String? {
        return null
    }

    override fun onSuccess(player: EntityPlayer?, `object`: Any?) {

    }
}
