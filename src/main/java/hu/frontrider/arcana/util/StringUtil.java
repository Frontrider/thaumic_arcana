package hu.frontrider.arcana.util;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;
import java.util.function.Consumer;

@SideOnly(Side.CLIENT)
public class StringUtil {
    private static StringUtilKeyboardHandler stringUtilKeyboardHandler;

    public static void initialise() {
        stringUtilKeyboardHandler = new StringUtilKeyboardHandler();
    }

    /**
     * gives back a helper class, that can be used to construct configurable descriptions
     */
    public static DescriptionHelper swappedDescription(List<String> description) {
        return new DescriptionHelper(description);
    }

    public static class DescriptionHelper {
        private final List<String> description;
        static boolean isShifted = false;
        private Consumer<List<String>> normalDescription;
        private Consumer<List<String>> shiftedDescription;
        private Consumer<List<String>> permanentDescription;

        private DescriptionHelper(List<String> description) {
            this.description = description;
        }

        public DescriptionHelper permanent(Consumer<List<String>> permanentDescription) {
            this.permanentDescription = permanentDescription;

            return this;
        }


        public DescriptionHelper normal(Consumer<List<String>> normalDescription) {
            this.normalDescription = normalDescription;
            return this;
        }

        public DescriptionHelper shifted(Consumer<List<String>> shiftedDescription) {
            this.shiftedDescription = shiftedDescription;
            return this;
        }

        public List<String> render() {

            if (permanentDescription != null)
                permanentDescription.accept(description);

            if (isShifted) {
                if (shiftedDescription != null)
                    shiftedDescription.accept(description);
            } else {
                if (normalDescription != null)
                    normalDescription.accept(description);
            }
            return description;
        }
    }


    static class StringUtilKeyboardHandler {
        public static KeyBinding[] keyBindings;

        StringUtilKeyboardHandler() {
            keyBindings = new KeyBinding[1];
            keyBindings[0] = new KeyBinding("key.descriptions.desc", Keyboard.KEY_LSHIFT, "key.thamic_arcana.category");

            for (KeyBinding keyBinding : keyBindings) {
                ClientRegistry.registerKeyBinding(keyBinding);
            }
        }

        @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
        public static void onEvent(InputEvent.KeyInputEvent event) {
            DescriptionHelper.isShifted = keyBindings[0].isPressed();
        }
    }

    public static StringUtilKeyboardHandler getStringUtilKeyboardHandler() {
        return stringUtilKeyboardHandler;
    }
}
