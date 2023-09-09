package Enterprise;

import handler.AddStaffViewHandler;
import service.StaffDO;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AddStaffView extends JDialog {
    JPanel jPanel = new JPanel();
    JLabel idLabel = new JLabel("工号：",JLabel.RIGHT);    //将该jlabel设置为水平居右,同下
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
    JButton addBtn = new JButton("添加员工");
    AddStaffViewHandler addStaffViewHandler;
    public AddStaffView(MainView mainView){
        super(mainView,"添加员工",true);//设置为模态的

        addStaffViewHandler = new AddStaffViewHandler(this,mainView);
        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(idTxt);

        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameTxt);

        sexLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(sexLabel);
        sexTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(sexTxt);

        ageLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(ageLabel);
        ageTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(ageTxt);

        adeptLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(adeptLabel);
        adeptTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(adeptTxt);

        salaryLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(salaryLabel);
        salaryTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(salaryTxt);

        jPanel.add(addBtn);
        addBtn.addActionListener(addStaffViewHandler);

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

    public StaffDO buildStaffDO(){
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
