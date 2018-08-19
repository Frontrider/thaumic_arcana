package hu.frontrider.arcana.client.commands;

import hu.frontrider.arcana.client.rendering.EnchantRenderer;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.IClientCommand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ReloadOffsetsCommand implements IClientCommand {
    private final List<String> aliases;
    private final String name;
    private final EnchantRenderer enchantRenderer;

    public ReloadOffsetsCommand(EnchantRenderer enchantRenderer) {
        this.enchantRenderer = enchantRenderer;
        aliases = new ArrayList<>();
        name = "reloadarcana";
        aliases.add("name");

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/" + name;
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        enchantRenderer.reload();
        sender.sendMessage(new TextComponentTranslation("command.reload.feedback"));
    }

    @Override

    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand iCommand) {
        return 0;
    }

    @Override
    public boolean allowUsageWithoutPrefix(ICommandSender sender, String message) {
        return false;
    }
}
