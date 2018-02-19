package org.majorcraft.groups;

import org.majorcraft.groups.entity.Group;
import org.majorcraft.groups.entity.User;

import java.util.List;
import java.util.UUID;

public interface DataProvider {

    List<User> getAllUsers();

    User findUser(UUID userId);

    boolean addUser(User user);

    List<Group> getAllGroups();

    Group findGroup(String groupId);

    boolean addGroup(Group g);

    Group getDefaultGroup();

}
