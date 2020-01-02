package com.orangeboy.service;

import com.orangeboy.pojo.Register;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface RegisterService {
    Register getRegisterFromSession(HttpSession session);
    void addRegister(Register register);
    void deleteRegister(Register register);
    void updateRegister(Register register);
    Register queryRegister(String email);
    List<Register> getAllRegisters();
}
