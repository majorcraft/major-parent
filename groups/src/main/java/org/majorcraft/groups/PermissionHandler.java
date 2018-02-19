package org.majorcraft.groups;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PermissionHandler {

    private static PermissionHandler instance = new PermissionHandler();

    public static PermissionHandler getInstance() {
        return instance;
    }



    private Map<UUID, PermissionAttachment> attachmentMap;

    private PermissionHandler() {
        attachmentMap = new HashMap<>();
    }


    public void setPermission(Player player, Permission perm, boolean enabled) {

        PermissionAttachment atta = attachmentMap.get(player.getUniqueId());

        if (atta == null) {
            atta = player.addAttachment(MajorGroups.getInstance());
            attachmentMap.put(player.getUniqueId(), atta);
        }

        atta.setPermission(perm, enabled);

    }


}
