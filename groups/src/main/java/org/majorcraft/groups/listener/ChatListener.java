package org.majorcraft.groups.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.majorcraft.groups.model.DataProvider;
import org.majorcraft.groups.model.Group;
import org.majorcraft.groups.model.User;

public class ChatListener implements Listener {


    private DataProvider dataProvider;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent evt) {

        Player player = evt.getPlayer();
        User user = dataProvider.findUser(evt.getPlayer().getUniqueId());
        Group group = user.getGroup();


        player.setDisplayName(group.getPrefix() + player.getName()+ group.getSuffix());

    }


}
