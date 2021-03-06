/*
By
Muhammad Difagama Ivanka
1202184310
SI-42-08
*/
package simulasiabsensi;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.*;
import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

public class Presensi_DataDosen extends javax.swing.JFrame {
    Border bdr = BorderFactory.createLoweredSoftBevelBorder();  
  Border bdr1 = BorderFactory.createRaisedSoftBevelBorder();  
  Format dateFormat = new SimpleDateFormat ("EEEEEEEEE, dd/MM/yyyy ");
  String tbt = dateFormat.format(new java.util.Date());
  
private Connection con;
  private PreparedStatement pst;
    private ResultSet rs;
    
    public Presensi_DataDosen(String fn) {
        initComponents();
        tanggalandsnprs.setText(tbt);
        fnShowdtpresdosn.setText(fn);
        connection();
        showLectures();
        columnWidth();
        fnShowdtpresdosn.setVisible(false);
        
        new Thread()
        {
        @Override
        public void run()
        {
            int timerun = 0;
         while (timerun == 0)
                {
                 Calendar cal = new GregorianCalendar();
                 int jam = cal.get(Calendar.HOUR_OF_DAY);
                 int mnt = cal.get(Calendar.MINUTE);
                 long dtk = cal.get(Calendar.SECOND);
  
                 String time = jam+"."+mnt+"."+dtk+" WIB";  
                 jamdndnglpRFID.setText(time);
                }
    }
    }.start();
    }
    
  public void connection() {
      String url ="jdbc:mysql://localhost:3306/dbsimulasiabsen";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }

    }

    public void columnWidth() {
        TableColumn column;
    tablePresensi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        column = tablePresensi.getColumnModel().getColumn(0);
        column.setPreferredWidth(10);
        column = tablePresensi.getColumnModel().getColumn(1);
        column.setPreferredWidth(80);
        column = tablePresensi.getColumnModel().getColumn(2);
        column.setPreferredWidth(60);
        column = tablePresensi.getColumnModel().getColumn(3);
        column.setPreferredWidth(70);
        column = tablePresensi.getColumnModel().getColumn(4);
        column.setPreferredWidth(150);
        column = tablePresensi.getColumnModel().getColumn(5);
        column.setPreferredWidth(190);
        column = tablePresensi.getColumnModel().getColumn(6);
        column.setPreferredWidth(120);
        column = tablePresensi.getColumnModel().getColumn(7);
        column.setPreferredWidth(70);
        column = tablePresensi.getColumnModel().getColumn(8);
        column.setPreferredWidth(70);
        column = tablePresensi.getColumnModel().getColumn(9);
        column.setPreferredWidth(50);
    }

    public void showLectures() {
        DefaultTableModel kolom = new DefaultTableModel();
        kolom.addColumn("No"); 
        kolom.addColumn("rfid Reader");
        kolom.addColumn("Status");
        kolom.addColumn("NIM");
        kolom.addColumn("Nama");
        kolom.addColumn("Mata Kuliah");
        kolom.addColumn("Tanggal Tap");
        kolom.addColumn("Waktu Tap");
        kolom.addColumn("Ruangan");
        kolom.addColumn("Kehadiran");
      
        String qury = "SELECT * FROM `rfidcard`,`datadosen` "
               + "Where `rfidcard`.`Status` ='Mahasiswa' "
               + "AND `datadosen`.`Nama` ='"+
               fnShowdtpresdosn.getText() +
            "' AND `rfidcard`.`Mata Kuliah`=`datadosen`.`Mata Kuliah diampu`"
           + " AND `rfidcard`.`Tanggal Tap` ='"+tanggalandsnprs.getText()+"'"; 
        try {
            int i = 1;
            pst = con.prepareStatement(qury);
            rs = pst.executeQuery();
            while (rs.next()) {
                kolom.addRow(new Object[]{
                    ("" + i++),
                    rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)
                });
                tablePresensi.setModel(kolom);
                //BtnTambah.requestFocus();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
             
    }
    private void UpdateTabel1() {
        try {
            String sql = "SELECT * FROM `rfidcard`";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tablePresensi.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePresensi = new javax.swing.JTable();
        backIndmk = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        printoutDMK = new javax.swing.JLabel();
        fnShowdtpresdosn = new javax.swing.JLabel();
        tanggalandsnprs = new javax.swing.JLabel();
        confrmkhdrn = new javax.swing.JLabel();
        confrmtidak = new javax.swing.JLabel();
        jamdndnglpRFID = new javax.swing.JLabel();
        allPrscsns = new javax.swing.JRadioButton();
        abscAll = new javax.swing.JRadioButton();
        clearAll = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        getContentPane().setLayout(null);

        jScrollPane1.setName("Informasi Buku"); // NOI18N

        tablePresensi.setAutoCreateRowSorter(true);
        tablePresensi.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        tablePresensi.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tablePresensi.setFont(new java.awt.Font("Garamond", 0, 12)); // NOI18N
        tablePresensi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePresensi.setGridColor(new java.awt.Color(0, 0, 0));
        tablePresensi.setName(""); // NOI18N
        tablePresensi.setShowGrid(false);
        tablePresensi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePresensiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePresensi);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(80, 140, 960, 370);

        backIndmk.setBackground(java.awt.Color.pink);
        backIndmk.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        backIndmk.setForeground(java.awt.Color.white);
        backIndmk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backIndmk.setText("<< Back");
        backIndmk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backIndmk.setPreferredSize(new java.awt.Dimension(63, 23));
        backIndmk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backIndmkMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backIndmkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backIndmkMouseExited(evt);
            }
        });
        getContentPane().add(backIndmk);
        backIndmk.setBounds(20, 30, 80, 23);

        jLabel1.setFont(new java.awt.Font("Georgia", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("List Presensi RFID");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(450, 20, 390, 50);

        printoutDMK.setBackground(java.awt.Color.red);
        printoutDMK.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        printoutDMK.setForeground(new java.awt.Color(255, 255, 255));
        printoutDMK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        printoutDMK.setText("Printout");
        printoutDMK.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        printoutDMK.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        printoutDMK.setOpaque(true);
        printoutDMK.setPreferredSize(new java.awt.Dimension(63, 23));
        printoutDMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printoutDMKMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printoutDMKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                printoutDMKMouseExited(evt);
            }
        });
        getContentPane().add(printoutDMK);
        printoutDMK.setBounds(650, 540, 170, 40);

        fnShowdtpresdosn.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        fnShowdtpresdosn.setForeground(new java.awt.Color(255, 255, 255));
        fnShowdtpresdosn.setText("Fullname User");
        getContentPane().add(fnShowdtpresdosn);
        fnShowdtpresdosn.setBounds(150, 80, 170, 30);

        tanggalandsnprs.setForeground(new java.awt.Color(255, 255, 255));
        tanggalandsnprs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tanggalandsnprs.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        getContentPane().add(tanggalandsnprs);
        tanggalandsnprs.setBounds(650, 110, 130, 20);

        confrmkhdrn.setBackground(java.awt.Color.pink);
        confrmkhdrn.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        confrmkhdrn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        confrmkhdrn.setText("Hadir");
        confrmkhdrn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        confrmkhdrn.setOpaque(true);
        confrmkhdrn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confrmkhdrnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confrmkhdrnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confrmkhdrnMouseExited(evt);
            }
        });
        getContentPane().add(confrmkhdrn);
        confrmkhdrn.setBounds(370, 540, 130, 40);

        confrmtidak.setBackground(java.awt.Color.pink);
        confrmtidak.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        confrmtidak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        confrmtidak.setText("Tidak Hadir");
        confrmtidak.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        confrmtidak.setOpaque(true);
        confrmtidak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confrmtidakMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confrmtidakMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confrmtidakMouseExited(evt);
            }
        });
        getContentPane().add(confrmtidak);
        confrmtidak.setBounds(510, 540, 130, 40);

        jamdndnglpRFID.setForeground(new java.awt.Color(255, 255, 255));
        jamdndnglpRFID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jamdndnglpRFID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        getContentPane().add(jamdndnglpRFID);
        jamdndnglpRFID.setBounds(790, 110, 110, 20);

        buttonGroup1.add(allPrscsns);
        allPrscsns.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        allPrscsns.setForeground(java.awt.Color.white);
        allPrscsns.setText("Hadir Semua");
        allPrscsns.setOpaque(false);
        allPrscsns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                allPrscsnsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                allPrscsnsMouseExited(evt);
            }
        });
        allPrscsns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allPrscsnsActionPerformed(evt);
            }
        });
        getContentPane().add(allPrscsns);
        allPrscsns.setBounds(1050, 340, 130, 25);

        buttonGroup1.add(abscAll);
        abscAll.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        abscAll.setForeground(java.awt.Color.white);
        abscAll.setText("Tidak Hadir Semua");
        abscAll.setOpaque(false);
        abscAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                abscAllMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                abscAllMouseExited(evt);
            }
        });
        abscAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abscAllActionPerformed(evt);
            }
        });
        getContentPane().add(abscAll);
        abscAll.setBounds(1050, 370, 170, 25);

        buttonGroup1.add(clearAll);
        clearAll.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        clearAll.setForeground(java.awt.Color.white);
        clearAll.setText("Kosongkan");
        clearAll.setOpaque(false);
        clearAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearAllMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearAllMouseExited(evt);
            }
        });
        clearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAllActionPerformed(evt);
            }
        });
        getContentPane().add(clearAll);
        clearAll.setBounds(1050, 400, 110, 25);

        jLabel3.setBackground(new java.awt.Color(102, 0, 102));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/wood_trees_gloomy_fog_haze_darkness_50175_1280x720.jpg"))); // NOI18N
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablePresensiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePresensiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablePresensiMouseClicked

    private void backIndmkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backIndmkMouseClicked
      this.dispose();
    }//GEN-LAST:event_backIndmkMouseClicked

    private void backIndmkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backIndmkMouseEntered
backIndmk.setForeground(Color.pink);
    }//GEN-LAST:event_backIndmkMouseEntered

    private void printoutDMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printoutDMKMouseClicked
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
       jfc.showSaveDialog(null);
   
        PreparedStatement psm;
       ResultSet rss;
       String query ="SELECT * FROM `rfidcard`,`datadosen` "
               + "Where `rfidcard`.`Status` ='Mahasiswa' "
               + "AND `datadosen`.`Nama` ='"+
               fnShowdtpresdosn.getText() +
            "' AND `rfidcard`.`Mata Kuliah`=`datadosen`.`Mata Kuliah diampu`"
            + " AND `rfidcard`.`Tanggal Tap` ='"+tanggalandsnprs.getText()+"'";
        try{
   psm = ConnecttoSQL.getConnection().prepareStatement(query);
   rss = psm.executeQuery();
   Document my_pdf_report = new Document();
  PdfWriter.getInstance(my_pdf_report, 
          new FileOutputStream(jfc.getSelectedFile()
                           +"/List Presensi RFID for Dosen "+
                  fnShowdtpresdosn.getText()+".pdf"));
                my_pdf_report.open();         
                PdfPTable my_report_table = new PdfPTable(9);
                PdfPCell table_cell;
               
               String headerr = "Seri Mesin RFID";
               table_cell=new PdfPCell(new Phrase(headerr));
               my_report_table.addCell(table_cell);
               String headerr1 = "Status";
               table_cell=new PdfPCell(new Phrase(headerr1));
               my_report_table.addCell(table_cell);
               String headerr2 = "NIM";
               table_cell=new PdfPCell(new Phrase(headerr2));
               my_report_table.addCell(table_cell);
               String headerr3 = "Nama";
               table_cell=new PdfPCell(new Phrase(headerr3));
               my_report_table.addCell(table_cell);
               String headerr4 = "Mata Kuliah";
               table_cell=new PdfPCell(new Phrase(headerr4));
               my_report_table.addCell(table_cell);
               String headerr5 = "Tanggal Tap";
               table_cell=new PdfPCell(new Phrase(headerr5));
               my_report_table.addCell(table_cell);
               String headerr6 = "Waktu Tap";
               table_cell=new PdfPCell(new Phrase(headerr6));
               my_report_table.addCell(table_cell);
               String headerr7 = "Ruangan";
               table_cell=new PdfPCell(new Phrase(headerr7));
               my_report_table.addCell(table_cell);
               String headerr8 = "Kehadiran";
               table_cell=new PdfPCell(new Phrase(headerr8));
               my_report_table.addCell(table_cell);
                while (rss.next()) {  
                    
                                String ser = rss.getString("Rfid Reader No");
                                table_cell=new PdfPCell(new Phrase(ser));
                                my_report_table.addCell(table_cell);
                                String sts=rss.getString("Status");
                                table_cell=new PdfPCell(new Phrase(sts));
                                my_report_table.addCell(table_cell);
                                String code=rss.getString("Id No.");
                                table_cell=new PdfPCell(new Phrase(code));
                                my_report_table.addCell(table_cell);
                                String nama=rss.getString("Name");
                                table_cell=new PdfPCell(new Phrase(nama));
                                my_report_table.addCell(table_cell);
                                String lectures=rss.getString("Mata Kuliah");
                                table_cell=new PdfPCell(new Phrase(lectures));
                                my_report_table.addCell(table_cell);
                                String dtot=rss.getString("Tanggal Tap");
                                table_cell=new PdfPCell(new Phrase(dtot));
                                my_report_table.addCell(table_cell);
                                String tot=rss.getString("Waktu Tap");
                                table_cell=new PdfPCell(new Phrase(tot));
                                my_report_table.addCell(table_cell);
                                String room=rss.getString("Ruangan");
                                table_cell=new PdfPCell(new Phrase(room));
                                my_report_table.addCell(table_cell);
                                String psn=rss.getString("Kehadiran");
                                table_cell=new PdfPCell(new Phrase(psn));
                                my_report_table.addCell(table_cell);
                                }
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
                /* Close all DB related objects */
                rss.close();
                psm.close(); 
                ConnecttoSQL.getConnection().close();   } 
   catch (SQLException | DocumentException | FileNotFoundException ex) {
   JOptionPane.showMessageDialog(null, ex);
    }            
                
        
    }//GEN-LAST:event_printoutDMKMouseClicked

    private void printoutDMKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printoutDMKMouseEntered
     printoutDMK.setBorder(bdr);
     printoutDMK.setForeground(Color.gray);
    }//GEN-LAST:event_printoutDMKMouseEntered

    private void backIndmkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backIndmkMouseExited
    backIndmk.setForeground(Color.white);
    }//GEN-LAST:event_backIndmkMouseExited

    private void printoutDMKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printoutDMKMouseExited
     printoutDMK.setBorder(bdr1);
     printoutDMK.setForeground(Color.white);
    }//GEN-LAST:event_printoutDMKMouseExited

    private void confrmkhdrnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confrmkhdrnMouseEntered
     confrmkhdrn.setBorder(bdr);
     confrmkhdrn.setForeground(Color.gray);
    }//GEN-LAST:event_confrmkhdrnMouseEntered

    private void confrmkhdrnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confrmkhdrnMouseExited
    confrmkhdrn.setBorder(bdr1);
     confrmkhdrn.setForeground(Color.black);
    }//GEN-LAST:event_confrmkhdrnMouseExited

    private void confrmkhdrnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confrmkhdrnMouseClicked
   int grhd = tablePresensi.getSelectedRow();
    PreparedStatement pss;
   String query = "UPDATE `rfidcard` SET `Kehadiran` = 'Hadir' WHERE "
           + "`Id No.` ='"+tablePresensi.getValueAt(grhd, 3)+"'";
   
        try {
        con = DriverManager.getConnection
        ("jdbc:mysql://localhost/dbsimulasiabsen", "root", "");
         pss = con.prepareStatement(query);
         pss.executeUpdate();
         pss.close();
         UpdateTabel1();
         showLectures();
         JOptionPane.showMessageDialog(null, "Konfirmasi berhasil :)");
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_confrmkhdrnMouseClicked

    private void confrmtidakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confrmtidakMouseClicked
         int grhd = tablePresensi.getSelectedRow();
         PreparedStatement pss;
   String query = "UPDATE `rfidcard` SET `Kehadiran` = 'Absen' WHERE "
           + "`Id No.` ='"+tablePresensi.getValueAt(grhd, 3)+"'";
   
        try {
        con = DriverManager.getConnection
        ("jdbc:mysql://localhost/dbsimulasiabsen", "root", "");
         pss = con.prepareStatement(query);
         pss.executeUpdate();
         pss.close();
         UpdateTabel1();
         showLectures();
         JOptionPane.showMessageDialog(null, "Konfirmasi berhasil :)");
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_confrmtidakMouseClicked

    private void confrmtidakMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confrmtidakMouseEntered
       confrmtidak.setBorder(bdr);
     confrmtidak.setForeground(Color.gray);
    }//GEN-LAST:event_confrmtidakMouseEntered

    private void confrmtidakMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confrmtidakMouseExited
        confrmtidak.setBorder(bdr1);
     confrmtidak.setForeground(Color.black);
    }//GEN-LAST:event_confrmtidakMouseExited

    private void allPrscsnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allPrscsnsActionPerformed
      PreparedStatement pss;
        String query = "UPDATE `rfidcard` SET `Kehadiran` = 'Hadir' WHERE "
                + "`Tanggal Tap` ='"+tanggalandsnprs.getText()+"'";
   
        try {
        con = DriverManager.getConnection
        ("jdbc:mysql://localhost/dbsimulasiabsen", "root", "");
        pss = con.prepareStatement(query);
         pss.executeUpdate();
         pss.close();
         UpdateTabel1();
         showLectures();
         JOptionPane.showMessageDialog(null, "Konfirmasi berhasil :)");
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_allPrscsnsActionPerformed

    private void abscAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abscAllActionPerformed
        PreparedStatement pss;
        String query = "UPDATE `rfidcard` SET `Kehadiran` = 'Absen' WHERE "
                + "`Tanggal Tap` ='"+tanggalandsnprs.getText()+"'";
   
        try {
        con = DriverManager.getConnection
        ("jdbc:mysql://localhost/dbsimulasiabsen", "root", "");
        pss = con.prepareStatement(query);
         pss.executeUpdate();
         pss.close();
         UpdateTabel1();
         showLectures();
         JOptionPane.showMessageDialog(null, "Konfirmasi berhasil :)");
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_abscAllActionPerformed

    private void clearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllActionPerformed
        PreparedStatement pss;
        String query = "UPDATE `rfidcard` SET `Kehadiran` = '' WHERE "
                + "`Tanggal Tap` ='"+tanggalandsnprs.getText()+"'";
   
        try {
        con = DriverManager.getConnection
        ("jdbc:mysql://localhost/dbsimulasiabsen", "root", "");
        pss = con.prepareStatement(query);
         pss.executeUpdate();
         pss.close();
         UpdateTabel1();
         showLectures();
         JOptionPane.showMessageDialog(null, "Konfirmasi berhasil :)");
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_clearAllActionPerformed

    private void allPrscsnsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allPrscsnsMouseEntered
     allPrscsns.setForeground(Color.green);
    }//GEN-LAST:event_allPrscsnsMouseEntered

    private void allPrscsnsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allPrscsnsMouseExited
      allPrscsns.setForeground(Color.white);
    }//GEN-LAST:event_allPrscsnsMouseExited

    private void abscAllMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abscAllMouseEntered
       abscAll.setForeground(Color.red);
    }//GEN-LAST:event_abscAllMouseEntered

    private void abscAllMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abscAllMouseExited
      abscAll.setForeground(Color.white);
    }//GEN-LAST:event_abscAllMouseExited

    private void clearAllMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearAllMouseEntered
      clearAll.setForeground(Color.lightGray);
    }//GEN-LAST:event_clearAllMouseEntered

    private void clearAllMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearAllMouseExited
      clearAll.setForeground(Color.white);
    }//GEN-LAST:event_clearAllMouseExited

    public static void main(String args[]) {
     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : 
                    javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | 
                InstantiationException |
                IllegalAccessException | 
                javax.swing.UnsupportedLookAndFeelException ex) {
              JOptionPane.showMessageDialog(null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Presensi_DataDosen(null).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton abscAll;
    private javax.swing.JRadioButton allPrscsns;
    private javax.swing.JLabel backIndmk;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton clearAll;
    private javax.swing.JLabel confrmkhdrn;
    private javax.swing.JLabel confrmtidak;
    private javax.swing.JLabel fnShowdtpresdosn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jamdndnglpRFID;
    private javax.swing.JLabel printoutDMK;
    private javax.swing.JTable tablePresensi;
    private javax.swing.JLabel tanggalandsnprs;
    // End of variables declaration//GEN-END:variables
}
