package hu.frontrider.arcana.client;

import hu.frontrider.arcana.CommonProxy;
import hu.frontrider.arcana.client.commands.ReloadOffsetsCommand;
import hu.frontrider.arcana.client.rendering.EnchantRenderer;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import hu.frontrider.arcana.registrationhandlers.ItemRegistry;
import hu.frontrider.arcana.util.StringUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.awt.*;
import java.util.Objects;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class ClientProxy extends CommonProxy {
    @GameRegistry.ObjectHolder(MODID + ":enchant_modifier")
    static Item enchantModifier = null;

    @SideOnly(Side.CLIENT)
    public static void initClient(ItemModelMesher mesher) {
        for (Item item : ItemRegistry.Companion.getItems()) {
            ModelResourceLocation model = new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()).toString(), "inventory");
            ModelLoader.registerItemVariants(item, model);
            mesher.register(item, 0, model);
        }
        StringUtil.INSTANCE.initialise();
        MinecraftForge.EVENT_BUS.register(StringUtil.INSTANCE.getStringUtilKeyboardHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        initClient(Minecraft.getMinecraft().getRenderItem().getItemModelMesher());
        EnchantRenderer enchantRenderer = new EnchantRenderer();

        ForgeRegistries.ENTITIES.getEntries().forEach(resourceLocationEntityEntryEntry -> {
            Class<? extends Entity> entityClass = resourceLocationEntityEntryEntry.getValue().getEntityClass();
            Render<Entity> renderer = Minecraft.getMinecraft().getRenderManager().getEntityClassRenderObject(entityClass);

            if (renderer instanceof RenderLivingBase) {
                ((RenderLivingBase<?>) renderer).addLayer(enchantRenderer);
            }
        });

        for (RenderPlayer playerRender : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {

            playerRender.addLayer(enchantRenderer);
        }

        MinecraftForge.EVENT_BUS.register(enchantRenderer);

        ClientCommandHandler.instance.registerCommand(new ReloadOffsetsCommand(enchantRenderer));

        final IForgeRegistry<EnchantingBaseCircle> registry = GameRegistry.findRegistry(EnchantingBaseCircle.class);

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(
                new IItemColor() {
                    @Override
                    public int colorMultiplier(ItemStack stack, int tintIndex) {
                        NBTTagCompound tagCompound = stack.getTagCompound();
                        if (tagCompound == null)
                            return 0;

                        if (!tagCompound.hasKey("modifier"))
                            return 0;

                        String modifier = tagCompound.getString("modifier");

                        EnchantingBaseCircle value = registry.getValue(new ResourceLocation(modifier));
                        assert value != null;
                        EnchantingBaseCircle.Color color = value.getColor();

                        return new Color(color.getR(), color.getG(), color.getB()).getRGB();
                    }
                }
                ,enchantModifier);
    }
}
