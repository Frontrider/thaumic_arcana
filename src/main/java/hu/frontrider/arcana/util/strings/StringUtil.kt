package hu.frontrider.arcana.util.strings

import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.input.Keyboard
import java.util.function.Consumer

@SideOnly(Side.CLIENT)
object StringUtil {
    var stringUtilKeyboardHandler: StringUtilKeyboardHandler? = null
        private set

    fun initialise() {
        stringUtilKeyboardHandler = StringUtilKeyboardHandler()
    }

    /**
     * gives back a helper class, that can be used to construct configurable descriptions
     */
    fun swappedDescription(description: List<String>): DescriptionHelper {
        return DescriptionHelper(description)
    }

    class DescriptionHelper (private val description: List<String>) {
        private var normalDescription: Consumer<List<String>>? = null
        private var shiftedDescription: Consumer<List<String>>? = null
        private var permanentDescription: Consumer<List<String>>? = null

        fun permanent(permanentDescription: Consumer<List<String>>): DescriptionHelper {
            this.permanentDescription = permanentDescription

            return this
        }


        fun normal(normalDescription: Consumer<List<String>>): DescriptionHelper {
            this.normalDescription = normalDescription
            return this
        }

        fun shifted(shiftedDescription: Consumer<List<String>>): DescriptionHelper {
            this.shiftedDescription = shiftedDescription
            return this
        }

        fun render(): List<String> {

            if (permanentDescription != null)
                permanentDescription!!.accept(description)

            if (isShifted) {
                if (shiftedDescription != null)
                    shiftedDescription!!.accept(description)
            } else {
                if (normalDescription != null)
                    normalDescription!!.accept(description)
            }
            return description
        }

        companion object {
            internal var isShifted = false
        }
    }


    class StringUtilKeyboardHandler {
        init {
            keyBindings = arrayOf(KeyBinding("key.descriptions.desc", Keyboard.KEY_LSHIFT, "key.thamic_arcana.category"))

            for (keyBinding in keyBindings) {
                ClientRegistry.registerKeyBinding(keyBinding)
            }
        }

        companion object {
            lateinit var keyBindings: Array<KeyBinding>

            @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
            fun onEvent(event: InputEvent.KeyInputEvent) {
                DescriptionHelper.isShifted = keyBindings[0].isPressed
            }
        }
    }


}
