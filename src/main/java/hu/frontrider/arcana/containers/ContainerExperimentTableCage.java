package hu.frontrider.arcana.containers;

import hu.frontrider.arcana.blocks.experiments.tiles.TileEntityExperimentTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerExperimentTableCage extends ContainerBase {

    public ContainerExperimentTableCage(InventoryPlayer playerInv, final TileEntityExperimentTable experimentTable) {
        IItemHandler inventory = experimentTable.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);

        this.addSlotToContainer(new SlotItemHandler(inventory, 0, 80, 35) {
            @Override
            public void onSlotChanged() {
                experimentTable.markDirty();
            }
        });

        this.addSlotToContainer(new SlotItemHandler(inventory, 1, 135, 35) {
            @Override
            public void onSlotChanged() {
                experimentTable.markDirty();
            }
        });


        placePlayerInv(playerInv);
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }


}
