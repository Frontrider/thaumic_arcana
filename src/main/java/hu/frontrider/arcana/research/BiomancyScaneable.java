package hu.frontrider.arcana.research;

import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.api.research.IScanThing;

public class BiomancyScaneable implements IScanThing {
    @Override
    public boolean checkThing(EntityPlayer entityPlayer, Object o) {
        return false;
    }

    @Override
    public String getResearchKey(EntityPlayer entityPlayer, Object o) {
        return null;
    }

    @Override
    public void onSuccess(EntityPlayer player, Object object) {

    }
}
