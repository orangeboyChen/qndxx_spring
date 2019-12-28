package com.orangeboy.dao;

import com.orangeboy.pojo.Admin;
import org.springframework.stereotype.Repository;

public interface AdminDao {
    Admin queryAdmin(Admin admin);
    void updateAdmin(Admin admin);
    void changeAdminPassword(Admin admin);

}
