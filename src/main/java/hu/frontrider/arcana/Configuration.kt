package hu.frontrider.arcana

import hu.frontrider.arcana.ThaumicArcana.MODID
import net.minecraftforge.common.config.Config

@Config(modid = MODID)
object Configuration {

    @Config.Comment("Toggles weather or not it should enable the platinum recipe.", "This one has a toggle, because it can be truly game breaking.", "Turn it on, if metal transmutation is the intended way of getting resources.")
    @Config.Name("Enable platinum")
    var enablePlatinum = false

    @Config.Comment("a list of entities that can not be enchanted.", "Slimes are blacklisted, because the rendering can never be correct. (because of the varying size)")
    @Config.Name("Enchantable entity blacklist")
    var entityBlacklist = arrayOf("minecraft:slime")

    @Config.Comment("Items that you can use in cages to feed the labrats.")
    @Config.Name("Labrat food list")
    var rodentFoodWhitelist = arrayOf("minecraft:carrot", "minecraft:potato")

    @Config.Comment("This segment can be used to load custom research files into thaumcraft.", "Intended to be used by packmakers, to gain access to the thaumonomicon.", "The defaults are also here, so that you can replace them.")
    @Config.Name("Research configuration")
    var research = arrayOf("$MODID:research/metal_transmutation",

            "$MODID:research/biomancy",
            "$MODID:research/biomancy/enchanting",
            "$MODID:research/biomancy/plantproducts",
            "$MODID:research/biomancy/animalproducts",

            "$MODID:research/terramancy")
}
