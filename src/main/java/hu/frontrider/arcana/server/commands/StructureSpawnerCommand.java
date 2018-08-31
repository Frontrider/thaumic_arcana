package hu.frontrider.arcana.server.commands;

import hu.frontrider.arcana.worldgen.generators.TaintWineGenerator;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.command.server.CommandSetBlock;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

/**

 */
public class StructureSpawnerCommand extends CommandSetBlock {
    /**
     * Gets the name of the command
     */
    public String getName() {
        return "spawnArcanaStructure";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel() {
        return 2;
    }

    /**
     * Gets the usage string for the command.
     */
    public String getUsage(ICommandSender sender) {
        return "commands.thaumic_arcana.spawn_structure.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 4) {
            throw new WrongUsageException("commands.setblock.usage");
        }
        BlockPos blockpos = parseBlockPos(sender, args, 0, false);

        switch (args[3]) {
            case "taintWine": {
                new TaintWineGenerator().generate(sender.getEntityWorld(), sender.getEntityWorld().rand, blockpos);
                break;
            }
            default:
                throw new CommandException("invalid name");
        }
    }
}
