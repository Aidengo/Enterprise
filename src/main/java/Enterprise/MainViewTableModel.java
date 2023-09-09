package Enterprise;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("工号：");
        columns.addElement("姓名：");
        columns.addElement("性别：");
        columns.addElement("年龄：");
        columns.addElement("部门：");
        columns.addElement("薪资：");
    }
    private MainViewTableModel(){
        super(null,columns);
    }

    private static MainViewTableModel mainViewTableModel = new MainViewTableModel();

    public static Vector<String> getColumns() {
        return columns;
    }

    public static MainViewTableModel assembleModel(Vector<Vector<Object>> data) {
        mainViewTableModel.setDataVector(data,columns);
        return mainViewTableModel;
    }

    public static void updataModel(Vector<Vector<Object>> data) {
        mainViewTableModel.setDataVector(data,columns);
    }


    //设置单元格不可编辑
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
