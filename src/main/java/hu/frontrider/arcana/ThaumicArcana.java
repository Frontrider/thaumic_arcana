package hu.frontrider.arcana;

import hu.frontrider.arcana.capabilities.CreatureEnchantCapability;
import hu.frontrider.arcana.capabilities.CreatureEnchantStorage;
import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import hu.frontrider.arcana.client.gui.GuiHandler;
import hu.frontrider.arcana.eventhandlers.FunctionEventManager;
import hu.frontrider.arcana.eventhandlers.LifecycleEventManager;
import hu.frontrider.arcana.network.creatureenchants.CreatureEnchantSyncMessage;
import hu.frontrider.arcana.network.creatureenchants.CreatureEnchantSyncMessageHandler;
import hu.frontrider.arcana.network.creatureenchants.CreatureEnchantSynchroniser;
import hu.frontrider.arcana.network.falldamage.FalldamageSyncMessage;
import hu.frontrider.arcana.network.falldamage.FalldamageSyncMessageHandler;
import hu.frontrider.arcana.recipes.AlchemyRecipes;
import hu.frontrider.arcana.recipes.ArcaneCraftingRecipes;
import hu.frontrider.arcana.recipes.FormulaCraftingRecipes;
import hu.frontrider.arcana.recipes.InfusionRecipes;
import hu.frontrider.arcana.recipes.formulacrafting.FormulaCraftingHandler;
import hu.frontrider.arcana.recipes.formulacrafting.FormulaRecipes;
import hu.frontrider.arcana.registrationhandlers.BlockRegistry;
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

import java.io.File;

@Mod(modid = ThaumicArcana.MODID,
        name = ThaumicArcana.NAME,
        version = ThaumicArcana.VERSION,
        dependencies="required-after:thaumcraft"

)
public class ThaumicArcana {
    public static final String MODID = "thaumic_arcana";
    public static final String NAME = "Thaumic Arcana";
    public static final String VERSION = "0.1.1";

    public static File ConfigDirectory;
    public static Logger logger;
    public static CreativeTabs TABARCANA = new CreativeTabArcana(CreativeTabs.getNextID(), MODID);
    public static final SimpleNetworkWrapper NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    @Mod.Instance(MODID)
    public static ThaumicArcana instance;

    @SidedProxy(clientSide = "hu.frontrider.arcana.client.ClientProxy", serverSide = "hu.frontrider.arcana.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        File suggestedConfigurationFile = event.getSuggestedConfigurationFile();
        MinecraftForge.EVENT_BUS.register(new BlockRegistry());

        ConfigDirectory = new File(suggestedConfigurationFile.getParent() + "/" + MODID + "/");

        proxy.preInit(event);
        CapabilityManager.INSTANCE.register(ICreatureEnchant.class, new CreatureEnchantStorage(), CreatureEnchantCapability::new);

        NETWORK_WRAPPER.registerMessage(CreatureEnchantSyncMessageHandler.class,CreatureEnchantSyncMessage.class,0,Side.CLIENT);
        NETWORK_WRAPPER.registerMessage(FalldamageSyncMessageHandler.class,FalldamageSyncMessage.class,1,Side.SERVER);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        FormulaRecipes.create();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);

        MinecraftForge.EVENT_BUS.register(new CreatureEnchantSynchroniser());
        MinecraftForge.EVENT_BUS.register(new FunctionEventManager());
        MinecraftForge.EVENT_BUS.register(new LifecycleEventManager());
        MinecraftForge.EVENT_BUS.register(new FormulaCraftingHandler());

        FormulaCraftingRecipes.initRecipes();
        AlchemyRecipes.register();
        InfusionRecipes.register();
        ArcaneCraftingRecipes.register();
        ResearchRegistry.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }
}
