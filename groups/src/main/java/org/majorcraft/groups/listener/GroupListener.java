package org.majorcraft.groups.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.majorcraft.groups.GroupHandler;
import org.majorcraft.groups.events.GroupChangeEvent;
import org.majorcraft.groups.model.DataProvider;
import org.majorcraft.groups.MajorGroups;
import org.majorcraft.groups.model.User;
import org.majorcraft.groups.events.GroupCreateEvent;
import org.majorcraft.groups.events.UserChangeGroupEvent;

public class GroupListener implements Listener {

    private DataProvider dataProvider;

    private MajorGroups majorGroups;

    private GroupHandler permissionHandler = GroupHandler.getInstance();


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {

        Player player = evt.getPlayer();
        User user = dataProvider.findUser(player.getUniqueId());

        if (user == null) {
            dataProvider.addUser(new User(player.getUniqueId(), player.getName(), dataProvider.getDefaultGroup()));
        }

        permissionHandler.updateUser(user);

    }


    @EventHandler
    public void onGroupChange(GroupChangeEvent evt) {

        dataProvider.findUserByGroup(evt.getGroup()).forEach(permissionHandler::updateUser);

    }

    @EventHandler
    public void onGroupCreate(GroupCreateEvent evt) {
        System.out.println("Group " + evt.getGroup().getGroupId() + " is created by " + evt.getCreator().getName());
    }


    @EventHandler
    public void onUserGroupChange(UserChangeGroupEvent evt) {
        System.out.println("User " + evt.getUser() + " changed from Group " + evt.getOldGroup() + " to group " + evt.getNewGroup());
    }


}
