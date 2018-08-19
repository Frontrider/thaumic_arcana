package hu.frontrider.arcana.blocks.tiles.experimenttable;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class WaterStorage extends FluidTank
{

    public WaterStorage(Fluid fluid, int amount, int capacity) {
        super(fluid, amount, capacity);

    }
    public WaterStorage(int capacity)
    {
        super(null, capacity);
    }


    @Override
    public boolean canFillFluidType(FluidStack fluid) {
        boolean canFillFluidType = super.canFillFluidType(fluid);
        return /*fluid.getFluid().getName().equals("water") && */canFillFluidType;
    }
}