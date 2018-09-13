package hu.frontrider.arcana.sided.client

import hu.frontrider.arcana.CommonProxy
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle
import hu.frontrider.arcana.registrationhandlers.ItemRegistry
import hu.frontrider.arcana.sided.client.commands.ReloadOffsetsCommand
import hu.frontrider.arcana.sided.client.rendering.EnchantRenderer
import hu.frontrider.arcana.util.StringUtil
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.ItemModelMesher
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.client.renderer.entity.Render
import net.minecraft.client.renderer.entity.RenderLivingBase
import net.minecraft.client.renderer.entity.RenderPlayer
import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

import java.awt.*
import java.util.Objects

import hu.frontrider.arcana.ThaumicArcana.MODID
import net.minecraft.entity.EntityLivingBase

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
        }

        MinecraftForge.EVENT_BUS.register(enchantRenderer)

        ClientCommandHandler.instance.registerCommand(ReloadOffsetsCommand(enchantRenderer))

        val registry = GameRegistry.findRegistry(EnchantingBaseCircle::class.java)

        Minecraft.getMinecraft().itemColors.registerItemColorHandler(
                IItemColor { stack, tintIndex ->
                    val tagCompound = stack.tagCompound ?: return@IItemColor 0

                    if (!tagCompound.hasKey("modifier"))
                        return@IItemColor 0

                    val modifier = tagCompound.getString("modifier")

                    val value = registry.getValue(ResourceLocation(modifier))!!
                    val color = value.color

                    Color(color.r, color.g, color.b).rgb
                }, enchantModifier!!)
    }

    companion object {
        @GameRegistry.ObjectHolder("$MODID:enchant_modifier")
        internal var enchantModifier: Item? = null

        @SideOnly(Side.CLIENT)
        fun initClient(mesher: ItemModelMesher) {
            for (item in ItemRegistry.items) {
                val model = ModelResourceLocation(Objects.requireNonNull<ResourceLocation>(item.registryName).toString(), "inventory")
                ModelLoader.registerItemVariants(item, model)
                mesher.register(item, 0, model)
            }
            StringUtil.initialise()
            MinecraftForge.EVENT_BUS.register(StringUtil.stringUtilKeyboardHandler)
        }
    }
}
