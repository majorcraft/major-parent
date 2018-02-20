package org.majorcraft.groups.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class User {

    private final UUID userId;
    private final String latestName;

    @Setter
    private Group group;


    public boolean isOnline(){
        return Bukkit.getServer().getPlayer(userId).isOnline();
    }


}
