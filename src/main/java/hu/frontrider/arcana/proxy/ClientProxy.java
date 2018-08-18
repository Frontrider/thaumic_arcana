package hu.frontrider.arcana.proxy;

import hu.frontrider.arcana.client.EnchantRenderer;
import hu.frontrider.arcana.items.ItemRegistry;
import hu.frontrider.arcana.util.StringUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

public class ClientProxy extends CommonProxy {

    @SideOnly(Side.CLIENT)
    public static void initClient(ItemModelMesher mesher) {
        for (Item item : ItemRegistry.items) {
            ModelResourceLocation model = new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()).toString(), "inventory");
            ModelLoader.registerItemVariants(item, model);
            mesher.register(item, 0, model);
        }
        StringUtil.initialise();
        MinecraftForge.EVENT_BUS.register(StringUtil.getStringUtilKeyboardHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        initClient(Minecraft.getMinecraft().getRenderItem().getItemModelMesher());
        EnchantRenderer enchantRenderer = new EnchantRenderer();

        ForgeRegistries.ENTITIES.getEntries().forEach(resourceLocationEntityEntryEntry -> {
            Class<? extends Entity> entityClass = resourceLocationEntityEntryEntry.getValue().getEntityClass();
            Render<Entity> renderer =  Minecraft.getMinecraft().getRenderManager().getEntityClassRenderObject(entityClass);

            if (renderer instanceof RenderLivingBase) {
                ((RenderLivingBase<?>) renderer).addLayer(enchantRenderer);
            }
        });

        for (RenderPlayer playerRender : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {

            playerRender.addLayer(enchantRenderer);
        }

        MinecraftForge.EVENT_BUS.register(enchantRenderer);
    }
}
