package org.majorcraft.groups.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.permissions.Permission;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class Group {

    private String prefix;
    private String suffix;
    private final String groupId;
    private final Group inheritance;

    private Map<Permission, Boolean> permissions;


}
