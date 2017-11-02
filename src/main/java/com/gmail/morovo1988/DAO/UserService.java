package com.gmail.morovo1988.DAO;

import com.gmail.morovo1988.Entity.CustomUser;

public interface UserService {
    CustomUser getUserByLogin(String login);
    boolean existsByLogin(String login);
    void addUser(CustomUser customUser);
    void updateUser(CustomUser customUser);


}
