package hu.frontrider.arcana

import com.google.gson.Gson
import hu.frontrider.arcana.AspectEffectMap
import hu.frontrider.arcana.sided.network.creatureenchants.CreatureEnchantSyncMessage
import hu.frontrider.arcana.sided.network.creatureenchants.CreatureEnchantSyncMessageHandler
import hu.frontrider.arcana.sided.network.creatureenchants.CreatureEnchantSynchroniser
import hu.frontrider.arcana.sided.network.falldamage.FalldamageSyncMessage
import hu.frontrider.arcana.sided.network.falldamage.FalldamageSyncMessageHandler
import hu.frontrider.arcana.registrationhandlers.recipes.AlchemyRecipes
import hu.frontrider.arcana.registrationhandlers.recipes.ArcaneCraftingRecipes
import hu.frontrider.arcana.registrationhandlers.recipes.InfusionRecipes
import hu.frontrider.arcana.research.ResearchEventManager
import hu.frontrider.arcana.research.ResearchRegistry

import hu.frontrider.arcana.sided.server.commands.ScarHelperCommand
import hu.frontrider.arcana.capabilities.creatureenchant.CreatureEnchantCapability
import hu.frontrider.arcana.capabilities.creatureenchant.CreatureEnchantStorage
import hu.frontrider.arcana.capabilities.creatureenchant.ICreatureEnchant
import hu.frontrider.arcana.capabilities.scar.IScarred
import hu.frontrider.arcana.capabilities.scar.ScarredCapability
import hu.frontrider.arcana.capabilities.scar.ScarredStorage
import hu.frontrider.arcana.eventhandlers.*
import hu.frontrider.arcana.recipes.BrewSlime
import hu.frontrider.arcana.registrationhandlers.*
import hu.frontrider.arcana.registrationhandlers.recipes.FakeRecipes
import hu.frontrider.arcana.server.commands.StructureSpawnerCommand
import hu.frontrider.arcana.sided.GuiHandler
import hu.frontrider.arcana.util.CreativeTabArcana
import hu.frontrider.thaumcraft.researchloader.ResearchCategoryJson
import net.minecraft.block.BlockDispenser
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.dispenser.BehaviorDefaultDispenseItem
import net.minecraft.dispenser.IBlockSource
import net.minecraft.entity.passive.EntityChicken
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.brewing.BrewingRecipeRegistry
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
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import org.apache.logging.log4j.Logger
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import java.io.File


@Mod(
        modid = ThaumicArcana.MODID,
        name = ThaumicArcana.NAME,
        version = ThaumicArcana.VERSION,
        dependencies = "required-after:thaumcraft;required-after:forgelin;before:jei;required-after:thaumcraftresearchloader",
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
        MinecraftForge.EVENT_BUS.register(ToolEvents())
        MinecraftForge.EVENT_BUS.register(ScarEvents())
        MinecraftForge.EVENT_BUS.register(UnluckHandler())

        ConfigDirectory = File(suggestedConfigurationFile.parent + "/" + MODID + "/")

        proxy.preInit(event)
        CapabilityManager.INSTANCE.register<ICreatureEnchant>(ICreatureEnchant::class.java, CreatureEnchantStorage()) { CreatureEnchantCapability() }
        CapabilityManager.INSTANCE.register<IScarred>(IScarred::class.java, ScarredStorage()) { ScarredCapability() }

//CapabilityManager.INSTANCE.register<ITAImplants>(ITAImplants::class.java, ImplantStorage()) { TAImplants() }

        LootHandler().init()

        NETWORK_WRAPPER.registerMessage<CreatureEnchantSyncMessage, IMessage>(CreatureEnchantSyncMessageHandler::class.java, CreatureEnchantSyncMessage::class.java, 0, Side.CLIENT)
        NETWORK_WRAPPER.registerMessage<FalldamageSyncMessage, IMessage>(FalldamageSyncMessageHandler::class.java, FalldamageSyncMessage::class.java, 1, Side.SERVER)

        NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler())
        val researchFile = File(suggestedConfigurationFile.parent + "/thaumcraftresearchloader/research/$MODID.txt")
        researchFile.parentFile.mkdirs()
        if (!researchFile.exists()) {
            researchFile.createNewFile()
            val printWriter = researchFile.printWriter(Charsets.UTF_8)

            arrayOf(
                    "$MODID:research/biomancy",
                    "$MODID:research/biomancy/enchanting",
                    "$MODID:research/biomancy/plantproducts",
                    "$MODID:research/biomancy/animalproducts",
                    "$MODID:research/biomancy/golems",
                    "$MODID:research/biomancy/plantexperiments",
                    "$MODID:research/biomancy/metallurgy",
                    "$MODID:research/biomancy/slime",
                    "$MODID:research/gauntlet",
                    "$MODID:research/scans"
            ).forEach {
                printWriter.write(it + "\n")
            }
            printWriter.close()
        }
        val category = File(suggestedConfigurationFile.parent + "/thaumcraftresearchloader/category/$MODID.json")
        category.parentFile.mkdirs()
        if (!category.exists()) {
            category.createNewFile()
            val printWriter = category.printWriter(Charsets.UTF_8)

            val categoryFile = ResearchCategoryJson()
                    .setKey("BIOMANCY")
                    .setRequired_research("MINDBIOTHAUMIC")
                    .setAspectList(
                            AspectList().add(Aspect.ALCHEMY, 30).add(Aspect.LIFE, 10).add(Aspect.MAGIC, 10).add(Aspect.LIGHT, 5).add(Aspect.AVERSION, 5).add(Aspect.EARTH, 5).add(Aspect.WATER, 5)
                    )
                    .setIcon("$MODID:textures/research/cat_biomancy.png")
                    .setBackground("thaumcraft:textures/gui/gui_research_back_7.jpg")
                    .setBackground_overlay("thaumcraft:textures/gui/gui_research_back_over.png")

            printWriter.println(Gson().toJson(categoryFile))
            printWriter.close()
        }

    }

    @EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.init(event)
        AspectEffectMap.init()

        MinecraftForge.EVENT_BUS.register(CreatureEnchantSynchroniser())
        MinecraftForge.EVENT_BUS.register(FunctionEventManager())
        MinecraftForge.EVENT_BUS.register(LifecycleEventManager())
        MinecraftForge.EVENT_BUS.register(CurioDropEvents())

        OreDictionaryRegistry().init()
        ResearchEventManager().initHandlers()
        AlchemyRecipes().register()
        InfusionRecipes().register()
        ArcaneCraftingRecipes().register()
        ResearchRegistry().init()
        FocusRegistry().init()
        GolemRegistry().init()
        FakeRecipes().init()

        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegistry.incubated_egg, object:BehaviorDefaultDispenseItem() {
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
        FurnaceRecipes.instance().addSmelting(slimeMeat, ItemStack(slimeSteak),.1f)
        BrewingRecipeRegistry.addRecipe(BrewSlime())
    }

    @EventHandler
    fun serverLoad(event: FMLServerStartingEvent) {
        event.registerServerCommand(StructureSpawnerCommand())
        event.registerServerCommand(ScarHelperCommand())

    }

    const val MODID = "thaumic_arcana"
    const val NAME = "Thaumic Arcana"
    const val VERSION = "0.6.0"

    lateinit var ConfigDirectory: File
    lateinit var logger: Logger
    var TABARCANA: CreativeTabs = CreativeTabArcana(CreativeTabs.getNextID(), MODID)
    val NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(MODID)

    @SidedProxy(clientSide = "hu.frontrider.arcana.sided.client.ClientProxy", serverSide = "hu.frontrider.arcana.CommonProxy")
    lateinit var proxy: CommonProxy

    @GameRegistry.ObjectHolder("$MODID:slime_meat_raw")
    lateinit var slimeMeat: Item
    @GameRegistry.ObjectHolder("$MODID:slime_meat_cooked")
    lateinit var slimeSteak: Item
}
