package hu.frontrider.arcana.server.commands

import hu.frontrider.arcana.content.worldgen.generators.TaintWineGenerator
import net.minecraft.command.CommandBase
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.command.server.CommandSetBlock
import net.minecraft.server.MinecraftServer

/**
 *
 */
class StructureSpawnerCommand : CommandSetBlock() {
    /**
     * Gets the name of the command
     */
    override fun getName(): String {
        return "spawnArcanaStructure"
    }

    /**
     * Return the required permission level for this command.
     */
    override fun getRequiredPermissionLevel(): Int {
        return 2
    }

    /**
     * Gets the usage string for the command.
     */
    override fun getUsage(sender: ICommandSender?): String {
        return "commands.thaumic_arcana.spawn_structure.usage"
    }

    @Throws(CommandException::class)
    override fun execute(server: MinecraftServer?, sender: ICommandSender, args: Array<String>) {
        if (args.size < 4) {
            throw WrongUsageException("commands.thaumic_arcana.spawn_structure.usage")
        }
        val blockpos = CommandBase.parseBlockPos(sender, args, 0, false)

        when (args[3]) {
            "taintWine" -> {
                TaintWineGenerator().generate(sender.entityWorld, sender.entityWorld.rand, blockpos)
            }
            else -> throw CommandException("commands.thaumic_arcana.spawn_structure.invalid_name")
        }
    }
}
