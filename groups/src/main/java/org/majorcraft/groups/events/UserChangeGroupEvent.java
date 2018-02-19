package org.majorcraft.groups.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.majorcraft.groups.entity.Group;
import org.majorcraft.groups.entity.User;

@Getter
@Setter
@RequiredArgsConstructor
public class UserChangeGroupEvent extends Event {

    private final User user;
    private final Group newGroup;
    private final Group oldGroup;


    public HandlerList getHandlers() {
        return null;
    }


}
