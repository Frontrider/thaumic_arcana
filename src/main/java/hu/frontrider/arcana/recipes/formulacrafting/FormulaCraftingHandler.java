package hu.frontrider.arcana.recipes.formulacrafting;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aura.AuraHelper;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class FormulaCraftingHandler {

    @GameRegistry.ObjectHolder(MODID + ":formula")
    private static Item formula = null;

    /**
     * sorting it to a map, for faster access later.
     *
     * @see IForgeRegistry<FormulaCraftingHandler>.getSlaveMap()
     */
    public FormulaCraftingHandler() {


    }

    @SubscribeEvent
    public void doCraft(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        if (world.isRemote)
            return;

        EntityPlayer player = event.getEntityPlayer();

        ItemStack itemMainhand = player.getHeldItemMainhand();
        ItemStack itemOffhand = player.getHeldItemOffhand();
        Map<Block, List<FormulaApplicationRecipe>> recipeHashMap = FormulaRecipes.INSTANCE.recipes;
        ItemStack formulaStack = itemOffhand.getItem() == formula ? itemOffhand : itemMainhand.getItem() == formula ? itemMainhand : null;
        ItemStack resourceStack = itemOffhand.getItem() != formula ? itemOffhand : itemMainhand.getItem() != formula ? itemMainhand : null;
        if (formulaStack != null) {
            if (formulaStack.getTagCompound() == null)
                return;

            AspectList aspectFormula = new AspectList();
            aspectFormula.readFromNBT(formulaStack.getTagCompound());

            for (Block block : recipeHashMap.keySet()) {
                if (world.getBlockState(event.getPos()).getBlock() != block)
                    continue;

                List<FormulaApplicationRecipe> formulaApplicationRecipes = recipeHashMap.get(block);
                for (FormulaApplicationRecipe formulaApplicationRecipe : formulaApplicationRecipes) {
                    if (formulaApplicationRecipe.canCraft(block, aspectFormula, resourceStack)) {
                        if(!ThaumcraftCapabilities.knowsResearch(player,formulaApplicationRecipe.getResearch()))
                            return;

                        if(craft(world, formulaApplicationRecipe, event.getPos(), formulaStack, resourceStack))
                            event.setUseBlock(Event.Result.DENY);
                    }
                }
            }
        }
    }

    public boolean craft(World world, FormulaApplicationRecipe recipe, BlockPos pos, ItemStack formulaStack, @Nullable ItemStack usedItem) {

        if(recipe.getVis()>0)
        if(AuraHelper.drainVis(world,pos,recipe.getVis(),true)!= recipe.getVis()){
            return false;
        }

        AuraHelper.drainVis(world,pos,recipe.getVis(),false);
        AuraHelper.polluteAura(world,pos,recipe.getPollution(),true);

        pos = pos.up();
        EntityItem entityItem = new EntityItem(world);
        entityItem.setItem(recipe.getResult());
        entityItem.setPosition(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);
        world.spawnEntity(entityItem);
        formulaStack.shrink(1);

        if (usedItem != null) {
            usedItem.shrink(1);
        }
        SoundEvent sound = new SoundEvent(new ResourceLocation("thaumcraft:dust"));
        world.playSound(null, pos, sound, SoundCategory.AMBIENT, 1, 1.5f);
        return true;
    }
}
