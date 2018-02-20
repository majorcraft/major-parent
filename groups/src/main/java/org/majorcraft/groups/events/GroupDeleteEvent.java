package org.majorcraft.groups.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.majorcraft.groups.model.Group;

@RequiredArgsConstructor
@Getter
public class GroupDeleteEvent extends Event {


    private final CommandSender sender;
    private final Group group;

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
