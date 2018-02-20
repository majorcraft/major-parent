package org.majorcraft.groups.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.majorcraft.groups.GroupHandler;
import org.majorcraft.groups.Permissions;
import org.majorcraft.groups.events.UserChangeGroupEvent;
import org.majorcraft.groups.model.Group;
import org.majorcraft.groups.model.User;


/**
 * Classifies a user to a group regarding the classify.all permission
 */
public class ClassifyCommand implements CommandExecutor {

    private GroupHandler groupHandler;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length == 2) {

            String userName = args[0];
            String groupName = args[1];

            Player player = Bukkit.getServer().getPlayer(userName);
            Group group = groupHandler.findGroup(groupName);

            if (player != null && group != null) {

                //If the Sender is a Player and don't has the classify.all Permission
                if (commandSender instanceof Player && !commandSender.hasPermission(Permissions.CLASSIFY_ALL)) {

                    User user = groupHandler.findUser(((Player) commandSender).getUniqueId());

                    if (user != null) {

                        //check if the given group is in inheritance chain
                        if (!user.canClassify(group)) {
                            commandSender.sendMessage("Cannot classify to a group above your own");
                            return false;
                        }

                    }

                }

                User user = groupHandler.findUser(player.getUniqueId());

                if (user == null) {
                    user = groupHandler.addUser(player);
                }

                Bukkit.getPluginManager().callEvent(new UserChangeGroupEvent(user, group, user.getGroup()));
                return true;

            }

        }


        return false;
    }
}
