package hu.frontrider.arcana.items

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.ThaumicArcana.TABARCANA
import hu.frontrider.arcana.util.Initialisable
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagEnd
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class ItemPlantBall : ItemBase(ResourceLocation(MODID, "plant_ball")), Initialisable {

    val treeVariants: List<ItemStack>
        get() {

            val items = ArrayList<ItemStack>()

            run {
                var meta = 0

                val sapling = Item.REGISTRY.getObject(ResourceLocation("minecraft:sapling"))
                val log = Item.REGISTRY.getObject(ResourceLocation("minecraft:log"))

                val log2 = Item.REGISTRY.getObject(ResourceLocation("minecraft:log2"))

                while (meta < 4) {
                    items.add(getBallFor(ItemStack(sapling!!, 2, meta), ItemStack(log!!, 5, meta)))
                    meta++
                }
                meta = 0

                while (meta < 2) {
                    items.add(getBallFor(ItemStack(sapling!!, 2, meta + 4), ItemStack(log2!!, 5, meta)))
                    meta++
                }
            }
            return items
        }

    val seedVariants: List<ItemStack>
        get() {
            val items = ArrayList<ItemStack>()
            items.add(getBallFor(ItemStack(Items.WHEAT_SEEDS, 2), ItemStack(Items.WHEAT, 6)))
            items.add(getBallFor(ItemStack(Items.BEETROOT_SEEDS, 2), ItemStack(Items.BEETROOT, 3)))

            return items
        }


    fun getBallFor(vararg stacks: ItemStack): ItemStack {
        val itemStack = ItemStack(this)
        val nbtTagCompound = NBTTagCompound()

        val tagList=NBTTagList()
        stacks.map { stack ->

            val regname = stack.item.registryName!!.toString()
            val metadata = stack.metadata
            val count = stack.count

            val tagCompound = NBTTagCompound()
            tagCompound.setString("item", regname)
            tagCompound.setInteger("count", count)
            tagCompound.setInteger("meta", metadata)

            tagCompound
        }.forEach{
            tagList.appendTag(it)
        }
        nbtTagCompound.setTag("products", tagList)
        itemStack.tagCompound = nbtTagCompound

        return itemStack
    }

    override fun addInformation(stack: ItemStack?, worldIn: World?, tooltip: MutableList<String>?, flagIn: ITooltipFlag?) {
        super.addInformation(stack, worldIn, tooltip, flagIn)
        if(stack!!.tagCompound == null)
            return

        val tagCompound = stack.tagCompound
        val products = tagCompound!!.getTag("products") as NBTTagList
        tooltip!!.add(I18n.format("item.thaumic_arcana.plant_ball.contains"))
        products.iterator().forEachRemaining { item ->
            val regname = (item as NBTTagCompound).getString("item")
            val count = item.getInteger("count")
            val meta = item.getInteger("meta")
            val resourcelocation = ResourceLocation(regname)
            val itemObj = Item.REGISTRY.getObject(resourcelocation)
            val lore = " - " + TextFormatting.GREEN + ItemStack(itemObj!!, 1, meta).displayName + " " + TextFormatting.RESET + count
            tooltip.add(lore)
        }
    }

    override fun onItemUse(player: EntityPlayer?, worldIn: World?, pos: BlockPos?, hand: EnumHand?, facing: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {

        val heldItem = player!!.getHeldItem(hand)
        val tagCompound = heldItem.tagCompound
        val products = tagCompound!!.getTag("products") as NBTTagList
        products.iterator().forEachRemaining { item ->
            val regName = (item as NBTTagCompound).getString("item")
            val count = item.getInteger("count")
            val meta = item.getInteger("meta")
            val resourcelocation = ResourceLocation(regName)
            val itemObj = Item.REGISTRY.getObject(resourcelocation)
            val itemStack = ItemStack(itemObj!!, count, meta)

            if (!player.addItemStackToInventory(itemStack)) {
                val entityItem = EntityItem(worldIn!!)
                entityItem.item = itemStack
                entityItem.posX = player.posX
                entityItem.posY = player.posY
                entityItem.posZ = player.posZ

                worldIn.spawnEntity(entityItem)
            }
        }
        heldItem.shrink(1)
        return EnumActionResult.SUCCESS
    }

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (tab !== TABARCANA)
            return
        if (treeItems != null)
            items.addAll(treeItems!!)
        if (seedItems != null)
            items.addAll(seedItems!!)
    }

    override fun init() {
        treeItems = treeVariants
        seedItems = seedVariants
    }

    companion object {

        var treeItems: List<ItemStack>? = null
            private set
        var seedItems: List<ItemStack>? = null
            private set


        fun getProductByIndex(itemStack: ItemStack, index: Int): ItemStack {
            val tagCompound = itemStack.tagCompound
            val products = tagCompound!!.getTag("products") as NBTTagList
            val item = products.get(index)

            if (item is NBTTagEnd)
                return ItemStack(Items.AIR)

            val regName = (item as NBTTagCompound).getString("item")
            val count = item.getInteger("count")
            val meta = item.getInteger("meta")
            val resourcelocation = ResourceLocation(regName)
            val itemObj = Item.REGISTRY.getObject(resourcelocation)
            return ItemStack(itemObj!!, count, meta)
        }
    }
}
