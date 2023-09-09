package handler;

import Enterprise.LoginView;
import Enterprise.MainView;
import service.AdminDO;
import service.AdminService;
import service.AdminServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginHandler implements ActionListener {
    private LoginView loginView;
    public  LoginHandler(LoginView loginView)
    {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("重置".equals(text))
        {
            loginView.getUserTxt().setText("");
            loginView.getPwdField().setText("");
        }
        else if("登录".equals(text))
        {
            login();
        }
    }

    private void login() {
        String user = loginView.getUserTxt().getText();
        char[] chars = loginView.getPwdField().getPassword();

        if (user == null || "".equals(user.trim())||
                chars == null) {
            JOptionPane.showMessageDialog(loginView,"用户名或密码不能为空！");
            return;
        }

        String pwd = new String(chars);
        //查询db
        AdminService adminService = new AdminServiceImpl();
        AdminDO adminDO = new AdminDO();
        adminDO.setUserName(user);
        adminDO.setPwd(pwd);
        boolean flag = adminService.validateAdmin(adminDO);
        if(flag)
        {
            //如果登陆成功则跳转到主页面
            new MainView();
            //销毁登陆页面
            loginView.dispose();
        }
        else {
             JOptionPane.showMessageDialog(loginView,"用户名或密码错误！");
        }
    }

}
