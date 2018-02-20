package org.majorcraft.groups.repo;

import org.majorcraft.groups.model.Group;
import org.majorcraft.groups.model.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {

    List<User> getAllUsers();

    User findUser(UUID userId);

    boolean addUser(User user);

    List<User> findUserByGroup(Group group);

}
