package org.majorcraft.groups.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.majorcraft.groups.GroupHandler;
import org.majorcraft.groups.Permissions;
import org.majorcraft.groups.events.UserChangeGroupEvent;
import org.majorcraft.groups.model.DataProvider;
import org.majorcraft.groups.model.Group;
import org.majorcraft.groups.model.User;

public class ClassifyCommand implements CommandExecutor {

    private GroupHandler groupHandler;

    private DataProvider dataProvider;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length == 2) {

            String userName = args[0];
            String groupName = args[1];

            Player player = Bukkit.getServer().getPlayer(userName);
            Group group = dataProvider.findGroup(groupName);

            if (player != null && group != null) {

                if(commandSender instanceof Player && !commandSender.hasPermission(Permissions.CLASSIFY_ALL)){

                    User user = dataProvider.findUser(((Player) commandSender).getUniqueId());

                    if(user != null){

                        if(!user.canClassify(group)){
                            commandSender.sendMessage("Cannot classify to a group above your own");
                            return false;
                        }

                    }

                }

                User user = dataProvider.findUser(player.getUniqueId());

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
