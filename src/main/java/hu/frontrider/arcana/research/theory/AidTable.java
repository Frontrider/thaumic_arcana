package hu.frontrider.arcana.research.theory;

import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.research.theorycraft.ITheorycraftAid;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class AidTable implements ITheorycraftAid {
    @Override
    public Object getAidObject() {
        return BlocksTC.tableStone;
    }

    @Override
    public Class<TheorycraftCard>[] getCards() {
        //noinspection unchecked
        return new Class[]{
                CardDissect.class,
                CardDissect.class,
                CardDissect.class,
                CardDissectDead.class};
    }

}
