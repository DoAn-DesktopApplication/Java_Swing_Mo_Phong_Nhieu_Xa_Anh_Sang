
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Vector;
import javax.swing.*;

public class drawWindow extends JPanel {

    private final Dimension size;

    //Hỗ trợ tia sáng dịch chuyển
    private int length = 1; //Độ dài đường đi tia sáng
    public boolean quaCachTu = false;
    public boolean complete = false;
    private Vector cucDai = new Vector();

    public final mainWindow main;
    public final graph gr;
    public Ruler rl; //Thước dọc
    public Ruler rl2; //Thước ngang
    private final int achorY; //Độ chênh lệch của khung làm việc với của sổ menubar
    private final MovingAdapter ma = new MovingAdapter();
    private final image cachTu, mC, nguon, point, point2, point3;

    public drawWindow(mainWindow main) {
        this.main = main;
        initComponents();
        size = new Dimension(1000, 500);
        setSize(size);

        cachTu = new image("cachTu.png", 30, 55);
        mC = new image("manChan.png", 50, size.height - 100);
        nguon = new image("Untitled.png", 100, 60);
        point = new image("Point.png", 32, 32);
        point2 = new image("Point2.png", 40, 22);
        point3 = new image("Point2.png", 40, 22);
        //Khởi tạo tọa độ hiển thị của ảnh
        achorY = -22;
        mC.setLocation(new Point((size.width - 160) - point2.getWidth(null) - mC.getWidth(null), size.height / 2 - mC.getHeight(null) / 2 + achorY));
        nguon.setLocation(new Point(30, size.height / 2 - nguon.getHeight(null) / 2 + achorY));
        cachTu.setLocation(new Point(size.width / 2 - 55 - cachTu.getWidth(null) / 2 + 33, size.height / 2 - cachTu.getHeight(null) / 2 + achorY));
        point.setLocation(new Point(cachTu.x + cachTu.getWidth(null) / 3 - point.getWidth(null) / 2 - 2, mC.getHeight(null) + 13 + achorY));
        point2.setLocation(new Point((size.width - 160) - point2.getWidth(null), mC.y + mC.getHeight(null) / 2 - point2.getHeight(null) / 2));
        point3.setLocation(new Point((size.width - 160) - point2.getWidth(null), mC.y + mC.getHeight(null) - 32));
        
        int temp = mC.x - (cachTu.x + cachTu.getWidth(null) / 2 + 15);
        gr = new graph(this, temp, size.height - 160, 0.3, 1);
        gr.linspace(-0.3, 0.3);
        gr.setPosO(new Point(mC.x - 10, size.height / 2 + achorY));
        
        addMouseMotionListener(ma);
        addMouseListener(ma);
        addMouseWheelListener(ma);

        Rectangle rect = new Rectangle(size.width - 160, mC.y + 20, 50, mC.getHeight(null) - 40);
        Rectangle rect2 = new Rectangle(30 + nguon.getWidth(null) - 4, mC.getHeight(null) + 55 + achorY, mC.x - 43 - nguon.getWidth(null), 250 + achorY - (mC.y + mC.getHeight(null) + 40 + achorY));
        rl = new Ruler(Ruler.DOC, rect, 30, new int[]{10, 5, 1}, new boolean[]{true, true, false}, "cm", main.getColor()[5]);
        rl2 = new Ruler(Ruler.NGANG, rect2, 100, new int[]{10, 5, 1}, new boolean[]{true, false, false}, "cm", main.getColor()[5]);

        //Cài đặt phương và giới hạn di chuyển của hình
        cachTu.setPhuongDiChuyen(image.NGANG);
        cachTu.setGioiHanDiChuyen(toCoordinatesH(0.9).x, toCoordinatesH(0.1).x);
        point.setGioiHanDiChuyen(toCoordinatesH(0.9).x, toCoordinatesH(0.1).x);
        point2.setPhuongDiChuyen(image.DOC);
        point2.setGioiHanDiChuyen(mC.y + 10, mC.y + mC.getHeight(null) - 32);
        point3.setPhuongDiChuyen(image.DOC);
        point3.setGioiHanDiChuyen(mC.y + 10, mC.y + mC.getHeight(null) - 32);
        
        updateThemes();
        diChuyenCachTu(0.5);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        veNen(g2d);

        //Cập nhật tọa độ cách tử và con trỏ
        cachTu.setLocation(new Point((rl2.area.x + rl2.area.width) - (int) (((((rl2.area.x + rl2.area.width) - (cachTu.x + 10)) * 0.5 / (rl2.area.width / 2)) * rl2.area.width / 2) / 0.5) - 10, cachTu.y));
        point.setLocation(new Point(cachTu.x, point.y));

        veTiaSang(g2d);
        veDoThi(g);
        veHinh(g2d);
        repaint();
    }

    public void update() {
        lamMoiThongSo();
        if (gr.mode == graph.DO_THI_CACH_TU) timCucDai();
    }
    
    public void updateThemes(){
        this.setBackground(main.getColor()[4]);
        rl.updateColor(main.getColor()[5]);
        rl2.updateColor(main.getColor()[5]);
    }

    //<editor-fold desc="Các hàm vẽ">
    private void veNen(Graphics2D g2d) {
        g2d.setColor(main.getColor()[0]);
        g2d.fillRect(15, size.height / 2 - mC.getHeight(null) / 2 - 15 + achorY, size.width - 130, mC.getHeight(null) + 60);
        g2d.setColor(main.getColor()[1]);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(15, size.height / 2 - mC.getHeight(null) / 2 - 15 + achorY, size.width - 130, mC.getHeight(null) + 60);
        g2d.drawImage(nguon.img, nguon.x, nguon.y, this);
        g2d.drawImage(mC.img, mC.x, mC.y, this);
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString("Kết quả đo: " + rl.getSpace(point2.y + point2.getHeight(null) / 2, point3.y + point3.getHeight(null) / 2) + " " + rl.getTitle(), size.width - 245, size.height / 2 + mC.getHeight(null) / 2 + achorY + 62);
    }

    private void veHinh(Graphics2D g2d) {
        g2d.drawImage(cachTu.img, cachTu.x, cachTu.y, this);
        g2d.drawImage(point.img, point.x, point.y, this);
        g2d.drawImage(point2.img, point2.x, point2.y, this);
        g2d.drawImage(point3.img, point3.x, point3.y, this);
        if (ma.hinh == point2 || ma.hinh == point3) {
            g2d.setColor(main.getColor()[2]);
            g2d.fillOval(ma.hinh.x + ma.hinh.getWidth(null) / 2 - 5, ma.hinh.y + ma.hinh.getHeight(null) / 2 - 7, 11, 12);
        }
        rl.drawRuler(g2d);
        rl2.drawRuler(g2d);
    }

    private void veTiaSang(Graphics2D g2d) {
        g2d.setColor(main.control.getColor());
        BasicStroke dashed
                = new BasicStroke(5.0f,
                        BasicStroke.CAP_SQUARE,
                        BasicStroke.JOIN_ROUND,
                        1.0f, new float[]{2f}, 2.0f);
        g2d.setStroke(dashed);
        if ((30 + nguon.getWidth(null) - 4 + this.length) > cachTu.x) {
            this.length = cachTu.x - (30 + nguon.getWidth(null) - 4);
            quaCachTu = true;
            complete = false;
        } else if (quaCachTu == true && (30 + nguon.getWidth(null) - 4 + this.length) < cachTu.x) {
            this.length = cachTu.x - (30 + nguon.getWidth(null) - 4);
        }

        g2d.drawLine(30 + nguon.getWidth(null) - 4, (size.height / 2 - 1) + achorY, 30 + nguon.getWidth(null) - 4 + this.length, (size.height / 2 - 1) + achorY);
    }

    private void veDoThi(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(main.getColor()[3]);
        gr.drawArrow(g2d, gr.getPosO().x - 6, gr.getPosO().y + gr.height / 2 + 20, gr.getPosO().x - 6, gr.getPosO().y - gr.height / 2 - 20, 3.0);
        gr.drawArrow(g2d, gr.getPosO().x - 6, gr.getPosO().y, gr.getPosO().x - gr.width + 3, gr.getPosO().y, 3.0);
        if (quaCachTu == true) {
            gr.figure(g);
            g2d.setClip(0, 0, size.width, size.height);
            if (gr.mode == 1 && gr.getN() > 1) {
                veCacCucDaiChoCT(g2d, mC.getBounds());
            } else {
                veCacCucDaiChoKhe(g2d, mC.getBounds());
            }
        }
    }

    private void veCacCucDaiChoCT(Graphics2D g2d, Rectangle clip) {
        Point p;
        Color colorMax = main.control.getColor(), color = null;
        double khoangVan = (double) ((gr.getLambda() / gr.getKhoangCach2Khe()) * gr.getL());
        int maxR = (int) ((gr.getPosO().y - gr.getX(khoangVan)) * 0.85), R = 0, maxY = 0, max = 20;
        double x = 0;

        timCucDai();
        maxY = ((Point) cucDai.elementAt(cucDai.size() / 2)).x;
        if (maxR > max) maxR = max;
        for (int i = 0; i < cucDai.size(); i++) {
            p = (Point) cucDai.elementAt(i);
            R = (int) (maxY * maxR / p.x);
            color = reduceIntensityLight(colorMax, maxY * 255 / p.x); //Cường độ ánh sáng giảm dần khi xa vân trung tâm
            g2d.setColor(color);
            if (p.y - R <= clip.y) {
                break;
            }
            g2d.fillArc(clip.x + clip.width / 2 - R / 2, p.y - R / 2, R, R, 0, 360);
        }
    }

    private void veCacCucDaiChoKhe(Graphics2D g2d, Rectangle clip) {
        g2d.setClip(clip.x, clip.y + 20, clip.width, clip.height - 40);
        Color color = main.control.getColor();
        int height = 75;
        double k1 = ((gr.height / 2) / (0.0075 / 2)) / 2;
        double khoangVan = (double) (gr.getLambda() * gr.getL() / gr.getDoRongKhe());
        spreadColor(g2d, color, clip.x + 14, gr.getX(0.0), mC.getWidth(null) - 27, (int) ((height * gr.getL()) / 0.5), 0);//vẽ nửa dưới cho 1/2 đồ thị phía dưới
        spreadColor(g2d, color, clip.x + 14, gr.getX(0.0) - (int) ((height * gr.getL()) / 0.5) - (int) khoangVan, mC.getWidth(null) - 27, (int) ((height * gr.getL()) / 0.5) + 5, 1);//vẽ nửa trên cho 1/2 đồ thị phía trên
        height = 75 / 2;
        double x = 3 * khoangVan / 2;
        color = reduceIntensityLight(color, 0.75); //Cường độ sáng yếu dần khi ra xa vân trung tâm
        while (gr.getX(x) > (250 - gr.phamVi)) {
            spreadColor(g2d, color, clip.x + 14, gr.getX(x), mC.getWidth(null) - 27, (int) (((height * gr.getL()) / 0.5) * (gr.getLambda() / (760 / Math.pow(10, 9)))), 0);//vẽ nửa dưới cho 1/2 đồ thị phía trên
            spreadColor(g2d, color, clip.x + 14, gr.getX(-x), mC.getWidth(null) - 27, (int) (((height * gr.getL()) / 0.5) * (gr.getLambda() / (760 / Math.pow(10, 9)))), 0);//vẽ nửa dưới cho 1/2 đồ thị phía dưới
            spreadColor(g2d, color, clip.x + 14, gr.getX(x) - (int) (((height * gr.getL()) / 0.5) * (gr.getLambda() / (760 / Math.pow(10, 9)))), mC.getWidth(null) - 27, (int) (((height * gr.getL()) / 0.5) * (gr.getLambda() / (760 / Math.pow(10, 9)))), 1);//vẽ nửa trên cho 1/2 đồ thị phía trên
            spreadColor(g2d, color, clip.x + 14, gr.getX(-x) - (int) (((height * gr.getL()) / 0.5) * (gr.getLambda() / (760 / Math.pow(10, 9)))), mC.getWidth(null) - 27, (int) (((height * gr.getL()) / 0.5) * (gr.getLambda() / (760 / Math.pow(10, 9)))), 1);//vẽ nửa trên cho 1/2 đồ thị phía dưới
            x += khoangVan;
            color = reduceIntensityLight(color, 0.75); //Cường độ sáng yếu dần khi ra xa vân trung tâm

        }
    }

    private Color reduceIntensityLight(Color oldColor, int newAlpha) {
        //Thay đổi cường độ ánh sáng theo cường độ sáng mới

        if (newAlpha > 255) {
            return oldColor;
        }
        return new Color(oldColor.getRed(), oldColor.getGreen(), oldColor.getBlue(), newAlpha);
    }

    private Color reduceIntensityLight(Color oldColor, double percent) {
        //Thay đổi cường độ ánh sáng theo percent

        int alpha = (int) (percent * oldColor.getAlpha());
        if (alpha > 255) {
            return oldColor;
        }
        return new Color(oldColor.getRed(), oldColor.getGreen(), oldColor.getBlue(), alpha);
    }

    private void spreadColor(Graphics2D g2d, Color color, int x, int y, int width, int height, int chieu) {
        /*
            0 là chiều từ trên xuống
            1 là chiều ngược lại
         */

        Color newColor;

        if (chieu == 0) {
            int dAlpha = color.getAlpha();
            for (int i = 0; i < height; i++) {
                int alpha = color.getAlpha() - i * dAlpha / height;
                newColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
                g2d.setColor(newColor);
                g2d.drawLine(x, y + i, x + width, y + i);
            }
        } else {
            int dAlpha = color.getAlpha();
            for (int i = height; i >= 0; i--) {
                int alpha = color.getAlpha() - (height - i) * dAlpha / height;
                newColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
                g2d.setColor(newColor);
                g2d.drawLine(x, y + i, x + width, y + i);
            }
        }
    }

    private void timCucDai() {
        if (cucDai == null) {
            cucDai = new Vector();
        } else {
            cucDai.removeAllElements();
        }

        cucDai.addElement(new Point(gr.getY(gr.getPosO().y), gr.getPosO().y));
        double khoangVan = 0;
        double toaDoX = 0;
        if (gr.mode == graph.DO_THI_CACH_TU) {
            khoangVan = (double) ((gr.getLambda() / gr.getKhoangCach2Khe()) * gr.getL());
            toaDoX = khoangVan;
        } else {
            khoangVan = (double) (gr.getLambda() * gr.getL() / gr.getDoRongKhe());
            toaDoX = 3 * khoangVan / 2;
        }

        int x1 = 0;
        int x2 = 0;
        while (gr.getX(toaDoX) > (250 - gr.phamVi - 25)) {
            x1 = gr.getX(toaDoX);
            x2 = gr.getX(-toaDoX);
            cucDai.insertElementAt(new Point(gr.getY(x1), x1), 0);
            cucDai.addElement(new Point(gr.getY(x2), x2));
            toaDoX += khoangVan;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Hàm khởi tạo">
    private void initComponents() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
    }
    //</editor-fold>

    // <editor-fold desc="Hàm hỗ trợ tia sáng di chuyển">
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void lamMoiThongSo() {
        setLength(1);
        quaCachTu = false;
    }
    // </editor-fold>

    //<editor-fold desc="Hàm thao tác với các đối tượng trên màn hình theo chuột">
    public void diChuyenCachTu() {
        //Hàm thay đổi giá trị của graph theo tọa độ cách tử

        gr.setWidth(mC.x - (cachTu.x + cachTu.getWidth(null) / 2 + 15));
        gr.setL(((rl2.area.x + rl2.area.width) - (point.x + 17)) * 0.5 / (rl2.area.width / 2));
    }

    public void diChuyenCachTu(double value) {
        //Hàm thay đổi giá trị của graph theo giá trị value

        Point p = toCoordinatesH(value);
        point.setLocation(new Point(p.x, point.y));
        cachTu.setLocation(new Point(p.x, cachTu.y));
        gr.setWidth(mC.x - (cachTu.x + cachTu.getWidth(null) / 2 + 15));
        gr.setL(value);
    }

    private Point toCoordinatesH(double value) {
        //Chuyển đổi giá trị độ dài thước sang tọa độ trên màn hình

        Point p = point.getLocation();
        p.x = (int) ((rl2.area.x + rl2.area.width) - value * (rl2.area.width / 2) / 0.5 - 17);
        return p;

    }

    class MovingAdapter extends MouseAdapter {

        private int x;
        private int y;
        private boolean isDrag;
        public image hinh;

        @Override
        public void mousePressed(MouseEvent e) {
            //Cái này vừa bấm là nhận liền
            if (cachTu.isCollision(e.getPoint())) {
                hinh = cachTu;
            } else if (point2.isCollision(e.getPoint())) {
                hinh = point2;
            } else if (point3.isCollision(e.getPoint())) {
                hinh = point3;
            } else {
                hinh = null;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //Cái này bấm rồi thả mới nhận
            Point toaDoChuot = e.getPoint();
            if (nguon.isCollision(toaDoChuot)) {
                gr.setLength(1);
                gr.setLambda(main.control.getLambda());
                gr.start();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point toaDoChuot = e.getPoint();
            if (hinh == cachTu) {
                cachTu.moving(toaDoChuot);
                point.moving(toaDoChuot);
                diChuyenCachTu();
                repaint();
            } else if (hinh == point2) {
                point2.moving(toaDoChuot);
                repaint();
            } else if (hinh == point3) {
                point3.moving(toaDoChuot);
                repaint();
            } else {
                return;
            }
            
            isDrag = true;
            main.control.updateComponent();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (isDrag && hinh != null) {
                hinh = null;
            }
            isDrag = false;
        }
        
        public void mouseWheelMoved(MouseWheelEvent mwe) {
            int d = mwe.getWheelRotation();
            if (hinh == point2) {
                if (d >= 0) {
                    if (point2.y < point2.getGioiHanDiChuyen()[1]) {
                        point2.y += 2 * d;
                    } else {
                        point2.y = point2.getGioiHanDiChuyen()[1];
                    }
                } else {
                    if (point2.y > point2.getGioiHanDiChuyen()[0]) {
                        point2.y += 2 * d;
                    } else {
                        point2.y = point2.getGioiHanDiChuyen()[0];
                    }
                }
                repaint();
            } else if (hinh == point3) {
                if (d >= 0) {
                    if (point3.y < point3.getGioiHanDiChuyen()[1]) {
                        point3.y += 2 * d;
                    } else {
                        point3.y = point3.getGioiHanDiChuyen()[1];
                    }
                } else {
                    if (point3.y > point3.getGioiHanDiChuyen()[0]) {
                        point3.y += 2 * d;
                    } else {
                        point3.y = point3.getGioiHanDiChuyen()[0];
                    }
                }
                repaint();
            }
        }
    }
//</editor-fold>
}
