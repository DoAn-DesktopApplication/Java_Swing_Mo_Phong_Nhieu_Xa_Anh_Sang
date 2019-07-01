
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class controlWindow extends JPanel {

    private final mainWindow main;
    private JLabel lb1, lb4, lb3, lb2, lb5;
    public JTextField tfL, tfN, tfDoRongKhe, tfKhoangCach2Khe, tfTiLeKhe;

    public controlWindow(mainWindow main) {
        this.main = main;
        initComponents();
        initComponent();
    }

    //<editor-fold desc="Các hàm get và set">
    public Color getColor() {
        return bangMau.getBackground();
    }

    public double getLambda() {
        double k = (double) colorSlider.getMaximum() / (760 - 440);
        double lambda = Math.pow(10, -9) * (760 - colorSlider.getValue() * k);

        return lambda;
    }

    public double getLambda(int value) {
        double k = (double) colorSlider.getMaximum() / (760 - 440);
        double lambda = Math.pow(10, -9) * (760 - value * k);

        return lambda;
    }

    public int getManChan() {
        return this.cbManChan.getSelectedIndex();
    }
    //</editor-fold>

    //<editor-fold desc="Khởi tạo và các sự kiện">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jLabel2 = new javax.swing.JLabel();
        lbColor = new javax.swing.JLabel();
        lbManChan = new javax.swing.JLabel();
        cbManChan = new javax.swing.JComboBox<>();
        colorSlider = new javax.swing.JSlider();
        bangMau = new javax.swing.JTextField();
        lbLambda = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbThemes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jLabel2.setText("jLabel2");

        setBackground(java.awt.Color.white);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        setMaximumSize(new java.awt.Dimension(300, 800));
        setMinimumSize(new java.awt.Dimension(300, 800));
        setPreferredSize(new java.awt.Dimension(300, 800));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 0, 0, 0, 0};
        layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        setLayout(layout);

        lbColor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbColor.setText("Chọn màu tia sáng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        add(lbColor, gridBagConstraints);

        lbManChan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbManChan.setText("Chọn loại vật cản");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 36;
        gridBagConstraints.gridwidth = 5;
        add(lbManChan, gridBagConstraints);

        cbManChan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cách Tử", "Khe" }));
        cbManChan.setToolTipText("Chọn loại màn chắn");
        cbManChan.setMaximumSize(new java.awt.Dimension(100, 25));
        cbManChan.setMinimumSize(new java.awt.Dimension(100, 25));
        cbManChan.setPreferredSize(new java.awt.Dimension(100, 25));
        cbManChan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbManChanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 38;
        gridBagConstraints.gridwidth = 5;
        add(cbManChan, gridBagConstraints);
        cbManChan.getAccessibleContext().setAccessibleDescription("Chọn loại vật cản");

        colorSlider.setMajorTickSpacing(72);
        colorSlider.setMaximum(360);
        colorSlider.setPaintLabels(true);
        colorSlider.setPaintTicks(true);
        colorSlider.setToolTipText("Bước sóng ánh sáng");
        colorSlider.setValue(0);
        colorSlider.setAutoscrolls(true);
        colorSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        colorSlider.setPreferredSize(new java.awt.Dimension(250, 45));
        colorSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                colorSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 5;
        add(colorSlider, gridBagConstraints);
        Hashtable hash = new Hashtable();

        int space = colorSlider.getMajorTickSpacing();
        int size = colorSlider.getMaximum()/space;
        for (int i  = 0; i<=size; i++){
            int value = space*i;
            String text = String.valueOf((int)(getLambda(value)*Math.pow(10, 9)));
            hash.put(new Integer(value), new JLabel(text));
        }

        colorSlider.setLabelTable(hash);

        bangMau.setEditable(false);
        bangMau.setBackground(new java.awt.Color(255, 0, 0));
        bangMau.setPreferredSize(new java.awt.Dimension(200, 26));
        bangMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bangMauActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 5;
        add(bangMau, gridBagConstraints);

        lbLambda.setText("760 nm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 60, 0, 0);
        add(lbLambda, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Chọn giao diện");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 90);
        add(jLabel1, gridBagConstraints);

        cbThemes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sáng", "Tối" }));
        cbThemes.setMinimumSize(new java.awt.Dimension(75, 25));
        cbThemes.setPreferredSize(new java.awt.Dimension(75, 25));
        cbThemes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbThemesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 150, 0, 0);
        add(cbThemes, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Lambda:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        add(jLabel3, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void colorSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_colorSliderStateChanged
        bangMau.setBackground(new Color(Color.HSBtoRGB((float) colorSlider.getValue() / (float) 480.0, 1, 1)));
        main.draw.gr.setLambda(getLambda());
        lbLambda.setText(String.valueOf((int) (getLambda() * Math.pow(10, 9))) + " nm");
    }//GEN-LAST:event_colorSliderStateChanged

    private void bangMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bangMauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bangMauActionPerformed

    private void cbManChanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbManChanActionPerformed
        int index = cbManChan.getSelectedIndex() + 1;
        main.draw.gr.update(index % 2);
        main.draw.update();
        main.draw.repaint();
        updateComponent();
        tfL.selectAll();
        cbManChan.setFocusable(false);
    }//GEN-LAST:event_cbManChanActionPerformed

    private void cbThemesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbThemesActionPerformed
        int index = cbThemes.getSelectedIndex();
        if (index == 0) {
            main.setThemes(false);
            main.draw.updateThemes();
            this.updateThemes();
        } else {
            main.setThemes(true);
            main.draw.updateThemes();
            this.updateThemes();
        }
        main.draw.repaint();
    }//GEN-LAST:event_cbThemesActionPerformed

    private void updateThemes() {
        this.setBackground(main.getColor()[4]);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bangMau;
    public javax.swing.JComboBox<String> cbManChan;
    private javax.swing.JComboBox<String> cbThemes;
    public javax.swing.JSlider colorSlider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lbColor;
    private javax.swing.JLabel lbLambda;
    private javax.swing.JLabel lbManChan;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>

    private void initComponent() {
        lb1 = new JLabel("Khoảng cách vật cản - màn (L):             cm");
        lb4 = new JLabel("Số khe của cách tử (N) : ");
        lb3 = new JLabel("Độ rộng khe (b):             um");
        lb2 = new JLabel("Chu kì của cách tử (d):              um");
        lb5 = new JLabel("Độ rộng 1 khe (b) =             % d");
        Font font = new Font("Tahoma", 1, 12);
        lb1.setFont(font);
        lb4.setFont(font);
        lb3.setFont(font);
        lb2.setFont(font);
        lb5.setFont(font);
        tfL = new JTextField();
        tfN = new JTextField();
        tfKhoangCach2Khe = new JTextField();
        tfDoRongKhe = new JTextField();
        tfTiLeKhe = new JTextField();
        tfL.setPreferredSize(new Dimension(50, 25));
        tfL.setHorizontalAlignment(JTextField.CENTER);
        tfN.setPreferredSize(new Dimension(30, 25));
        tfN.setHorizontalAlignment(JTextField.CENTER);
        tfDoRongKhe.setPreferredSize(new Dimension(50, 25));
        tfDoRongKhe.setHorizontalAlignment(JTextField.CENTER);
        tfKhoangCach2Khe.setPreferredSize(new Dimension(50, 25));
        tfKhoangCach2Khe.setHorizontalAlignment(JTextField.CENTER);
        tfTiLeKhe.setPreferredSize(new Dimension(50, 25));
        tfTiLeKhe.setHorizontalAlignment(JTextField.CENTER);

        //Đặt giới hạn giá trị cho textField
        tfL.setDocument(new NumbericTextField(tfL, NumbericTextField.REAL_NUMBER, 2, 10.07, 90.1));
        tfN.setDocument(new NumbericTextField(tfN, NumbericTextField.INT_NUMBER, 1, 15));
        tfKhoangCach2Khe.setDocument(new NumbericTextField(tfKhoangCach2Khe, NumbericTextField.REAL_NUMBER, 2));
        tfDoRongKhe.setDocument(new NumbericTextField(tfDoRongKhe, NumbericTextField.REAL_NUMBER, 2, 1, 500));
        tfTiLeKhe.setDocument(new NumbericTextField(tfTiLeKhe, NumbericTextField.REAL_NUMBER, 2, 1, 80));

        //Khởi tạo grid
        GridBagConstraints grid = new GridBagConstraints();
        Insets inset = new Insets(0, 0, 0, 0);

        //Add label và Textfield cho L
        grid.insets = inset;
        inset.top = 20;
        grid.gridx = 0;
        grid.gridy = 46;
        grid.gridwidth = 2;
        inset.left = 15;
        grid.anchor = GridBagConstraints.WEST;
        add(lb1, grid);

        grid.gridx = 1;
        grid.gridwidth = 4;
        inset.right = 0;
        inset.left = 42;
        tfL.setText("" + (main.draw.gr.getL() * 100));
        add(tfL, grid);

        //Add label và Textfield cho doRongKhe
        inset.top = 0;
        grid.gridx = 0;
        grid.gridy = 52;
        grid.gridwidth = 2;
        inset.left = 15;
        inset.right = 0;
        add(lb5, grid);
        lb3.setVisible(false);
        add(lb3, grid);

        grid.gridx = 1;
        grid.gridwidth = 4;
        inset.left = -28;
        inset.right = 0;
        tfTiLeKhe.setText(String.valueOf(main.draw.gr.getDoRongKhe() * Math.pow(10, 7)));
        add(tfTiLeKhe, grid);
        inset.left = 120;
        tfDoRongKhe.setVisible(false);
        add(tfDoRongKhe, grid);

        //Add label và Textfield cho khoangCach2Khe
        inset.top = 0;
        grid.gridx = 0;
        grid.gridy = 58;
        grid.gridwidth = 2;
        inset.left = 15;
        inset.right = 0;
        add(lb2, grid);

        grid.gridx = 1;
        grid.gridwidth = 4;
        inset.left = -8;
        inset.right = 0;
        tfKhoangCach2Khe.setText(String.valueOf(main.draw.gr.getKhoangCach2Khe() * Math.pow(10, 6)));
        add(tfKhoangCach2Khe, grid);

        //Add label và Textfield cho N
        grid.gridx = 0;
        grid.gridy = 64;
        grid.gridwidth = 1;
        inset.top = 0;
        inset.left = 15;
        grid.anchor = GridBagConstraints.WEST;
        add(lb4, grid);

        grid.gridx = 1;
        grid.gridwidth = 4;
        inset.left = 0;
        tfN.setText(String.valueOf(main.draw.gr.getN()));
        add(tfN, grid);
    }

    public void updateComponent() {
        //Cập nhật các component theo cách tử hay khe
        Component[] comps = this.getComponents();
        GridBagLayout layout = (GridBagLayout) this.getLayout();

        if (cbManChan.getSelectedIndex() == 0) {
            GridBagConstraints gridL = layout.getConstraints(lb1);
            gridL.insets.left = 210;
            remove(tfL);
            add(tfL, gridL);

            lb5.setVisible(true);
            lb4.setVisible(true);
            lb2.setVisible(true);
            lb3.setVisible(false);
            tfN.setVisible(true);
            tfKhoangCach2Khe.setVisible(true);
            tfTiLeKhe.setVisible(true);
            tfDoRongKhe.setVisible(false);
            tfL.setText(String.valueOf((main.draw.gr.getL() * 100)));
            tfN.setText(String.valueOf(main.draw.gr.getN()));
            tfTiLeKhe.setText(String.valueOf((main.draw.gr.getDoRongKhe() / main.draw.gr.getKhoangCach2Khe()) * 100));
            tfKhoangCach2Khe.setText(String.valueOf(main.draw.gr.getKhoangCach2Khe() * Math.pow(10, 6)));
        } else {
            GridBagConstraints gridL = layout.getConstraints(lb1);
            gridL.insets.left = 210;
            remove(tfL);
            add(tfL, gridL);

            lb4.setVisible(false);
            lb2.setVisible(false);
            lb5.setVisible(false);
            lb3.setVisible(true);
            tfN.setVisible(false);
            tfKhoangCach2Khe.setVisible(false);
            tfTiLeKhe.setVisible(false);
            tfDoRongKhe.setVisible(true);
            tfL.setText(String.valueOf((main.draw.gr.getL() * 100)));
            tfDoRongKhe.setText(String.valueOf(main.draw.gr.getDoRongKhe() * Math.pow(10, 6)));
        }
    }

    public class NumbericTextField extends PlainDocument implements KeyListener {

        public static final int INT_NUMBER = 0;
        public static final int REAL_NUMBER = 1;
        private double end; //Giá trị lớn nhất
        private double start; //Giá trị nhỏ nhất
        private final boolean isLimit; //Kiểm tra có giới hạn giá trị hay không
        private final int formatCharacter;
        private int digit = 0; //Số chữ số sau dấu phẩy
        private final JTextField tf;

        public NumbericTextField(JTextField tf, int formatCharacter) {
            super();
            this.tf = tf;
            this.formatCharacter = formatCharacter;
            this.isLimit = false;
            this.tf.addKeyListener(this);
        }

        public NumbericTextField(JTextField tf, int formatCharacter, int digit) {
            super();
            this.tf = tf;
            this.formatCharacter = formatCharacter;
            this.digit = digit;
            this.isLimit = false;
            this.tf.addKeyListener(this);
        }

        public NumbericTextField(JTextField tf, int formatCharacter, double start, double end) {
            super();
            this.tf = tf;
            this.formatCharacter = formatCharacter;
            this.start = start;
            this.end = end;
            this.digit = digit;
            this.isLimit = true;
            this.tf.addKeyListener(this);
        }

        public NumbericTextField(JTextField tf, int formatCharacter, int digit, double start, double end) {
            super();
            this.tf = tf;
            this.formatCharacter = formatCharacter;
            this.start = start;
            this.end = end;
            this.digit = digit;
            this.isLimit = true;
            this.tf.addKeyListener(this);
        }

        @Override
        public void insertString(int offset, String str, AttributeSet attr)
                throws BadLocationException {
            StringBuilder sb = new StringBuilder(str);

            int count = 0; //Đếm số dấu chấm đã xuất hiện
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                if (c == '.') {
                    if (formatCharacter == REAL_NUMBER) {
                        count++;
                        if (count == 2) {
                            JOptionPane.showMessageDialog(main, "Định dạng số không hợp lệ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            sb.substring(0, sb.toString().lastIndexOf("."));
                        }
                    } else if (formatCharacter == INT_NUMBER) {
                        JOptionPane.showMessageDialog(main, "Định dạng số không hợp lệ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        sb.substring(0, sb.toString().lastIndexOf("."));
                    }
                }
                if (!((c >= '0' && c <= '9') || c == '.') && c != '\n') {
                    sb.deleteCharAt(i);
                }
            }
            if (formatCharacter == REAL_NUMBER && digit > 0 && sb.indexOf(".") != -1 && sb.indexOf(".") + digit < sb.length()) {
                double value = Double.parseDouble(sb.toString());
                sb.delete(0, sb.length());
                sb.append(String.valueOf(graph.round(value, digit)));
            }
            super.insertString(offset, sb.toString(), attr);
        }

        public void changeValue(double value) {
            if (tf == tfN) {
                main.draw.gr.setN((int) value);
            } else if (tf == tfL) {
                main.draw.gr.setL(value / 100);
                main.draw.diChuyenCachTu(value / 100);
            } else if (tf == tfKhoangCach2Khe) {
                main.draw.gr.setKhoangCach2Khe(value / Math.pow(10, 6));
            } else if (tf == tfDoRongKhe) {
                main.draw.gr.setDoRongKhe(value / Math.pow(10, 6));
            } else if (tf == tfTiLeKhe) {
                main.draw.gr.setTiLeKhe(value);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            String str = tf.getText();
            if (formatCharacter == REAL_NUMBER
                    && (e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_ENTER)) {
                if (digit > 0 && str.indexOf(".") != -1 && str.indexOf(".") + digit < str.length()) {
                    JOptionPane.showMessageDialog(main, "Giới hạn số sau dấu phẩy là " + digit, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    tf.setText(str.substring(0, str.indexOf(".") + digit + 1));
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                double value = 0;
                if (tf.getText().length() > 0) {
                    value = Double.parseDouble(tf.getText());
                }
                if (isLimit) {

                    if (value >= start && value <= end) {
                        changeValue(value);
                    } else {
                        if (value > end) {
                            changeValue(end);
                            if (formatCharacter == REAL_NUMBER) {
                                tf.setText("" + end);
                            } else if (formatCharacter == INT_NUMBER) {
                                tf.setText("" + (int)end);
                            }
                        } else {
                            changeValue(start);
                            tf.setText("" + start);
                        }
                    }
                } else {
                    changeValue(value);
                }
            }
        }
    }
}
