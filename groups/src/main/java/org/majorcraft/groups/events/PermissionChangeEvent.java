package org.majorcraft.groups.events;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.majorcraft.groups.model.Group;

@RequiredArgsConstructor
public class PermissionChangeEvent extends Event {

    private final Group group;

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
