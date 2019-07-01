
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public final class image {

    private drawWindow draw;
    public static final int NGANG = 0;
    public static final int DOC = 1;
    public static final int TU_DO = 2;
    private int phuongDiChuyen;
    private int[] gioiHanDiChuyen = new int[2];
    private String name; //Đường dẫn tới hình ảnh
    public Image img;
    public int x, y; //Tọa độ x, y của hình ảnh

    public image(String name) {
        this.name = name;
        img = getImage(name);
    }

    public image(String name, int width, int height) {
        this.name = name;
        img = getImage(name, width, height);
    }

    public image(String name, int width, int height, int x, int y) {
        this.name = name;
        img = getImage(name, width, height);
        this.x = x;
        this.y = y;
    }

    public int getWidth(ImageObserver ob) {
        return img.getWidth(ob);
    }

    public int getHeight(ImageObserver ob) {
        return img.getHeight(ob);
    }

    public Point getLocation() {
        return new Point(x, y);
    }

    public int[] getGioiHanDiChuyen() {
        return gioiHanDiChuyen;
    }

    //Cài đặt phương di chuyển của hình ảnh
    public void setPhuongDiChuyen(int phuong) {
        this.phuongDiChuyen = phuong;
    }

    //Cài đặt hai đầu mút khi di chuyển
    public void setGioiHanDiChuyen(int start, int end) {
        this.gioiHanDiChuyen[0] = start;
        this.gioiHanDiChuyen[1] = end;
    }

    //Cài đặt tọa độ cho hình ảnh
    public void setLocation(Point p) {
        this.x = p.x;
        this.y = p.y;
        
        if (gioiHanDiChuyen[0] == 0 && gioiHanDiChuyen[1] == 0) {
            return;
        }
        if (this.x > this.gioiHanDiChuyen[1]) {
            this.x = this.gioiHanDiChuyen[1];
        } else if (this.x < this.gioiHanDiChuyen[0]) {
            this.x = this.gioiHanDiChuyen[0];
        }
        if (this.y > this.gioiHanDiChuyen[1]) {
            this.y = this.gioiHanDiChuyen[1];
        } else if (this.y < this.gioiHanDiChuyen[0]) {
            this.y = this.gioiHanDiChuyen[0];
        }
    }

    //Lấy viền chữ nhật bao quanh hình
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(null), getHeight(null));
    }

    //Kiểm tra va chạm giữa hình và một điểm
    public boolean isCollision(Point p) {
        Rectangle imgRect = getBounds();
        return imgRect.contains(p);
    }

    //Hàm di chuyển hình ảnh theo tọa độ chuột
    public void moving(Point p) {
        switch (phuongDiChuyen) {
            case NGANG: {
                int dx = p.x - this.x;
                this.x += dx;
                if (this.x > this.gioiHanDiChuyen[1]) {
                    this.x = this.gioiHanDiChuyen[1];
                } else if (this.x < this.gioiHanDiChuyen[0]) {
                    this.x = this.gioiHanDiChuyen[0];
                }
                break;
            }
            case DOC: {
                int dy = p.y - this.y;
                this.y += dy;
                if (this.y > this.gioiHanDiChuyen[1]) {
                    this.y = this.gioiHanDiChuyen[1];
                } else if (this.y < this.gioiHanDiChuyen[0]) {
                    this.y = this.gioiHanDiChuyen[0];
                }
                break;
            }
            case TU_DO: {
                int dx = p.x - this.x;
                int dy = p.y - this.y;
                this.x += dx;
                this.y += dy;
                break;
            }
        }
    }

    private Image getImage(String name) {
        Image image = null;
        try {

            InputStream is = this.getClass().getResourceAsStream("res/" + name);
            image = ImageIO.read(is);
            //String path = "res/" + name;
            //return Toolkit.getDefaultToolkit().getImage(path);
        } catch (IOException ex) {
            Logger.getLogger(image.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    public Image getImage(String name, int k, int m) {
        /*linkImage là tên icon, k kích thước chiều rộng mình muốn,m chiều dài và hàm này trả về giá trị là 1 icon.*/
        try {
            InputStream is = this.getClass().getResourceAsStream("res/" + name);
            Image image = ImageIO.read(is);
            //BufferedImage image = ImageIO.read(new File("res/" + name));//đọc ảnh dùng BufferedImage
            Icon icon = new ImageIcon(image.getScaledInstance(k, m, image.SCALE_SMOOTH));
            return iconToImage(icon);
        } catch (IOException e) {
        }
        return null;

    }

    private Image iconToImage(Icon icon) {
        Image result;
        if (icon instanceof ImageIcon) {
            result = ((ImageIcon) icon).getImage();
        } else {
            BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            icon.paintIcon(null, image.getGraphics(), 0, 0);
            result = image;
        }
        return result;
    }
}
