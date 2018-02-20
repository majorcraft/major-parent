package org.majorcraft.groups;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.majorcraft.groups.events.GroupCreateEvent;
import org.majorcraft.groups.model.DataProvider;
import org.majorcraft.groups.model.Group;
import org.majorcraft.groups.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Handles the Groups
 */
public class GroupHandler {

    private static GroupHandler instance = new GroupHandler();

    private static DataProvider dataProvider;

    public static GroupHandler getInstance() {
        return instance;
    }


    /**
     * Map with PermissionAttachments of online Players
     */
    private Map<UUID, PermissionAttachment> attachmentMap;

    private GroupHandler() {
        attachmentMap = new HashMap<>();
    }


    public void removeGroup(Group group) {

        if (group.equals(dataProvider.getDefaultGroup())) {

            System.out.println("Cannot delete default Group");

        } else {

            dataProvider.findUserByGroup(group).forEach(user -> {


                Group g = group.getInheritance();

                if (g == null) {
                    g = dataProvider.getDefaultGroup();
                }

                user.setGroup(g);


            });

            dataProvider.removeGroup(group);
        }

    }

    public void addGroup(Group group){


    }

    public List<Group> getGroups(){
        return dataProvider.getAllGroups();
    }

    public void editGroup(Group group){

    }

    /**
     * Updates a Player properties given by his Group
     *
     * @param user User
     */
    public void updateUser(User user) {

        Player player = Bukkit.getServer().getPlayer(user.getUserId());

        if (player != null) {

            Group group = user.getGroup();

            //Set the permissions for the player
            group.getAllPermissions().keySet().forEach(perm -> {
                setPermission(player, perm, group.getPermissions().get(perm));
            });

            //Set the displayname with prefix and suffix
            player.setDisplayName(group.getPrefix() + player.getDisplayName() + group.getSuffix());
        }

    }


    /**
     * Sets Permissions for a Specific Player and saves attachment in the map for further use
     *
     * @param player  Player
     * @param perm    permission
     * @param enabled permission enabled
     */
    public void setPermission(Player player, Permission perm, boolean enabled) {

        PermissionAttachment atta = attachmentMap.get(player.getUniqueId());

        if (atta == null) {
            initPermissionAttachment(player);
        }

        atta.setPermission(perm, enabled);

    }

    public void initPermissionAttachment(Player player) {

        removePermissionAttachment(player);

        PermissionAttachment atta = player.addAttachment(MajorGroups.getInstance());
        attachmentMap.put(player.getUniqueId(), atta);

    }


    /**
     * Removes a player PermissionAttachment from the Server and the Map
     *
     * @param player Player
     */
    public void removePermissionAttachment(Player player) {

        PermissionAttachment atta = attachmentMap.get(player.getUniqueId());

        if (atta != null) {
            player.removeAttachment(atta);
            attachmentMap.remove(player.getUniqueId());
        }

    }


}
