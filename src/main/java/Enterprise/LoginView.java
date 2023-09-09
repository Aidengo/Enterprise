package Enterprise;

import handler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LoginView extends JFrame {
    JLabel nameLabel = new JLabel("企业财务管理系统",JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel userNameLabel = new JLabel("用户名:");
    JTextField userTxt = new JTextField();
    JLabel pwdLabel = new JLabel("密码:");
    JPasswordField pwdField = new JPasswordField();
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");
    LoginHandler loginHandler;
    public LoginView(){
        super("企业财务管理系统");

        loginHandler = new LoginHandler(this);
        Container contentPane = getContentPane();

        nameLabel.setFont(new Font("华文行楷", Font.PLAIN, 40));
        nameLabel.setPreferredSize(new Dimension(0,80));

        Font centerFont = new Font("楷体", Font.PLAIN, 20);
        userNameLabel.setFont(centerFont);
        userTxt.setPreferredSize(new Dimension(200,30));
        pwdLabel.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(200,30));
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);

        //把组件加入面板
        centerPanel.add(userNameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        loginBtn.addActionListener(loginHandler);
        centerPanel.add(loginBtn);
        resetBtn.addActionListener(loginHandler);
        centerPanel.add(resetBtn);

        //布局
        //布局userNameLabel
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel),Spring.width(userTxt)),
                Spring.constant(20));
        int offsetX = childWidth.getValue()/2;
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetX,
                SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(springLayout.NORTH,userNameLabel,20,springLayout.NORTH,centerPanel);

        //userTxt
        springLayout.putConstraint(springLayout.WEST,userTxt,20,springLayout.EAST,userNameLabel);
        springLayout.putConstraint(springLayout.NORTH,userTxt,0,springLayout.NORTH,userNameLabel);

        //pwdLabel
        springLayout.putConstraint(springLayout.EAST,pwdLabel,0,springLayout.EAST,userNameLabel);
        springLayout.putConstraint(springLayout.NORTH,pwdLabel,20,springLayout.SOUTH,userNameLabel);
        //pwdField
        springLayout.putConstraint(springLayout.WEST,pwdField,20,springLayout.EAST,pwdLabel);
        springLayout.putConstraint(springLayout.NORTH,pwdField,0,springLayout.NORTH,pwdLabel);
        //loginBtn
        springLayout.putConstraint(springLayout.WEST,loginBtn,50,springLayout.WEST,pwdLabel);
        springLayout.putConstraint(springLayout.NORTH,loginBtn,20,springLayout.SOUTH,pwdLabel);
        //resetBtn
        springLayout.putConstraint(springLayout.WEST,resetBtn,50,springLayout.EAST,loginBtn);
        springLayout.putConstraint(springLayout.NORTH,resetBtn,0,springLayout.NORTH,loginBtn);

        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);


        //设置窗体图标
        URL resource = LoginView.class.getClassLoader().getResource("R-C.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        //设置窗体大小
        setSize(600,400);  //单位：px
        //设置窗体居中
        setLocationRelativeTo(null);
        //设置窗体可见
        setVisible(true);
        //关闭退出程序
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new LoginView();
    }

    public JTextField getUserTxt() {
        return userTxt;
    }


    public JPasswordField getPwdField() {
        return pwdField;
    }


}
