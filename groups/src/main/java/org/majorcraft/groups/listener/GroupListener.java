package org.majorcraft.groups.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.majorcraft.groups.DataProvider;
import org.majorcraft.groups.MajorGroups;
import org.majorcraft.groups.entity.Group;
import org.majorcraft.groups.entity.User;
import org.majorcraft.groups.events.GroupCreateEvent;
import org.majorcraft.groups.events.UserChangeGroupEvent;

public class GroupListener implements Listener {

    private DataProvider dataProvider;


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {

        User user = dataProvider.findUser(evt.getPlayer().getUniqueId());
        Player player = evt.getPlayer();

        Group group;

        if (user != null) {
            group = user.getGroup();

        } else {
            group = dataProvider.getDefaultGroup();
            dataProvider.addUser(new User(player.getUniqueId(), player.getName(), group));
        }

        initPermissions(player, group);


    }

    private void initPermissions(Player player, Group group) {
        PermissionAttachment at = player.addAttachment(MajorGroups.getInstance());

        group.getPermissions().forEach(perm -> {

            at.setPermission(perm, true);

        });

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
