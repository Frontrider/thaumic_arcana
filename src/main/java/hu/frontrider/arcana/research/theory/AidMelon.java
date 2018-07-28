package hu.frontrider.arcana.research.theory;

import net.minecraft.init.Blocks;
import thaumcraft.api.research.theorycraft.ITheorycraftAid;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class AidMelon implements ITheorycraftAid {
    @Override
    public Object getAidObject() {
        return Blocks.MELON_BLOCK;
    }

    @Override
    public Class<TheorycraftCard>[] getCards() {
        //noinspection unchecked
        return new Class[]{CardGrow.class,
                CardGrow.class,
                CardGrow.class};
    }
}
