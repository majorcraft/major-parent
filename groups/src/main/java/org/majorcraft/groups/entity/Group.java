package org.majorcraft.groups.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.permissions.Permission;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Group {

    private String prefix;
    private String suffix;
    private final String groupId;
    private final Group inheritance;

    private List<Permission> permissions;


}
