package org.majorcraft.groups.model;

import java.util.List;
import java.util.UUID;

public class DBDataProvider implements DataProvider {


    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User findUser(UUID userId) {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public List<Group> getAllGroups() {
        return null;
    }

    @Override
    public Group findGroup(String groupId) {
        return null;
    }

    @Override
    public boolean addGroup(Group g) {
        return false;
    }

    @Override
    public Group getDefaultGroup() {
        return null;
    }

    @Override
    public List<User> findUserByGroup(Group group) {
        return null;
    }
}
