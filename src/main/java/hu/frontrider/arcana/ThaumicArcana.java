package hu.frontrider.arcana;

import hu.frontrider.arcana.capabilities.*;
import hu.frontrider.arcana.items.PlantBall;
import hu.frontrider.arcana.proxy.CommonProxy;
import hu.frontrider.arcana.research.ResearchRegistry;
import hu.frontrider.arcana.util.CreativeTabArcana;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ThaumicArcana.MODID, name = ThaumicArcana.NAME, version = ThaumicArcana.VERSION)
public class ThaumicArcana {
    public static final String MODID = "thaumic_arcana";
    public static final String NAME = "Thaumic Arcana";
    public static final String VERSION = "1.0";

    public static Logger logger;
    public static CreativeTabs TABARCANA = new CreativeTabArcana(CreativeTabs.getNextID(), "thaumic_arcana");


    @SidedProxy(clientSide = "hu.frontrider.arcana.proxy.ClientProxy", serverSide = "hu.frontrider.arcana.proxy.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        CapabilityManager.INSTANCE.register(ICreatureEnchant.class, new CreatureEnchantStorage(), CreatureEnchant::new);
        CapabilityManager.INSTANCE.register(IRelief.class, new ReliefStore(), Relief::new);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        ResearchRegistry.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        //we don't want to hold onto these references any longer.
        PlantBall.getTreeItems().clear();
        PlantBall.getSeedItems().clear();
    }
}
