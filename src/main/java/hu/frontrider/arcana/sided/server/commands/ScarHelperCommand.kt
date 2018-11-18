package hu.frontrider.arcana.sided.server.commands

import hu.frontrider.arcana.core.capabilities.scar.ScarProvider
import net.minecraft.command.CommandBase
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.server.MinecraftServer

class ScarHelperCommand: CommandBase() {
    override fun getName(): String {
        return "tascar"
    }

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        val player = sender.commandSenderEntity as? EntityPlayer ?: throw CommandException("Target must be a player")
        val capability = player.getCapability(ScarProvider.SCARRED_CAPABILITY, null)
        println(args.toList())
        println(capability)
    }

    override fun getUsage(sender: ICommandSender): String {
        return "tascar <player> <check|setdamage [number]>"
    }
}