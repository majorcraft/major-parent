package org.majorcraft.groups.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@RequiredArgsConstructor
public class User {

    private final UUID userId;
    private final String latestName;
    private final Group group;


}
