package hu.frontrider.arcana;

import hu.frontrider.arcana.capabilities.CreatureEnchantCapability;
import hu.frontrider.arcana.capabilities.CreatureEnchantStorage;
import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import hu.frontrider.arcana.creatureenchant.FertileEnchant;
import hu.frontrider.arcana.creatureenchant.ProtectionEnchant;
import hu.frontrider.arcana.creatureenchant.RespirationEnchant;
import hu.frontrider.arcana.creatureenchant.StrengthEnchant;
import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.eventhandlers.FunctionEventManager;
import hu.frontrider.arcana.eventhandlers.LifecycleEventManager;
import hu.frontrider.arcana.network.CreatureEnchantSyncMessage;
import hu.frontrider.arcana.network.CreatureEnchantSyncMessageHandler;
import hu.frontrider.arcana.network.CreatureEnchantSynchronizer;
import hu.frontrider.arcana.proxy.CommonProxy;
import hu.frontrider.arcana.recipes.AlchemyRecipes;
import hu.frontrider.arcana.recipes.ArcaneCraftingRecipes;
import hu.frontrider.arcana.recipes.InfusionRecipes;
import hu.frontrider.arcana.recipes.formulacrafting.FormulaRecipe;
import hu.frontrider.arcana.research.ResearchRegistry;
import hu.frontrider.arcana.util.CreativeTabArcana;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod(modid = ThaumicArcana.MODID, name = ThaumicArcana.NAME, version = ThaumicArcana.VERSION)
public class ThaumicArcana {
    public static final String MODID = "@MODID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VERSION@";

    public static Logger logger;
    public static CreativeTabs TABARCANA = new CreativeTabArcana(CreativeTabs.getNextID(), MODID);
    public static final SimpleNetworkWrapper NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    @SidedProxy(clientSide = "hu.frontrider.arcana.proxy.ClientProxy", serverSide = "hu.frontrider.arcana.proxy.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        CapabilityManager.INSTANCE.register(ICreatureEnchant.class, new CreatureEnchantStorage(), CreatureEnchantCapability::new);
        NETWORK_WRAPPER.registerMessage(CreatureEnchantSyncMessageHandler.class,CreatureEnchantSyncMessage.class,0,Side.CLIENT);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        List<CreatureEnchant> creatureEnchants = CreatureEnchant.getCreatureEnchants();

        creatureEnchants.add(new StrengthEnchant());
        creatureEnchants.add(new FertileEnchant());
        creatureEnchants.add(new ProtectionEnchant());
        creatureEnchants.add(new RespirationEnchant());

        for (CreatureEnchant creatureEnchant : creatureEnchants) {
            MinecraftForge.EVENT_BUS.register(creatureEnchant);
        }
        MinecraftForge.EVENT_BUS.register(new CreatureEnchantSynchronizer());
        MinecraftForge.EVENT_BUS.register(new FunctionEventManager());
        MinecraftForge.EVENT_BUS.register(new LifecycleEventManager());
        FormulaRecipe.registerRecipes();
        AlchemyRecipes.register();
        InfusionRecipes.register();
        ArcaneCraftingRecipes.register();
        ResearchRegistry.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }
}
