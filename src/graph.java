
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;
import java.awt.Point;

public class graph implements Runnable {

    public static final int DO_THI_KHE = 0;
    public static final int DO_THI_CACH_TU = 1;
    private int length = 1;
    public int phamVi = 1;
    public int width, height;
    private Point posO;
    int mode; //Loai do thi muon ve
    private double[] thongSoCachTu = new double[]{0.0000023, 0.00001};
    private double[] thongSoKhe = new double[]{0.00001};
    private double doRongKhe, khoangCach2Khe;
    private double lambda = 0.00000076;
    private double L = 0.5;
    private int N = 2;
    private double tiLeKhe;
    private double limit;
    private final drawWindow draw;
    private Vector x;
    private Thread td;

    public graph(drawWindow d, int width, int height, double limit, int mode) {
        this.draw = d;
        this.width = width;
        this.height = height;
        this.mode = mode;
        this.linspace(-limit, limit);
        this.capNhatThongSo(mode);
        this.tiLeKhe = (int)((doRongKhe/khoangCach2Khe)*100);
    }

    public void capNhatThongSo(int mode) {
        if (mode == DO_THI_KHE) {
            doRongKhe = thongSoKhe[0];
        } else {
            doRongKhe = thongSoCachTu[0];
            khoangCach2Khe = thongSoCachTu[1];
        }
    }
    
    public void update(int index){
        capNhatThongSo(index);
        setMode(index);
        setLength(1);
    }

    //<editor-fold desc="Các hàm get và set">
    
    public int getX(double value) {
        double k = (this.height / 2) / (limit / 2);
        return posO.y - (int) (value * k);
    } //Lấy toạ độ X trên màn hình theo giá trị thực tế
    
    public double getX(int value){
        int posX = value - getPosO().y;
        int indexY = (x.size()/2) + posX;
        if (indexY < 0) indexY = 0;
        else if (indexY >= x.size()) indexY = x.size() - 1;
        
        return (double)x.elementAt(indexY);
    } //Lấy giá trị thức tế của X theo toạ độ X trên màn hình
    
    public int getY(int value){
        double valueY = 0, maxY = 0;
        double valueX = getX(value);
        double maxX = (double)x.elementAt(x.size()/2);
        if (mode == DO_THI_CACH_TU){
            valueY = solveFx(valueX, 1);
            maxY = solveFx(maxX, 0);
        } else {
            valueY = solveFx(valueX, 0);
            maxY = solveFx(maxX, 0);
        }
        
        double k = (width - 30)/maxY;
        return posO.x - (int) (valueY * k) - 6;
    } //Lấy toạ độ Y trên màn hình theo giá trị X trên màn hình

    public double getLambda() {
        return this.lambda;
    }   

    public Point getPosO() {
        return posO;
    }

    public double getDoRongKhe() {
        return doRongKhe;
    }

    public double getKhoangCach2Khe() {
        return khoangCach2Khe;
    }

    public double getL() {
        return L;
    }

    public int getN() {
        return N;
    }

    public void setTiLeKhe(double tiLeKhe) {
        this.tiLeKhe = tiLeKhe;
        this.doRongKhe = (tiLeKhe/100)*this.khoangCach2Khe;
    }

    public void setDoRongKhe(double doRongKhe) {
        this.doRongKhe = doRongKhe;
    }

    public void setKhoangCach2Khe(double khoangCach2Khe) {
        this.khoangCach2Khe = khoangCach2Khe;
        this.doRongKhe = ((double)tiLeKhe/100)*this.khoangCach2Khe;
    }

    public void setL(double L) {
        this.L = L;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPosO(Point p) {
        this.posO = p;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setN(int N) {
        this.N = N;
    }

    public void setPhamVi(int phamVi) {
        this.phamVi = phamVi;
    }
    
    
    //</editor-fold>

    //<editor-fold desc="Các hàm vẽ đồ thị">
    public void linspace(double a, double b) {
        //Tạo mảng các giá trị x để vẽ đồ thị

        limit = Math.abs(a) + Math.abs(b);
        Vector v = new Vector();
        double length = Math.abs(a - b);
        double dx = length / height;
        
        try {
            while (round(a, 5) <= round(b, 5)) {
                v.addElement(new Double(a));
                a = a + dx;
            }
        } catch (Exception e) {
        }
        this.x = v;
    }

    public void figure(Graphics g) {
        //Vẽ đồ thị

        //mode: loai do thi muon ve
        try {
            Graphics2D g2d = (Graphics2D) g.create();
            double maxY; //Giá trị Y tối đa của đồ thị trên màn hình
            double k; //Do phong dai cua do thi

            g2d.setColor(draw.main.control.getColor());
            if (mode == 0) {
                g2d.setClip(posO.x - width + 18, posO.y - height/2, width - 18, height);
                maxY = solveFx((double)x.elementAt(x.size() / 2 - 1), 0);
                k = (width - 30) / maxY;
                int size = 0;
                if (length < x.size() - 1) {
                    size = length;
                } else {
                    size = x.size() - 1;
                }
                for (int i = 0; i < size; i++) {
                    phamVi = i + 1;
                    double x1 = (double) x.elementAt(x.size() / 2 - i + 1);
                    double x2 = (double) x.elementAt(x.size() / 2 - i);
                    double x3 = (double) x.elementAt(x.size() / 2 + i - 1);
                    double x4 = (double) x.elementAt(x.size() / 2 + i);

                    double y1 = solveFx(x1, 0) * k + 1;
                    double y2 = solveFx(x2, 0) * k + 1;
                    double y3 = solveFx(x3, 0) * k + 1;
                    double y4 = solveFx(x4, 0) * k + 1;
                    g2d.drawLine(posO.x - (int) y1 - 6, posO.y - i, posO.x - (int) y2 - 6, posO.y - i - 1);
                    g2d.drawLine(posO.x - (int) y3 - 6, posO.y + i, posO.x - (int) y4 - 6, posO.y + i + 1);
                }
            } else {
                g2d.setClip(posO.x - width + 18, posO.y - height/2, width - 18, height);
                maxY = solveFx((double)x.elementAt(x.size() / 2 - 1), 1);
                k = (width - 30) / maxY;
                int size = 0;
                if (length < x.size() / 2) {
                    size = length;
                } else {
                    size = x.size() / 2;
                }
                for (int i = 1; i < size; i++) {
                    phamVi = i + 1;
                    double x1 = (double) x.elementAt(x.size() / 2 - i + 1);
                    double x2 = (double) x.elementAt(x.size() / 2 - i);
                    double x3 = (double) x.elementAt(x.size() / 2 + i - 1);
                    double x4 = (double) x.elementAt(x.size() / 2 + i);

                    double y1 = solveFx(x1, 0) * k + 1;
                    double y2 = solveFx(x2, 0) * k + 1;
                    double y3 = solveFx(x3, 0) * k + 1;
                    double y4 = solveFx(x4, 0) * k + 1;
                    g2d.drawLine(posO.x - (int) y1 - 6, posO.y - i, posO.x - (int) y2 - 6, posO.y - i - 1);
                    g2d.drawLine(posO.x - (int) y3 - 6, posO.y + i, posO.x - (int) y4 - 6, posO.y + i + 1);

                    double z1 = solveFx(x1, 1) * k + 1;
                    double z2 = solveFx(x2, 1) * k + 1;
                    double z3 = solveFx(x3, 1) * k + 1;
                    double z4 = solveFx(x4, 1) * k + 1;
                    g2d.drawLine(posO.x - (int) z1 - 6, posO.y - i, posO.x - (int) z2 - 6, posO.y - i - 1);
                    g2d.drawLine(posO.x - (int) z3 - 6, posO.y + i, posO.x - (int) z4 - 6, posO.y + i + 1);
                }
            }
        } catch (Exception e) {
        }
        draw.complete = true;

    }

    public void drawArrowHead(Graphics2D g, int X, int Y, double Angle, double LW) {
        double A1, A2;
        Point[] Arrow = new Point[4];
        double Beta = 0.322;
        double LineLen = 4.74;
        double CentLen = 3.0;
        float OldWidth;
        BasicStroke stroke = (BasicStroke) g.getStroke();
        Angle = Math.PI + Angle;
        Arrow[0] = new Point(X, Y);
        A1 = Angle - Beta;
        A2 = Angle + Beta;
        Arrow[1] = new Point(X + (int) (LineLen * LW * Math.cos(A1)), Y - (int) (LineLen * LW * Math.sin(A1)));
        Arrow[2] = new Point(X + (int) (CentLen * LW * Math.cos(Angle)), Y - (int) (CentLen * LW * Math.sin(Angle)));
        Arrow[3] = new Point(X + (int) (LineLen * LW * Math.cos(A2)), Y - (int) (LineLen * LW * Math.sin(A2)));

        OldWidth = stroke.getLineWidth();
        g.setStroke(new BasicStroke(1));
        g.fillPolygon(new int[]{Arrow[0].x, Arrow[1].x, Arrow[2].x, Arrow[3].x},
                new int[]{Arrow[0].y, Arrow[1].y, Arrow[2].y, Arrow[3].y}, 4);
        g.setStroke(new BasicStroke(OldWidth));
    }

    public void drawArrow(Graphics g, int X1, int Y1, int X2, int Y2, double LW) {
        Graphics2D g2d = (Graphics2D) g.create();
        double Angle;
        Angle = Math.atan2(Y1 - Y2, X2 - X1);
        g.drawLine(X1, Y1, X2 - (int) (2 * LW * Math.cos(Angle)), Y2 + (int) (2 * LW * Math.sin(Angle)));

        drawArrowHead(g2d, X2, Y2, Angle, LW);
    }

    private double solveFx(double x, int loai) {
        //Tính y theo x

        double y = 0;
        if (mode == DO_THI_KHE) {
            double sinX = x / L;
            double alphaX = Math.PI * doRongKhe / lambda * sinX;
            y = pow(Math.sin(alphaX), 2) / pow(alphaX, 2);
        } else {
            if (loai == 0) {
                double sinX = x / L;
                double alphaX = Math.PI * doRongKhe / lambda * sinX;
                double gammaX = 2 * Math.PI * khoangCach2Khe / lambda * sinX;
                y = pow(Math.sin(alphaX), 2) / pow(alphaX, 2) * pow(Math.sin(N * gammaX / 2), 2) / pow(Math.sin(gammaX / 2), 2);
            } else {
                double sinX = x / L;
                double deltaX = 2 * Math.PI * doRongKhe / lambda * sinX;
                y = pow(N, 2) * pow(Math.sin(deltaX / 2), 2) / pow(deltaX / 2, 2);
            }
        }
        return y;
    }
    //</editor-fold>

    //<editor-fold desc="Các hàm hỗ trợ tính toán">
    public static double round(double a, int n) {
        double cs = pow(10, n + 1);
        int num = (int) (a * cs);
        if (num % 10 >= 5) {
            num = num / 10 + 1;
        } else {
            num /= 10;
        }
        return (double) num / pow(10, n);
    }

    public static double pow(double a, int n) {
        double result = a;
        for (int i = 1; i < n; i++) {
            result *= a;
        }
        return result;
    }
    //</editor-fold>

    //<editor-fold desc="Hàm di chuyển theo Thread">
    public void start() {
        if (td == null) {
            td = new Thread(this);
            td.start();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (draw.quaCachTu == false) {
                    int length = draw.getLength();
                    length += 6;
                    draw.setLength(length);
                }
                this.length += 1;
                Thread.sleep(25);
            }
        } catch (Exception e) {
        }
    }
    //</editor-fold>
}
