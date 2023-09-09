package Enterprise;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable {

    public MainViewTable(){
        //设置表头
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,30));
        tableHeader.setForeground(Color.RED);//设置字体颜色

        //设置表格体
        setFont(new Font(null,Font.PLAIN,15));
        setForeground(Color.black);
        setGridColor(Color.BLACK);//设置表格线的颜色
        setRowHeight(30);

        //设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

        public void renderRule(){
            //设置表格列的渲染方式
            Vector<String> columns = MainViewTableModel.getColumns();
            MainViewCellRender render = new MainViewCellRender();
            for(int i=0;i<columns.size();i++)
            {
                TableColumn column = getColumn(columns.get(i));
                column.setCellRenderer(render);
                if(i==0)
                {
                    column.setPreferredWidth(120);//设置该列宽度
                    column.setResizable(false);//设置该列宽度不可改变
                }
            }
        }
}
