package handler;

import Enterprise.AddStaffView;
import Enterprise.MainView;
import Enterprise.UpdateStaffView;
import service.StaffService;
import service.StaffServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewHandler implements ActionListener {
    private MainView mainView;
    public MainViewHandler(MainView mainView)
    {
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("添加员工".equals(text)) {
            new AddStaffView(mainView);
        } else if("修改员工信息".equals(text)) {
            int[] selectedStaffIds = mainView.getSelectedStaffIds();
            if (selectedStaffIds.length!=1){
                JOptionPane.showMessageDialog(mainView,"一次只能修改一行");
                return;
            }
            new UpdateStaffView(mainView,selectedStaffIds[0]);
        } else if("删除员工".equals(text)) {
            int[] selectedStaffIds = mainView.getSelectedStaffIds();//获取到要删除的行
            if (selectedStaffIds.length==0){
                JOptionPane.showMessageDialog(mainView,"请选择要删除的行");
                return;
            }
            int option = JOptionPane.showConfirmDialog(mainView, "你确认要删除嘛？",
                    "确认删除", JOptionPane.YES_NO_OPTION);
            if (option==JOptionPane.YES_OPTION){//确认
                //执行删除
                StaffService staffService = new StaffServiceImpl();
                boolean deleteResult = staffService.delete(selectedStaffIds);
                if (deleteResult){
                    //重新加载表格查到最新数据
                    mainView.reloadTable();
                }else {
                    JOptionPane.showMessageDialog(mainView,"删除失败！");
                }
            }
        } else if("查询员工信息".equals(text)) {
            mainView.setPageNow(1);
            mainView.reloadTable();
        } else if("上一页".equals(text)) {
            mainView.setPageNow(mainView.getPageNow()-1);
            mainView.reloadTable();
        } else if("下一页".equals(text)) {
            mainView.setPageNow(mainView.getPageNow()+1);
            mainView.reloadTable();
        }
    }



}
