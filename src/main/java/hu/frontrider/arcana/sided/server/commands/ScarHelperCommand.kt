package hu.frontrider.arcana.sided.server.commands

import hu.frontrider.arcana.capabilities.scar.ScarProvider
import hu.frontrider.arcana.util.strings.formatTranslate
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.entity.EntityLivingBase
import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentString

class ScarHelperCommand: CommandBase() {
    override fun getName(): String {
        return "tascar"
    }

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        if (args.size < 2) {
            throw WrongUsageException("/tascar <player> <check|setdamage> <number>>", *arrayOfNulls(0))
        } else {
            val entitylivingbase = CommandBase.getEntity(server, sender, args[0], EntityLivingBase::class.java) as EntityLivingBase

            if(!entitylivingbase.hasCapability(ScarProvider.SCARRED_CAPABILITY,null))
            {
                throw WrongUsageException("Target can not be scarred!", *arrayOfNulls(0))
            }
            val capability = entitylivingbase.getCapability(ScarProvider.SCARRED_CAPABILITY, null)
            when(args[1]){
                "check"->{
                    sender.sendMessage(TextComponentString(capability.toString()))
                }
                "setDamage"->{

                }
            }
        }
    }

    override fun getUsage(sender: ICommandSender): String {
        return "/tascar <player> <check|setdamage> <number>>"
    }

    /**
     * Get a list of options for when the user presses the TAB key
     */
    override fun getTabCompletions(server: MinecraftServer, sender: ICommandSender, args: Array<String>, targetPos: BlockPos?): List<String> {
        return when {
            args.size == 1 -> CommandBase.getListOfStringsMatchingLastWord(args, *server.onlinePlayerNames)
            args.size == 2 -> return arrayListOf("check","setDamage")
            else -> emptyList()
        }
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    override fun isUsernameIndex(args: Array<String>, index: Int): Boolean {
        return index == 0
    }
}