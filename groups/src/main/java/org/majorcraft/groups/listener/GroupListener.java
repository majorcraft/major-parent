package org.majorcraft.groups.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.majorcraft.groups.DataProvider;
import org.majorcraft.groups.entity.User;
import org.majorcraft.groups.events.UserChangeGroupEvent;

public class GroupListener implements Listener {

    private DataProvider dataProvider;


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {

        User user = dataProvider.findUser(evt.getPlayer().getUniqueId());
        Player player = evt.getPlayer();

        if (user != null) {



        } else {

            dataProvider.addUser(new User(player.getUniqueId(), player.getName(), dataProvider.getDefaultGroup()));

        }


    }


    @EventHandler
    public void onUserGroupChange(UserChangeGroupEvent evt) {
        System.out.println("User " + evt.getUser() + " changed from Group " + evt.getOldGroup() + " to group " + evt.getNewGroup());

    }


}
