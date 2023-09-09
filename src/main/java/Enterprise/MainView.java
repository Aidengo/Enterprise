package Enterprise;

import handler.MainViewHandler;
import service.StaffRequest;
import service.StaffService;
import service.StaffServiceImpl;
import service.TableDTO;
import util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;
import java.util.Vector;

public class MainView extends JFrame {
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton( "添加员工");
    JButton updateBtn = new JButton("修改员工信息");
    JButton delBtn = new JButton("删除员工");
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn = new JButton("查询员工信息");

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn =new JButton("上一页");
    JButton nextBtn =new JButton("下一页");

    MainViewTable mainViewTable = new MainViewTable();
    private int pageNow = 1;  //当前是第几页
    private int pageSize = 10;    //一页显示多少天记录

    MainViewHandler mainViewHandler;
    public MainView(){
        super("企业财务管理系统");
        Container contentPane = getContentPane();
        mainViewHandler = new MainViewHandler(this);
        //放置北边的组件
        layoutNorth(contentPane);

        //设置中间的jTable
        layoutCenter(contentPane);

        //放置南边的组件
        layoutSouth(contentPane);

        //设置窗体图标
        URL resource = MainView.class.getClassLoader().getResource("R-C.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        //根据屏幕大小设置主界面大小
        setSize(DimensionUtil.getBounds());
        //设置窗体可见
        setVisible(true);
        //关闭退出程序
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //设置上一页下一页是否可见
    private void showPreNext(int totalCount){
        if (pageNow == 1){
            preBtn.setVisible(false);
        }else{
            preBtn.setVisible(true);
        }
        int pageCount = 0 ;   // 总共有多少页
        if (totalCount%pageSize==0){
            pageCount = totalCount/pageSize;
        }else {
            pageCount = totalCount/pageSize+1;
        }
        if (pageNow==pageCount){
            nextBtn.setVisible(false);
        }else {
            nextBtn.setVisible(true);
        }
    }

    private void layoutCenter(Container contentPane) {
        TableDTO dto = getTableDTO();
        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(dto.getData());

        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }

    private TableDTO getTableDTO() {
        StaffService staffService = new StaffServiceImpl();
        StaffRequest request = new StaffRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = staffService.retrieveStaffs(request);
        return tableDTO;
    }

    private void layoutSouth(Container contentPane) {
        preBtn.addActionListener(mainViewHandler);
        nextBtn.addActionListener(mainViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }

    private void layoutNorth(Container contentPane) {
        //增加事件监听
        addBtn.addActionListener(mainViewHandler);
        updateBtn.addActionListener(mainViewHandler);
        delBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new MainView();
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void reloadTable() {
        TableDTO dto = getTableDTO();
        MainViewTableModel.updataModel(dto.getData());
        mainViewTable.renderRule();//表格列的渲染方式
        showPreNext(dto.getTotalCount());
    }

    public int[] getSelectedStaffIds(){   //获取它的行
        int[] selectedRows = mainViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for (int i =0;i<selectedRows.length;i++){
            int rowIndex = selectedRows[i];
            Object idObj = mainViewTable.getValueAt(rowIndex,0);
            ids[i] =Integer.valueOf(idObj.toString());
        }
        return ids;
    }
}
