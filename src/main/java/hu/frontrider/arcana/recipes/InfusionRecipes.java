package hu.frontrider.arcana.recipes;

import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import hu.frontrider.arcana.items.EnchantModifierDust;
import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.InfusionRecipe;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.registrationhandlers.ItemRegistry.enchanting_powder_advanced;
import static hu.frontrider.arcana.registrationhandlers.ItemRegistry.enchanting_powder_basic;
import static net.minecraft.init.Items.DYE;
import static thaumcraft.api.items.ItemsTC.salisMundus;

public class InfusionRecipes {

    @GameRegistry.ObjectHolder(MODID+":enchant_modifier")
    static Item modifier = null;

    @GameRegistry.ObjectHolder("minecraft:obsidian")
    static Block obsidian =null;

    @GameRegistry.ObjectHolder("minecraft:magma")
    static Block magma =null;


    @GameRegistry.ObjectHolder("minecraft:ender_eye")
    static Item enderEye = null;


    public static void register() {
        registerCreatureEnchants();
    }

    static void registerCreatureEnchants() {

        {
            ItemStack source = new ItemStack(enchanting_powder_basic);
            source.setTagCompound(null);

            ItemStack itemStack = new ItemStack(enchanting_powder_advanced);
            ThaumcraftApi.addInfusionCraftingRecipe(
                    new ResourceLocation(MODID, "enchant_powder_advanced"),
                    new InfusionRecipe("CREATURE_ENCHANT_ADVANCED",
                            itemStack,
                            10, (new AspectList())
                            .add(Aspect.MAGIC, 20)
                            .add(Aspect.LIFE, 50)
                            .add(Aspect.AURA, 20),
                            source,
                            new ItemStack(salisMundus),
                            new ItemStack(DYE, 1, 4),
                            new ItemStack(salisMundus),
                            new ItemStack(DYE, 1, 4),
                            new ItemStack(salisMundus),
                            new ItemStack(DYE, 1, 4)
                    )
            );
        }
        IForgeRegistry<EnchantingBaseCircle> registry = GameRegistry.findRegistry(EnchantingBaseCircle.class);
        {

            EnchantingBaseCircle normal = registry.getValue(new ResourceLocation(MODID, "normal"));

            ItemStack source = EnchantModifierDust.createItem(modifier,normal);

            ItemStack itemStack = new ItemStack(enchanting_powder_advanced);
            ThaumcraftApi.addInfusionCraftingRecipe(
                    new ResourceLocation(MODID, "enchant_modifier_basic"),
                    new InfusionRecipe("CREATURE_ENCHANT_MODIFICATION",
                            itemStack,
                            10, (new AspectList())
                            .add(Aspect.MAGIC, 20)
                            .add(Aspect.LIFE, 50)
                            .add(Aspect.AURA, 20)
                            .add(Aspect.BEAST,100),
                            source,
                            new ItemStack(BlocksTC.nitor.get(EnumDyeColor.WHITE)),
                            new ItemStack(BlocksTC.nitor.get(EnumDyeColor.WHITE)),
                            new ItemStack(BlocksTC.nitor.get(EnumDyeColor.WHITE)),
                            new ItemStack(BlocksTC.nitor.get(EnumDyeColor.WHITE)),
                            ThaumcraftApiHelper.makeCrystal(Aspect.ORDER),
                            ThaumcraftApiHelper.makeCrystal(Aspect.ORDER),
                            ThaumcraftApiHelper.makeCrystal(Aspect.MAGIC),
                            ThaumcraftApiHelper.makeCrystal(Aspect.MAGIC)
                    )
            );
        }
        {
            EnchantingBaseCircle negation = registry.getValue(new ResourceLocation(MODID, "negation"));
            ItemStack source = EnchantModifierDust.createItem(modifier,negation);

            ItemStack itemStack = new ItemStack(enchanting_powder_advanced);
            ThaumcraftApi.addInfusionCraftingRecipe(
                    new ResourceLocation(MODID, "enchant_modifier_negation"),
                    new InfusionRecipe("CREATURE_ENCHANT_MODIFICATION",
                            itemStack,
                            10, (new AspectList())
                            .add(Aspect.ENTROPY, 100)
                            .add(Aspect.LIFE, 50)
                            .add(Aspect.FLUX, 70)
                            .add(Aspect.BEAST,100),
                            source,
                            new ItemStack(obsidian),
                            new ItemStack(obsidian),
                            new ItemStack(magma),
                            new ItemStack(magma),
                            ThaumcraftApiHelper.makeCrystal(Aspect.FLUX),
                            ThaumcraftApiHelper.makeCrystal(Aspect.FLUX),
                            new ItemStack(enderEye),
                            new ItemStack(enderEye)
                    )
            );
        }
    }

}
