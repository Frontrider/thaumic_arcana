
package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.ThaumicArcana.MODID
import net.minecraft.world.storage.loot.LootTableList
import net.minecraftforge.event.LootTableLoadEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraft.world.storage.loot.LootEntryItem
import net.minecraft.world.storage.loot.LootEntry
import net.minecraftforge.common.MinecraftForge


class LootHandler {

    fun init(){
        MinecraftForge.EVENT_BUS.register(magical_powder)
    }
    var magical_powder: LootEntry = LootEntryItem(
            ItemRegistry.enchanting_powder_magical, 5, 1, arrayOfNulls(0), arrayOfNulls(0), "$MODID:magical_enchant_powder")

    @SubscribeEvent
    fun onLootTableLoad(event: LootTableLoadEvent) {

        val name = event.name
        if (name == LootTableList.CHESTS_ABANDONED_MINESHAFT ||
                name == LootTableList.CHESTS_VILLAGE_BLACKSMITH ||
                name == LootTableList.CHESTS_DESERT_PYRAMID ||
                name == LootTableList.ENTITIES_ENDER_DRAGON ||
                name == LootTableList.CHESTS_JUNGLE_TEMPLE) {
            event.table.getPool("main").addEntry(magical_powder)
        }
    }

}