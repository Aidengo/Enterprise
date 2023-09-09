package handler;

import Enterprise.AddStaffView;
import Enterprise.MainView;
import Enterprise.UpdateStaffView;
import service.StaffDO;
import service.StaffService;
import service.StaffServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateStaffViewHandler implements ActionListener {
    private UpdateStaffView updateStaffView;
    private MainView mainView;
    public UpdateStaffViewHandler(UpdateStaffView updateStaffView, MainView mainView) {
        this.updateStaffView = updateStaffView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource(); //获得这个按钮
        String text = jButton.getText();
        if("修改".equals(text)) {
            StaffService staffService = new StaffServiceImpl();
            StaffDO staffDO = updateStaffView.buildUpdatedStaffDO();
            boolean updateResult = staffService.update(staffDO);
            if (updateResult){
                //重新加载表格查到最新数据
                mainView.reloadTable();
                updateStaffView.dispose();//关闭当前窗口，不退出程序
            }else {
                JOptionPane.showMessageDialog(updateStaffView,"修改失败！");
            }
        }
    }



}
