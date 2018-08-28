package hu.frontrider.arcana.research;

import hu.frontrider.arcana.research.researchevents.StartingFires;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
public class ResearchEventManager {
    
    public static void initHandlers()
    {
        registerhandler(new StartingFires());
    }
    
   static void registerhandler(Object handler){
        MinecraftForge.EVENT_BUS.register(handler);
    }
}
