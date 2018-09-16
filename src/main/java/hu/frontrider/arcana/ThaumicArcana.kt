package hu.frontrider.arcana

import hu.frontrider.arcana.core.eventhandlers.FunctionEventManager
import hu.frontrider.arcana.core.eventhandlers.LifecycleEventManager
import hu.frontrider.arcana.sided.client.gui.formula.FormulaTextManager
import hu.frontrider.arcana.sided.network.creatureenchants.CreatureEnchantSyncMessage
import hu.frontrider.arcana.sided.network.creatureenchants.CreatureEnchantSyncMessageHandler
import hu.frontrider.arcana.sided.network.creatureenchants.CreatureEnchantSynchroniser
import hu.frontrider.arcana.sided.network.falldamage.FalldamageSyncMessage
import hu.frontrider.arcana.sided.network.falldamage.FalldamageSyncMessageHandler
import hu.frontrider.arcana.registrationhandlers.recipes.AlchemyRecipes
import hu.frontrider.arcana.registrationhandlers.recipes.ArcaneCraftingRecipes
import hu.frontrider.arcana.registrationhandlers.recipes.FormulaCraftingRecipes
import hu.frontrider.arcana.registrationhandlers.recipes.InfusionRecipes
import hu.frontrider.arcana.core.formulacrafting.FormulaCraftingHandler
import hu.frontrider.arcana.core.formulacrafting.FormulaRecipeLoader
import hu.frontrider.arcana.content.research.ResearchEventManager
import hu.frontrider.arcana.content.research.ResearchRegistry
import hu.frontrider.arcana.core.capabilities.CreatureEnchantCapability
import hu.frontrider.arcana.core.capabilities.CreatureEnchantStorage
import hu.frontrider.arcana.core.capabilities.ICreatureEnchant
import hu.frontrider.arcana.registrationhandlers.*
import hu.frontrider.arcana.server.commands.StructureSpawnerCommand
import hu.frontrider.arcana.sided.client.gui.GuiHandler
import hu.frontrider.arcana.util.CreativeTabArcana
import net.minecraft.block.BlockDispenser
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.dispenser.BehaviorDefaultDispenseItem
import net.minecraft.dispenser.IBlockSource
import net.minecraft.entity.passive.EntityChicken
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.event.FMLServerStartingEvent
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.relauncher.Side
import org.apache.logging.log4j.Logger
import java.io.File

@Mod(
        modid = ThaumicArcana.MODID,
        name = ThaumicArcana.NAME,
        version = ThaumicArcana.VERSION,
        dependencies = "required-after:thaumcraft;required-after:forgelin",
        modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter"
)
object ThaumicArcana {

    @EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        logger = event.modLog
        val suggestedConfigurationFile = event.suggestedConfigurationFile
        println("adding registration handlers")

        MinecraftForge.EVENT_BUS.register(BlockRegistry())
        MinecraftForge.EVENT_BUS.register(ItemRegistry())

        MinecraftForge.EVENT_BUS.register(CreatureEnchantRegistry())

        ConfigDirectory = File(suggestedConfigurationFile.parent + "/" + MODID + "/")

        proxy.preInit(event)
        CapabilityManager.INSTANCE.register<ICreatureEnchant>(ICreatureEnchant::class.java, CreatureEnchantStorage()) { CreatureEnchantCapability() }

        NETWORK_WRAPPER.registerMessage<CreatureEnchantSyncMessage, IMessage>(CreatureEnchantSyncMessageHandler::class.java, CreatureEnchantSyncMessage::class.java, 0, Side.CLIENT)
        NETWORK_WRAPPER.registerMessage<FalldamageSyncMessage, IMessage>(FalldamageSyncMessageHandler::class.java, FalldamageSyncMessage::class.java, 1, Side.SERVER)

        NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler())

    }

    @EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.init(event)

        MinecraftForge.EVENT_BUS.register(CreatureEnchantSynchroniser())
        MinecraftForge.EVENT_BUS.register(FunctionEventManager())
        MinecraftForge.EVENT_BUS.register(LifecycleEventManager())
        MinecraftForge.EVENT_BUS.register(FormulaCraftingHandler())

        FormulaRecipeLoader.load()
        ResearchEventManager.initHandlers()
        FormulaCraftingRecipes.initRecipes()
        AlchemyRecipes.register()
        InfusionRecipes.register()
        ArcaneCraftingRecipes().register()
        ResearchRegistry.init()
        FocusRegistry.init()
        GolemRegistry.init()

        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegistry.incubated_egg, object : BehaviorDefaultDispenseItem() {
            private val dispenseBehavior = BehaviorDefaultDispenseItem()

            public override fun dispenseStack(source: IBlockSource, stack: ItemStack): ItemStack {
                val enumfacing = source.blockState.getValue(BlockDispenser.FACING)
                val world = source.world
                val d0 = source.x + (enumfacing.frontOffsetX.toFloat() * 1.125f).toDouble()
                val d1 = source.y + (enumfacing.frontOffsetY.toFloat() * 1.125f).toDouble()
                val d2 = source.z + (enumfacing.frontOffsetZ.toFloat() * 1.125f).toDouble()
                val blockpos = source.blockPos.offset(enumfacing)
                val material = world.getBlockState(blockpos).material

                val d3 = if (Material.WATER == material) {
                    1.0
                } else {
                    if (Material.AIR != material || Material.WATER != world.getBlockState(blockpos.down()).material) {
                        return this.dispenseBehavior.dispense(source, stack)
                    }

                    0.0
                }

                val chicken = EntityChicken(world)
                chicken.rotationYaw = enumfacing.horizontalAngle
                chicken.growingAge = -24000

                chicken.posX = d0
                chicken.posY = d1 + d3
                chicken.posZ = d2

                world.spawnEntity(chicken)
                stack.shrink(1)

                return stack
            }

        })
    }

    @EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        FormulaTextManager.registerFormulaTexts()
    }

    @EventHandler
    fun serverLoad(event: FMLServerStartingEvent) {
        event.registerServerCommand(StructureSpawnerCommand())
    }

    const val MODID = "thaumic_arcana"
    const val NAME = "Thaumic Arcana"
    const val VERSION = "0.3.2"

    lateinit var ConfigDirectory: File
    lateinit var logger: Logger
    var TABARCANA: CreativeTabs = CreativeTabArcana(CreativeTabs.getNextID(), MODID)
    val NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(MODID)

    @SidedProxy(clientSide = "hu.frontrider.arcana.sided.client.ClientProxy", serverSide = "hu.frontrider.arcana.CommonProxy")
    lateinit var proxy: CommonProxy

}
