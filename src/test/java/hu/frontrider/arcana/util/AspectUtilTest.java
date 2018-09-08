package hu.frontrider.arcana.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing the aspect utility")
class AspectUtilTest {

    @Test
    @DisplayName("Aspect equals")
    void aspectEquals() {
        Aspect earth = Aspect.EARTH;
        Aspect aspect = Aspect.EARTH;

        assertTrue(AspectUtil.INSTANCE.aspectEquals(earth, aspect));
    }

    @Nested
    @DisplayName("Aspect list ")

    class AspectListEquals {

        @Test
        @DisplayName("equals when they are the same")
        void aspectListEquals() {
            AspectList aspectList = new AspectList()
                    .merge(Aspect.EXCHANGE, 2)
                    .merge(Aspect.DESIRE, 3);

            AspectList aspectList1 = new AspectList()
                    .merge(Aspect.EXCHANGE, 2)
                    .merge(Aspect.DESIRE, 3);

            assertTrue(AspectUtil.INSTANCE.aspectListEquals(aspectList, aspectList1));
        }

        @Test
        @DisplayName("not equals when they have the same aspects, with different amounts")
        void aspectListDifferentAmounts() {
            AspectList aspectList = new AspectList()
                    .merge(Aspect.EXCHANGE, 2)
                    .merge(Aspect.DESIRE, 3);

            AspectList aspectList1 = new AspectList()
                    .merge(Aspect.EXCHANGE, 2)
                    .merge(Aspect.DESIRE, 4);

            assertFalse(AspectUtil.INSTANCE.aspectListEquals(aspectList, aspectList1));
        }

        @Test
        @DisplayName("not equals when one is the superset of the other")
        void aspectListDifferentSupersetList() {
            AspectList aspectList = new AspectList()
                    .merge(Aspect.EXCHANGE, 2)
                    .merge(Aspect.DESIRE, 3);

            AspectList aspectList1 = new AspectList()
                    .merge(Aspect.EXCHANGE, 2)
                    .merge(Aspect.DESIRE, 3)
                    .merge(Aspect.SOUL,12);

            assertFalse(AspectUtil.INSTANCE.aspectListEquals(aspectList, aspectList1));
        }
        @Test
        @DisplayName("not equals when the lists are different")
        void aspectListDifferentList() {
            AspectList aspectList = new AspectList()
                    .merge(Aspect.ENERGY, 2)
                    .merge(Aspect.MAGIC, 3);

            AspectList aspectList1 = new AspectList()
                    .merge(Aspect.EXCHANGE, 2)
                    .merge(Aspect.DESIRE, 3)
                    .merge(Aspect.SOUL,12);

            assertFalse(AspectUtil.INSTANCE.aspectListEquals(aspectList, aspectList1));
        }
    }
}