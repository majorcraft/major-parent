package org.majorcraft.groups.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.majorcraft.groups.model.Group;

@Getter
@RequiredArgsConstructor
public class GroupCreateEvent extends Event {

    private final CommandSender creator;
    private final Group group;

    public HandlerList getHandlers() {
        return null;
    }
}
