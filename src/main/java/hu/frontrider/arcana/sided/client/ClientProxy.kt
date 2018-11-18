package hu.frontrider.arcana.sided.client

import hu.frontrider.arcana.CommonProxy
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle
import hu.frontrider.arcana.registrationhandlers.ItemRegistry
import hu.frontrider.arcana.sided.client.commands.ReloadOffsetsCommand
import hu.frontrider.arcana.sided.client.rendering.EnchantRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.ItemModelMesher
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.client.renderer.entity.RenderLivingBase
import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import java.awt.*
import java.util.Objects

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.content.blocks.effect.tiles.TileEssentiaMine
import hu.frontrider.arcana.sided.client.rendering.implantmodel.ImplantRenderer
import net.minecraft.block.Block
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import thaumcraft.api.aspects.Aspect

class ClientProxy : CommonProxy() {

    override fun init(event: FMLInitializationEvent) {
        super.init(event)
        initClient(Minecraft.getMinecraft().renderItem.itemModelMesher)
        val enchantRenderer = EnchantRenderer()

        ForgeRegistries.ENTITIES.entries.forEach { resourceLocationEntityEntryEntry ->
            val entityClass = resourceLocationEntityEntryEntry.value.entityClass
            val renderer = Minecraft.getMinecraft().renderManager.getEntityClassRenderObject<Entity>(entityClass)

            if (renderer is RenderLivingBase<*>) {
                (renderer as RenderLivingBase<*>).addLayer<EntityLivingBase, EnchantRenderer>(enchantRenderer)
            }
        }

        for (playerRender in Minecraft.getMinecraft().renderManager.skinMap.values) {
            playerRender.addLayer<EntityLivingBase, EnchantRenderer>(enchantRenderer)
            playerRender.addLayer<EntityPlayer, ImplantRenderer>(ImplantRenderer())
        }

        MinecraftForge.EVENT_BUS.register(enchantRenderer)

        ClientCommandHandler.instance.registerCommand(ReloadOffsetsCommand(enchantRenderer))

        val registry = GameRegistry.findRegistry(EnchantingBaseCircle::class.java)

        Minecraft.getMinecraft().itemColors.registerItemColorHandler(
                IItemColor { stack, _ ->
                    val tagCompound = stack.tagCompound ?: return@IItemColor 0

                    if (!tagCompound.hasKey("modifier"))
                        return@IItemColor 0

                    val modifier = tagCompound.getString("modifier")

                    val value = registry.getValue(ResourceLocation(modifier))!!
                    val color = value.color

                    Color(color.r, color.g, color.b).rgb
                }, enchantModifier!!)

        Minecraft.getMinecraft().itemColors.registerItemColorHandler(
                IItemColor { stack, _ ->
                    val tagCompound = stack.tagCompound ?: return@IItemColor 0

                    if (!tagCompound.hasKey("aspect"))
                        return@IItemColor 0

                    Aspect.getAspect(tagCompound.getString("aspect"))!!.color
                }, infusedSlime)

        Minecraft.getMinecraft().blockColors.registerBlockColorHandler(
                IBlockColor { state, access, pos, tintindex ->
                    val tile = access!!.getTileEntity(pos!!)
                    (tile as? TileEssentiaMine)?.aspect?.color ?: 0
                }, essentia_mine)
    }

    companion object {
        @GameRegistry.ObjectHolder("$MODID:enchant_modifier")
        internal var enchantModifier: Item? = null

        @GameRegistry.ObjectHolder("$MODID:infused_slime")
        internal lateinit var infusedSlime: Item


        @GameRegistry.ObjectHolder("$MODID:essentia_mine")
        internal lateinit var essentia_mine: Block

        @SideOnly(Side.CLIENT)
        fun initClient(mesher: ItemModelMesher) {
            for (item in ItemRegistry.items) {
                val model = ModelResourceLocation(Objects.requireNonNull<ResourceLocation>(item.registryName).toString(), "inventory")
                ModelLoader.registerItemVariants(item, model)
                mesher.register(item, 0, model)
            }

        }
    }
}
