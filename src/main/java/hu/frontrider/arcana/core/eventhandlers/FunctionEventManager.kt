package hu.frontrider.arcana.core.eventhandlers

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.content.items.EnchantmentUpgradePowder
import hu.frontrider.arcana.registrationhandlers.ItemRegistry.Companion.nutrient_mix
import net.minecraft.block.Block
import net.minecraft.entity.EntityAgeable
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraftforge.event.entity.living.LivingDropsEvent
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.eventhandler.Event
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder
import thaumcraft.api.capabilities.ThaumcraftCapabilities
import thaumcraft.api.capabilities.ThaumcraftCapabilities.KNOWLEDGE

class FunctionEventManager {

    @SubscribeEvent
    fun entityRightClick(event: PlayerInteractEvent.EntityInteract) {

        val target = event.target
        if (target is EntityAgeable) {
            if (target.isChild) {
                val player = event.entityPlayer
                val itemStack = player.heldItemMainhand
                val item = itemStack.item
                if (item == nutrient_mix) {
                    itemStack.shrink(1)
                    target.addGrowth(30000)
                }
            }
        }
    }

    //add the organic curio to the drops.
    @SubscribeEvent
    fun entityDrop(event: LivingDropsEvent) {
        val source = (event.source.trueSource ?: return)

        if(source !is EntityPlayer && source !is EntityPlayer)
            return
        val knowsResearchStrict = ThaumcraftCapabilities.knowsResearch(source, "BIOMANCY_BASICS@4")
        if (knowsResearchStrict) {
            val entity = event.entity
            val world = entity.world

            if(world.rand.nextInt(10)>4){
                return
            }

            val item = EntityItem(world, entity.posX, entity.posY, entity.posZ)
            val itemStack = ItemStack(organic_curio, world.rand.nextInt(2))
            item.item = itemStack
            world.spawnEntity(item)
        }
    }

    //create the disenchanter
    @SubscribeEvent
    fun createEnchantedBook(event: PlayerInteractEvent.RightClickBlock) {
        val world = event.world
        if (world.isRemote)
            return

        val player = event.entityPlayer

        val itemMainhand = player.heldItemMainhand
        val itemOffhand = player.heldItemOffhand

        if (world.getBlockState(event.pos).block === enchanting_table &&
                itemMainhand.item is EnchantmentUpgradePowder &&
                itemOffhand.item === enchant_book) {

            val pos = event.pos.up()

            val entityItem = EntityItem(world)
            entityItem.setPosition(pos.x + .5, pos.y + .5, pos.z + .5)
            val itemStack = ItemStack(enchant_book!!)
            entityItem.item = itemStack

            world.spawnEntity(entityItem)
            (itemMainhand.item as EnchantmentUpgradePowder).transferEnchants(itemMainhand, itemStack)

            val sound = SoundEvent(ResourceLocation("thaumcraft:dust"))
            world.playSound(null, pos, sound, SoundCategory.AMBIENT, 1f, 1.5f)

            for (i in 0..49) {
                world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK,
                        pos.x + .5,
                        pos.y + .5,
                        pos.z + .5,
                        0.0, 0.0, 0.0
                )
            }
        }
    }

    //must deny this event, in order to be able to use the book on it
    @SubscribeEvent
    fun selfEnchantFix(event: PlayerInteractEvent.RightClickBlock) {
        val world = event.world
        if (world.isRemote)
            return

        val player = event.entityPlayer
        val itemMainhand = player.heldItemMainhand

        if (itemMainhand.item === enchant_book
                || itemMainhand.item == enchant_basic
                || itemMainhand.item == enchant_advanced
                || itemMainhand.item == enchant_magical) {
            event.useBlock = Event.Result.DENY
        }

    }

    companion object {

        @ObjectHolder("thaumic_arcana:enchanting_powder_basic")
        private lateinit var enchant_basic: Item

        @ObjectHolder("thaumic_arcana:enchanting_powder_advanced")
        private lateinit var enchant_advanced: Item

        @ObjectHolder("thaumic_arcana:enchanting_powder_magical")
        private lateinit var enchant_magical: Item

        @ObjectHolder("$MODID:organic_curiosity")
        private lateinit var organic_curio: Item

        @ObjectHolder("thaumcraft:salis_mundus")
        private lateinit var sal_mundi: Item

        @ObjectHolder("minecraft:book")
        private lateinit var book: Item

        @ObjectHolder("thaumic_arcana:creature_enchanter")
        private lateinit var enchant_book: Item

        @ObjectHolder("minecraft:enchanting_table")
        private lateinit var enchanting_table: Block

        @ObjectHolder("thaumcraft:plank_greatwood")
        private lateinit var greatwood_planks: Block

        @ObjectHolder("minecraft:glass_bottle")
        private lateinit var glass_bottle: Item

        @ObjectHolder("minecraft:stick")
        private lateinit var stick: Item

        @ObjectHolder("$MODID:experiment_table")
        private lateinit var experiment_table: Block
    }
}
