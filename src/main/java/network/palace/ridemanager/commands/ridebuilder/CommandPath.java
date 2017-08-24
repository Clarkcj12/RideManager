package network.palace.ridemanager.commands.ridebuilder;

import network.palace.core.command.CommandException;
import network.palace.core.command.CoreCommand;
import network.palace.core.player.CPlayer;

/**
 * @author Marc
 * @since 8/10/17
 */
public class CommandPath extends CoreCommand {

    public CommandPath() {
        super("path");
    }

    @Override
    protected void handleCommand(CPlayer player, String[] args) throws CommandException {
    }
}