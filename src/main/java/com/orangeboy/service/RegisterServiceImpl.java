package com.orangeboy.service;

import com.orangeboy.controller.RegisterController;
import com.orangeboy.dao.RegistersDao;
import com.orangeboy.pojo.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegistersDao registersDao;

    @Override
    public Register getRegisterFromSession(HttpSession session) {
        return (Register) session.getAttribute(RegisterController.REGISTER);
    }

    @Override
    public void addRegister(Register register) {
        registersDao.addRegister(register);
    }

    @Override
    public void deleteRegister(Register register) {
        registersDao.deleteRegister(register);
    }

    @Override
    public void updateRegister(Register register) {
        registersDao.updateRegister(register);
    }

    @Override
    public Register queryRegister(String email) {
        return registersDao.queryRegister(email);
    }

    @Override
    public List<Register> getAllRegisters() {
        return registersDao.getAllRegisters();
    }
}
