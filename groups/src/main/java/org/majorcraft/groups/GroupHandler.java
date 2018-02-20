package org.majorcraft.groups;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.majorcraft.groups.model.Group;
import org.majorcraft.groups.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Handles the Groups
 */
public class GroupHandler {

    private static GroupHandler instance = new GroupHandler();

    public static GroupHandler getInstance() {
        return instance;
    }


    /**
     * Map with PermissionAttachements of online Players
     */
    private Map<UUID, PermissionAttachment> attachmentMap;

    private GroupHandler() {
        attachmentMap = new HashMap<>();
    }

    /**
     * Updates a Player properties given by his Group
     * @param user User
     */
    public void updateUser(User user){

        Player player = Bukkit.getServer().getPlayer(user.getUserId());

        if(player != null){

            Group group = user.getGroup();

            //Set the permissions for the player
            group.getPermissions().keySet().forEach(perm -> {
                setPermission(player, perm, group.getPermissions().get(perm));
            });

            //Set the displayname with prefix and suffix
            player.setDisplayName(group.getPrefix()+player.getDisplayName()+group.getSuffix());
        }

    }


    /**
     * Sets Permissions for a Specific Player and saves attachement in the map for further use
     * @param player Player
     * @param perm permission
     * @param enabled permission enabled
     */
    public void setPermission(Player player, Permission perm, boolean enabled) {

        PermissionAttachment atta = attachmentMap.get(player.getUniqueId());

        if (atta == null) {
            atta = player.addAttachment(MajorGroups.getInstance());
            attachmentMap.put(player.getUniqueId(), atta);
        }

        atta.setPermission(perm, enabled);

    }


}
