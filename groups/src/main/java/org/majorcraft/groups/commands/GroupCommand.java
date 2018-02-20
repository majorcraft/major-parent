package org.majorcraft.groups.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.majorcraft.groups.events.GroupCreateEvent;
import org.majorcraft.groups.events.GroupDeleteEvent;
import org.majorcraft.groups.model.DataProvider;
import org.majorcraft.groups.model.Group;

public class GroupCommand implements CommandExecutor {


    private DataProvider dataProvider;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length > 0) {

            //Create
            if (args[0].equalsIgnoreCase("add")) {
                if (args.length == 2) {
                    String groupId = args[1];
                    Bukkit.getPluginManager().callEvent(new GroupCreateEvent(commandSender, new Group(groupId)));
                    return true;
                }
            }
            //Delete
            else if (args[0].equalsIgnoreCase("remove")) {
                if (args.length == 2) {
                    String groupId = args[1];
                    Group group = dataProvider.findGroup(groupId);

                    if (group != null) {
                        Bukkit.getPluginManager().callEvent(new GroupDeleteEvent(commandSender, group));
                        return true;
                    }
                }
            }
            //Update
            else if (args[0].equalsIgnoreCase("edit")) {

                if (args.length == 4) {

                    Group group = dataProvider.findGroup(args[1]);

                    if (group != null) {

                        String key = args[2];
                        String value = args[3];

                        if (key.equalsIgnoreCase("prefix")) {
                            group.setPrefix(value);
                            return true;

                        } else if (key.equalsIgnoreCase("suffix")) {
                            group.setSuffix(value);
                            return true;

                        } else if (key.equalsIgnoreCase("inheritance")) {

                            Group inheritance = dataProvider.findGroup(value);

                            if (inheritance != null) {
                                group.setInheritance(inheritance);
                                return true;
                            }
                        }
                    }
                }

            }
            //Read
            else if (args[0].equalsIgnoreCase("list")) {
                dataProvider.getAllGroups().forEach(group -> {
                    commandSender.sendMessage(group.getGroupId());
                });
                return true;
            }

        }

        return false;


    }
}
