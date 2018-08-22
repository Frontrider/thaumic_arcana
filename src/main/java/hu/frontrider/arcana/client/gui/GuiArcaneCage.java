package hu.frontrider.arcana.client.gui;

import hu.frontrider.arcana.blocks.tiles.TileEntityArcaneCage;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class GuiArcaneCage extends GuiContainer {
    @GameRegistry.ObjectHolder(MODID + ":experiment_table")
    Block experimentTable = null;

    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(MODID + ":textures/gui/experiment_table_cage.png");
    private InventoryPlayer playerInv;
    private final TileEntityArcaneCage experimentTableTileEntity;

    public GuiArcaneCage(Container container, InventoryPlayer playerInv, TileEntityArcaneCage tileEntityArcaneCage) {
        super(container);
        this.playerInv = playerInv;
        this.experimentTableTileEntity = tileEntityArcaneCage;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(BG_TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
}
