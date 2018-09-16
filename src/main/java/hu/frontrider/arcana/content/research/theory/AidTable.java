package hu.frontrider.arcana.content.research.theory;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.research.theorycraft.ITheorycraftAid;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class AidTable implements ITheorycraftAid {

    @GameRegistry.ObjectHolder(MODID +":experiment_table")
    private static Block experiment_table = null;

    @Override
    public Object getAidObject() {
        return experiment_table;
    }

    @Override
    public Class<TheorycraftCard>[] getCards() {
        //noinspection unchecked
        return new Class[]{
                CardGrow.class,
                CardDissect.class,
                CardDissectDead.class,
                CardExperiment.class,
                CardGrow.class,
                CardDissect.class,
                CardGrow.class,
                CardDissectDead.class,
                CardGrow.class,
                CardDissect.class,
                CardGrow.class,
                };
    }

}
