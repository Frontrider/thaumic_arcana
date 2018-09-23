package hu.frontrider.arcana.core.eventhandlers

import hu.frontrider.arcana.content.items.EnchantmentUpgradePowder
import net.minecraft.block.Block
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityAgeable
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.eventhandler.Event
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder
import thaumcraft.api.aura.AuraHelper

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.registrationhandlers.ItemRegistry.Companion
import hu.frontrider.arcana.registrationhandlers.ItemRegistry.Companion.nutrient_mix

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

    @SubscribeEvent
    fun createBook(event: PlayerInteractEvent.RightClickBlock) {
        val world = event.world
        if (world.isRemote)
            return

        val player = event.entityPlayer


        val itemMainhand = player.heldItemMainhand
        val itemOffhand = player.heldItemOffhand

        if (world.getBlockState(event.pos).block === enchanting_table &&
                itemMainhand.item === sal_mundi &&
                itemOffhand.item === book && AuraHelper.drainVis(world, event.pos, 20f, true) == 100f) {
            itemMainhand.shrink(1)
            itemOffhand.shrink(1)
            val pos = event.pos.up()

            val entityItem = EntityItem(world)
            entityItem.setPosition(pos.x + .5, pos.y + .5, pos.z + .5)
            entityItem.item = ItemStack(enchant_book!!)

            world.spawnEntity(entityItem)

            val sound = SoundEvent(ResourceLocation("thaumcraft:dust"))
            world.playSound(null, pos, sound, SoundCategory.AMBIENT, 1f, 1.5f)

            AuraHelper.polluteAura(world, event.pos, 5f, true)
            event.useBlock = Event.Result.DENY
        }
    }

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

            world.spawnEntity(entityItem)
            (itemMainhand.item as EnchantmentUpgradePowder).transferEnchants(itemMainhand, itemStack)

            entityItem.item = itemStack

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
            itemMainhand.shrink(1)
            itemOffhand.shrink(1)

            event.useBlock = Event.Result.DENY
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

        if (itemMainhand.item === enchant_book) {
            event.useBlock = Event.Result.DENY
        }

    }

    companion object {

        @ObjectHolder("thaumcraft:salis_mundus")
        private val sal_mundi: Item? = null

        @ObjectHolder("minecraft:book")
        private val book: Item? = null

        @ObjectHolder("thaumic_arcana:creature_enchanter")
        private val enchant_book: Item? = null

        @ObjectHolder("minecraft:enchanting_table")
        private val enchanting_table: Block? = null

        @ObjectHolder("thaumcraft:plank_greatwood")
        private val greatwood_planks: Block? = null

        @ObjectHolder("minecraft:glass_bottle")
        private val glass_bottle: Item? = null

        @ObjectHolder("minecraft:stick")
        private val stick: Item? = null

        @ObjectHolder("$MODID:experiment_table")
        private val experiment_table: Block? = null
    }
}
