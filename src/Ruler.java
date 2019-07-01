
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Ruler extends Canvas {

    public static final int DOC = 0;
    public static final int NGANG = 1;
    private int chieu = DOC;
    public final Rectangle area; //Vùng giới hạn thước được vẽ
    private int startDraw; //Điểm neo để bắt đầu vẽ thước
    private int size; //Phạm vi đo của thước
    private int[] width; //Giá trị cần vẽ vạch
    private boolean[] isPaint;
    private String title;
    private int space; //Khoảng cách giũa hai vạch nhỏ nhất
    private Color color;

    public Ruler(int chieu, Rectangle area, int size, int[] width, boolean[] isPaint, String title, Color color) {
        this.chieu = chieu;
        this.area = area;
        this.size = size;
        this.width = width;
        this.isPaint = isPaint;
        this.title = title;
        this.updateColor(color);
        space = getCoordinate(width[width.length - 1]) - getCoordinate(0);
    }
    
    public void updateColor(Color cl){
        this.color = cl;
    }

    public void drawRuler(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int start = 0; //Điểm bắt đầu vẽ
        int limitDraw = 0; //Giới hạn để vẽ thước
        int limitValue = 0; //Giới hạn giá trị của thước

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(2));

        if (chieu == DOC) {
            limitDraw = area.y + area.height;
            start = area.y + area.height / 2;
            g2d.drawLine(area.x + 4, area.y + 1, area.x + 4, area.y + area.height);
            for (int i = width.length - 1; i >= 0; i--) {
                drawLineRuler(g, chieu, start, limitDraw, (width.length - i) * 3, width[i], 0, size);
                drawLineRuler(g, chieu, start, limitDraw, (width.length - i) * 3, -width[i], 0, size);
            }
            drawValueRuler(g, chieu, start, limitDraw);
        } else if (chieu == NGANG) {
            limitDraw = area.x;
            start = area.x + area.width;
            g2d.drawLine(area.x + 1, area.y, area.x + area.width, area.y);
            for (int i = width.length - 1; i >= 0; i--) {
                drawLineRuler(g, chieu, start, limitDraw, (width.length - i) * 3, -width[i], 0, size);
            }
            drawValueRuler(g, chieu, start, limitDraw);
        }
    }

    private void drawLineRuler(Graphics g, int chieu, int start, int limitDraw, int width, int length, int startValue, int limitValue) {
        /*
            start - điểm để bắt đầu vẽ các vạch
            limitDraw - điểm giới hạn để vẽ các vạch
            width - độ rộng của vạch
            length - khoảng cách giữa các vạch
            startValue - giá trị của thước khi bắt đầu vẽ vạch
            limitValue - giá trị giới hạn của thước để vẽ các vạch
         */

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(1));

        if (chieu == DOC) {
            while (start + getCoordinate(startValue) <= limitDraw && Math.abs(startValue) <= limitValue) {
                g2d.drawLine(area.x + 4, start + getCoordinate(startValue), area.x + 4 + width, start + getCoordinate(startValue));
                startValue += length;
            }
        } else if (chieu == NGANG) {
            while (start - getCoordinate(startValue) >= limitDraw && Math.abs(startValue) <= limitValue) {
                g2d.drawLine(start + getCoordinate(startValue), area.y, start + getCoordinate(startValue), area.y + width);
                startValue += length;
            }
        }
    }

    private void drawValueRuler(Graphics g, int chieu, int start, int limitDraw) {
        Graphics2D g2d = (Graphics2D) g.create();
        ArrayList list = new ArrayList();
        Font font = new Font("Arial", Font.PLAIN, 11);
        int value = 0;
        int spaceWidth = 0, spaceHeight = 0;
        if (chieu == DOC) {
            spaceWidth = 5;
            spaceHeight = font.getSize() / 3;
            g2d.setColor(color);
            g2d.setFont(font);
            
            for (int i = 0; i < width.length; i++) {
                value = 0;
                while (isPaint[i] == true && start + getCoordinate(value) <= limitDraw && Math.abs(value) <= size) {
                    if (list.contains(value) == true) {
                        value += width[i];
                        continue;
                    }
                    int y = getCoordinate(value);
                    int dy = getCoordinate(-value);
                    g2d.drawString("" + value, area.x + 4 + (width.length - i) * 3 + spaceWidth, start + y + spaceHeight);
                    if (y != dy) {
                        g2d.drawString("" + value, area.x + 4 + (width.length - i) * 3 + spaceWidth, start + dy + spaceHeight);
                    }
                    list.add(value);
                    value += width[i];
                }
            }
        } else if (chieu == NGANG){
            g2d.setColor(color);
            g2d.setFont(font);
            
            for (int i = 0; i < width.length; i++) {
                value = 0;
                while (isPaint[i] == true && start - getCoordinate(value) >= limitDraw && Math.abs(value) <= size) {
                    if (list.contains(value) == true) {
                        value += width[i];
                        continue;
                    }
                    int x = getCoordinate(value);
                    g2d.drawString("" + value, start - getCoordinate(value) - (String.valueOf(value).length()*font.getSize())/4, area.y + width.length*7 - 2);
                    list.add(value);
                    value += width[i];
                }
            }
        }
    }

    public int getCoordinate(int value) {
        //Lấy tọa độ thước trên màn hình dựa vào giá trị hiện tại

        if (chieu == DOC) {
            return value * (area.height / 2) / size;
        }
        return value * area.width / size;
    }

    public double getSpace(int y1, int y2) {
        int denta = Math.abs(y1 - y2);
        double space = graph.round(denta * width[width.length - 1] / ((double) this.space + 0.16), 2);
        if ((int) space > 2 * size) {
            space = 2 * size;
        }
        return space;
    }

    public String getTitle() {
        return title;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setWidth(int[] width) {
        this.width = width;
        space = getCoordinate(width[width.length - 1]) - getCoordinate(0);
    }

    public void setIsPaint(boolean[] isPaint) {
        this.isPaint = isPaint;
    }

}
