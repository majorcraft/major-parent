package org.majorcraft.groups;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.majorcraft.groups.model.Group;
import org.majorcraft.groups.model.User;
import org.majorcraft.groups.repo.GroupRepository;
import org.majorcraft.groups.repo.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Handles the Groups
 */
public class GroupHandler {

    private static GroupHandler instance = new GroupHandler();

    private static GroupRepository groupRepository;

    private static UserRepository userRepository;

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


    /**
     * Removes a Group and updates all Players
     * @param group
     */
    public void removeGroup(Group group) {

        if (group.equals(groupRepository.getDefaultGroup())) {

            System.out.println("Cannot delete default Group");

        } else {

            userRepository.findUserByGroup(group).forEach(user -> {
                Group g = group.getInheritance();

                if (g == null) {
                    g = groupRepository.getDefaultGroup();
                }

                user.setGroup(g);
            });

            groupRepository.removeGroup(group);
        }

    }


    /**
     * Adds a group
     * @param group
     */
    public void addGroup(Group group) {
        groupRepository.addGroup(group);

    }


    /**
     * Finds a User from the userId
     * @param userId userId
     * @return User
     */
    public User findUser(UUID userId) {
        return userRepository.findUser(userId);
    }


    /**
     * Finds a Group from the groupId
     * @param groupId groupId
     * @return
     */
    public Group findGroup(String groupId) {
        return groupRepository.findGroup(groupId);
    }


    /**
     * Adds user to the Repository from a Player with a specific group
     * @param player the player
     * @param group the group
     * @return the User
     */
    public User addUser(Player player, Group group) {
        User user = new User(player.getUniqueId(), player.getName(), group);

        userRepository.addUser(user);
        return user;
    }

    /**
     * Adds a User to the Repository with the default group
     * @param player
     * @return
     */
    public User addUser(Player player) {
        return addUser(player, groupRepository.getDefaultGroup());
    }


    /**
     * Finds all Users from a specific Group
     * @param group
     * @return
     */
    public List<User> findUserByGroup(Group group) {
        return userRepository.findUserByGroup(group);

    }

    /**
     * Returns a List of all Groups
     * @return List of all Groups
     */
    public List<Group> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    /**
     * Updates the Group into the Repository and all of the users of the group
     * @param group Group
     */
    public void updateGroup(Group group) {
        findUserByGroup(group).forEach(this::updateUser);
        groupRepository.updateGroup(group);
    }
}
