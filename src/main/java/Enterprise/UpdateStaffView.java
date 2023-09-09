package Enterprise;

import handler.UpdateStaffViewHandler;
import service.StaffDO;
import service.StaffService;
import service.StaffServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class UpdateStaffView extends JDialog {
    JPanel jPanel = new JPanel();
    JLabel idLabel = new JLabel("工号：",JLabel.RIGHT);   //将该jlabel设置为水平居右,同下
    JTextField idTxt = new JTextField();
    JLabel nameLabel = new JLabel("姓名：",JLabel.RIGHT);
    JTextField nameTxt = new JTextField();
    JLabel sexLabel = new JLabel("性别：",JLabel.RIGHT);
    JTextField sexTxt = new JTextField();
    JLabel ageLabel = new JLabel("年龄：",JLabel.RIGHT);
    JTextField ageTxt = new JTextField();
    JLabel adeptLabel = new JLabel("部门：",JLabel.RIGHT);
    JTextField adeptTxt = new JTextField();
    JLabel salaryLabel = new JLabel("薪资：",JLabel.RIGHT);
    JTextField salaryTxt = new JTextField();
    JButton updateBtn = new JButton("修改");
    UpdateStaffViewHandler updateStaffViewHandler;

    public UpdateStaffView(MainView mainView, int selectedStaffId){
        super(mainView,"修改员工信息",true);//设置为模态的
        updateStaffViewHandler = new UpdateStaffViewHandler(this,mainView);
        //查询selectedStaffId对应的记录并回显
        StaffService staffService = new StaffServiceImpl();
        StaffDO selectedSta = staffService.getById(selectedStaffId);

        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        idTxt.setText(selectedSta.getId() + "");
        idTxt.setEnabled(false);//设置id不可编辑
        jPanel.add(idTxt);

        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        nameTxt.setText(selectedSta.getName());
        jPanel.add(nameTxt);

        sexLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(sexLabel);
        sexTxt.setPreferredSize(new Dimension(200,30));
        sexTxt.setText(selectedSta.getSex());
        jPanel.add(sexTxt);

        ageLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(ageLabel);
        ageTxt.setPreferredSize(new Dimension(200,30));
        ageTxt.setText(String.valueOf(selectedSta.getAge()));
        jPanel.add(ageTxt);

        adeptLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(adeptLabel);
        adeptTxt.setPreferredSize(new Dimension(200,30));
        adeptTxt.setText(selectedSta.getAdept());
        jPanel.add(adeptTxt);

        salaryLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(salaryLabel);
        salaryTxt.setPreferredSize(new Dimension(200,30));
        salaryTxt.setText(String.valueOf(selectedSta.getSalary()));
        jPanel.add(salaryTxt);

        updateBtn.addActionListener(updateStaffViewHandler);
        jPanel.add(updateBtn);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);

        //设置窗体图标
        URL resource = LoginView.class.getClassLoader().getResource("R-C.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        //设置窗体大小
        setSize(350,350);  //单位：px
        //设置窗体居中
        setLocationRelativeTo(null);
        //设置窗体可见
        setVisible(true);
        //只销毁当前窗体
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    //获取修改后的员工对象
    public StaffDO buildUpdatedStaffDO() {
        StaffDO staffDO = new StaffDO();
        staffDO.setId(Integer.valueOf(idTxt.getText()));
        staffDO.setName(nameTxt.getText());
        staffDO.setSex(sexTxt.getText());
        staffDO.setAge(Integer.valueOf(ageTxt.getText()));
        staffDO.setAdept(adeptTxt.getText());
        staffDO.setSalary(Double.valueOf(salaryTxt.getText()));
        return  staffDO;
    }
}
