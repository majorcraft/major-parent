package org.majorcraft.groups.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.majorcraft.groups.events.GroupCreateEvent;
import org.majorcraft.groups.model.Group;

public class GroupCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (s.equalsIgnoreCase("group")) {

            //Create
            if (args[0].equalsIgnoreCase("add")) {
                if (args.length == 2) {
                    String groupId = args[1];
                    Bukkit.getPluginManager().callEvent(new GroupCreateEvent(commandSender, new Group(groupId, null)));
                }
            }
            //Delete
//            else if (args[0].equalsIgnoreCase("remove")) {
//                if(args.length == 2){
//                    String groupId = args[1];
//                    Group group = group
//@TODO COMPLETE
//                    Bukkit.getPluginManager().callEvent(new GroupDeleteEvent(commandSender, group));
//                }
//            }
            //Update
            else if (args[0].equalsIgnoreCase("edit")) {

            }
            //Read
            else if (args[0].equalsIgnoreCase("list")) {

            }

            return false;
        } else {

            return false;
        }


    }
}
