package hu.frontrider.arcana.sided.client.commands

import hu.frontrider.arcana.sided.client.rendering.EnchantRenderer
import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraftforge.client.IClientCommand
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.ArrayList

class ReloadOffsetsCommand(private val enchantRenderer: EnchantRenderer) : IClientCommand {
    private val aliases: MutableList<String>
    private val name: String

    init {
        aliases = ArrayList()
        name = "reloadarcana"
        aliases.add("name")

    }

    override fun getName(): String {
        return name
    }

    override fun getUsage(sender: ICommandSender): String {
        return "/$name"
    }

    override fun getAliases(): List<String> {
        return aliases
    }

    @SideOnly(Side.CLIENT)
    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        enchantRenderer.reload()
        sender.sendMessage(TextComponentTranslation("command.thaumic_arcana.reload.feedback"))
    }

    override fun checkPermission(server: MinecraftServer, sender: ICommandSender): Boolean {
        return true
    }

    override fun getTabCompletions(server: MinecraftServer, sender: ICommandSender, args: Array<String>, targetPos: BlockPos?): List<String>? {
        return null
    }

    override fun isUsernameIndex(args: Array<String>, index: Int): Boolean {
        return false
    }

    override fun compareTo(iCommand: ICommand): Int {
        return 0
    }

    override fun allowUsageWithoutPrefix(sender: ICommandSender, message: String): Boolean {
        return false
    }
}
