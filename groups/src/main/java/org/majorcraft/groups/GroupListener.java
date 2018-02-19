package org.majorcraft.groups;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.majorcraft.groups.events.UserChangeGroupEvent;

public class GroupListener implements Listener {


    @EventHandler
    public void onUserGroupChange(UserChangeGroupEvent evt) {
        System.out.println("User " + evt.getUser() + " changed from Group " + evt.getOldGroup() + " to group " + evt.getNewGroup());

    }


}
