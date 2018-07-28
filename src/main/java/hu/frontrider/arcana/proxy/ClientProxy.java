package hu.frontrider.arcana.proxy;

import hu.frontrider.arcana.items.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
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
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        initClient(Minecraft.getMinecraft().getRenderItem().getItemModelMesher());
    }
}
