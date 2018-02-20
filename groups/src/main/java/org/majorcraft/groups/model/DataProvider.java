package org.majorcraft.groups.model;

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

    List<User> findUserByGroup(Group group);

}
