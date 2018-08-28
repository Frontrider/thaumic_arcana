package hu.frontrider.arcana.containers;

import hu.frontrider.arcana.blocks.experiments.tiles.TileEntityArcaneCage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerArcaneCage extends ContainerBase {

    public ContainerArcaneCage(InventoryPlayer playerInv, final TileEntityArcaneCage tileEntityArcaneCage) {
        IItemHandler inventory = tileEntityArcaneCage.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlotToContainer(new SlotItemHandler(inventory, 0, 80, 35) {
            @Override
            public void onSlotChanged() {
                tileEntityArcaneCage.markDirty();
            }
        });

        this.addSlotToContainer(new SlotItemHandler(inventory, 1, 135, 35) {
            @Override
            public void onSlotChanged() {
                tileEntityArcaneCage.markDirty();
            }
        });


        placePlayerInv(playerInv);
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }


}
