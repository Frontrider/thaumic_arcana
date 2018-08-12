package hu.frontrider.arcana.research.theory;

import hu.frontrider.arcana.blocks.BlockRegistry;
import thaumcraft.api.research.theorycraft.ITheorycraftAid;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class AidTable implements ITheorycraftAid {

    @Override
    public Object getAidObject() {
        return BlockRegistry.experiment_table;
    }

    @Override
    public Class<TheorycraftCard>[] getCards() {
        //noinspection unchecked
        return new Class[]{
                CardDissect.class,
                CardDissect.class,
                CardDissect.class,
                CardDissectDead.class,
                CardGrow.class,
                CardGrow.class,
                CardGrow.class,
                CardGrow.class,
                CardGrow.class};
    }

}
