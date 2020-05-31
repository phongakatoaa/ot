package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Constants {
    public static final Color DEFAULT_BACKGROUND_COLOR = UIManager.getColor("Panel.background");
    public static final Font DEFAULT_FONT = new Font("Verdana", Font.PLAIN, 16);
    public static final int DEFAULT_HORIZONTAL_PADDING = 20;
    public static final int DEFAULT_VERTICAL_PADDING = 4;
    public static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
    public static final Color FOCUSED_BORDER_COLOR = Color.RED;
    public static final Color HOVERED_BORDER_COLOR = Color.ORANGE;
    public static final Stroke THICK_STROKE = new BasicStroke(3);
    public static final Stroke THIN_STROKE = new BasicStroke(1);
    public static final Stroke MEDIUM_STROKE = new BasicStroke(2);
    public static final Stroke DASHED_THIN_STROKE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    public static final Stroke DASHED_MEDIUM_STROKE = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    public static final Stroke DASHED_THICK_STROKE = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
}
