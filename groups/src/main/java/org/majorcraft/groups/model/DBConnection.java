package org.majorcraft.groups.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DBConnection {

    private String host;
    private int port;
    private String driver;
    private String username;
    private String password;

}
