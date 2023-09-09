package handler;

import Enterprise.AddStaffView;
import Enterprise.MainView;
import service.StaffDO;
import service.StaffService;
import service.StaffServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStaffViewHandler implements ActionListener {
    private AddStaffView addStaffView;
    private MainView mainView;
    public AddStaffViewHandler(AddStaffView addStaffView, MainView mainView) {
        this.addStaffView = addStaffView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();     //获得这个按钮
        String text = jButton.getText();
        if("添加员工".equals(text)) {
            StaffService staffService = new StaffServiceImpl();
            StaffDO staffDO = addStaffView.buildStaffDO();
            boolean addResult = staffService.add(staffDO);
            if (addResult){
                //重新加载表格查到最新数据
                mainView.reloadTable();
                addStaffView.dispose();//关闭当前窗口，不退出程序
            }else {
                JOptionPane.showMessageDialog(addStaffView,"添加失败！");
            }
        }
    }



}
