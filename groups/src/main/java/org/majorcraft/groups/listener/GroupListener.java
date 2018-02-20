package org.majorcraft.groups.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.majorcraft.groups.GroupHandler;
import org.majorcraft.groups.events.GroupChangeEvent;
import org.majorcraft.groups.events.GroupCreateEvent;
import org.majorcraft.groups.events.GroupDeleteEvent;
import org.majorcraft.groups.events.UserChangeGroupEvent;
import org.majorcraft.groups.repo.GroupRepository;
import org.majorcraft.groups.model.User;

/**
 * The Class listens to Events needed for the Plugin
 */
public class GroupListener implements Listener {

    private GroupRepository dataProvider;

    private GroupHandler groupHandler = GroupHandler.getInstance();


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {

        Player player = evt.getPlayer();
        User user = groupHandler.findUser(player.getUniqueId());

        if (user == null) {
            user = groupHandler.addUser(evt.getPlayer());
        }

        groupHandler.updateUser(user);

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent evt){

        groupHandler.removePermissionAttachment(evt.getPlayer());

    }


    @EventHandler
    public void onGroupChange(GroupChangeEvent evt) {
        groupHandler.updateGroup(evt.getGroup());
        groupHandler.findUserByGroup(evt.getGroup()).forEach(groupHandler::updateUser);
    }

    @EventHandler
    public void onGroupCreate(GroupCreateEvent evt) {
        System.out.println("Group " + evt.getGroup().getGroupId() + " is created by " + evt.getCreator().getName());
    }


    @EventHandler
    public void onUserGroupDelete(GroupDeleteEvent evt) {

        groupHandler.removeGroup(evt.getGroup());
    }

    @EventHandler
    public void onUserGroupChange(UserChangeGroupEvent evt) {
        System.out.println("User " + evt.getUser() + " changed from Group " + evt.getOldGroup() + " to group " + evt.getNewGroup());

        evt.getUser().setGroup(evt.getNewGroup());

        groupHandler.updateUser(evt.getUser());
    }

}
