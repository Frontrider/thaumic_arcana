package hu.frontrider.arcana.items;

import hu.frontrider.arcana.items.formula.BaseFormula;
import hu.frontrider.arcana.util.AspectUtil;
import hu.frontrider.minecrafttestkit.MineCraftTest;
import hu.frontrider.minecrafttestkit.RegistrationHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@MineCraftTest
class FormulaTest {

    @RegistrationHelper.Register(Item.class)
    BaseFormula formula = new Formula();

    @BeforeEach
    void setUp() {

    }

    @Test
    void getItemStack() {
        AspectList aspectList = new AspectList().merge(Aspect.SENSES, 10);
        ItemStack itemStack = formula.getItemStack(aspectList);
        assertTrue(itemStack.hasTagCompound());
        AspectList storedAspects = AspectUtil.INSTANCE.getStoredAspects(itemStack);

        assertTrue(AspectUtil.INSTANCE.aspectListEquals(storedAspects,aspectList));
    }
}