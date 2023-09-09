package util;

import javax.swing.*;
import java.awt.*;

public class DimensionUtil {
    public static Dimension getBounds(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize;
    }
}
