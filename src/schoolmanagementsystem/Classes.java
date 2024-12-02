/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package schoolmanagementsystem;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Manages class-related operations in the School Management System.
 * Includes CRUD functionality for classes and role-based access control.
 * 
 * @Author Lindsay Blood
 */
public class Classes extends javax.swing.JFrame {

    /**
     * Default constructor to initialize the form and load class data.
     */
    public Classes() {
        initComponents(); // Initialize UI components
        Connect(); // Establish connection to the database
        Class_Load(); // Load class data into the table
        setIconImage(); // Set application icon
        setTitle("class page"); // Set the title of the window
    }

    /**
     * Sets the application icon using a resource.
     */
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
    }

    // Variables for user details
    int id;
    String uname;
    String usertype;
    
    /**
     * Parameterized constructor to initialize form with role-based access control.
     * 
     * @param id       User ID
     * @param username Username of the user
     * @param utype    Type of user (e.g., Admin, Guest, Student)
     */
    public Classes(int id, String username, String utype) {
        initComponents(); // Initialize UI components
        Connect(); // Establish connection to the database
        Class_Load(); // Load class data into the table
        setIconImage(); // Set application icon
        setTitle("class page"); // Set the title of the window
        this.uname = username; // Assign username
        jLabel20.setText(uname); // Display username on the UI

        this.usertype = utype; // Assign user type
        jLabel30.setText(utype); // Display user type on the UI

        this.id = id; // Assign user ID

        // Disable buttons based on user role
        if (utype.equals("Student") || utype.equals("Guest")) {
            savebutton.setEnabled(false);
            editbutton.setEnabled(false);
            deletebutton.setEnabled(false);
            clearbutton.setEnabled(false);
        } else {
            // Enable buttons for other roles
            savebutton.setEnabled(true);
            editbutton.setEnabled(true);
            deletebutton.setEnabled(true);
            clearbutton.setEnabled(true);
        }
    }

    // Database-related variables
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel d;

    /**
     * Establishes a connection to the database using configuration from a properties file.
     */
    public void Connect() {
        try {
            // Load database credentials from properties file
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\schoolmanagementsystem\\application.properties"));

            String url = properties.getProperty("db.url"); // Database URL
            String username = properties.getProperty("db.username"); // Database username
            String password = properties.getProperty("db.password"); // Database password

            // Load MySQL JDBC driver and establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection successful.");
        } catch (IOException e) {
            // Handle file-related exceptions
            System.out.println("Error loading properties file: " + e.getMessage());
        } catch (SQLException ex) {
            // Handle SQL exceptions
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            // Handle missing JDBC driver exception
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Loads class details from the database and displays them in the table.
     */
    public void Class_Load() {
        int c;
        try {
            pst = con.prepareStatement("select * from class"); // SQL query to fetch class data
            rs = pst.executeQuery(); // Execute the query

            ResultSetMetaData rsd = rs.getMetaData(); // Get metadata of the result set
            c = rsd.getColumnCount(); // Get the number of columns

            d = (DefaultTableModel) jTable1.getModel(); // Get table model
            d.setRowCount(0); // Clear existing data in the table
            while (rs.next()) {
                Vector v2 = new Vector(); // Create a vector to hold the row data
                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("id")); // Add class ID
                    v2.add(rs.getString("classname")); // Add class name
                    v2.add(rs.getString("section")); // Add section
                    v2.add(rs.getString("student_strength")); // Add student strength
                }
                d.addRow(v2); // Add row to the table
            }
        } catch (SQLException ex) {
            // Log SQL exceptions
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtstudentstrength = new javax.swing.JTextField();
        txtclassname = new javax.swing.JComboBox<>();
        txtsection = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        savebutton = new javax.swing.JButton();
        editbutton = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        clearbutton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new rojerusan.RSTableMetro();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Class Details:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 160, 41));

        txtstudentstrength.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtstudentstrength.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel2.add(txtstudentstrength, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 372, 168, 31));

        txtclassname.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtclassname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        txtclassname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtclassname.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(txtclassname, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 170, 168, 35));

        txtsection.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtsection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "A", "B", "C", "D" }));
        txtsection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtsection.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(txtsection, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 268, 168, 35));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Class Name");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 130, -1, 34));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Section ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 228, 65, 34));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Student Strength");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 332, 149, 34));

        savebutton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        savebutton.setText("SAVE");
        savebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        savebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(savebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 436, -1, 33));

        editbutton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editbutton.setText("EDIT");
        editbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(editbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 436, -1, 33));

        deletebutton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deletebutton.setText("DELETE");
        deletebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 497, -1, 33));

        clearbutton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        clearbutton.setText("CLEAR");
        clearbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(clearbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 497, -1, 33));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/class.png"))); // NOI18N
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 50, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 244, 561));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Section", "Strength"
            }
        ));
        jTable1.setColorBackgoundHead(new java.awt.Color(51, 153, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 91, 475, 177));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Lindsay Blood");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(652, 539, -1, -1));

        jLabel6.setText("Developed by:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(569, 539, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(714, 6, -1, 39));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("LOGOUT");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(648, 15, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("USER TYPE:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 37, -1, 33));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("USERNAME:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 15, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("USERNAME");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 15, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("USERTYPE");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 45, -1, -1));

        jMenu1.setText("Main Menu");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("About Project");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void savebuttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // Insert new class details into the database
        try {
            String name = txtclassname.getSelectedItem().toString();
            String section = txtsection.getSelectedItem().toString();
            String student_strength = txtstudentstrength.getText();

            pst = con.prepareStatement("insert into class(classname,section,student_strength)values(?,?,?)");
            pst.setString(1, name);
            pst.setString(2, section);
            pst.setString(3, student_strength);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "class details added successfully...");
            Class_Load();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }                                          

    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // Delete selected class details from the database
        try {
            d = (DefaultTableModel) jTable1.getModel();
            int selectIndex = jTable1.getSelectedRow();

            String id = d.getValueAt(selectIndex, 0).toString();

            pst = con.prepareStatement("delete from class where id=?");
            pst.setString(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "class details deleted successfully...");
            savebutton.setEnabled(true);

            txtclassname.setSelectedIndex(0);
            txtsection.setSelectedIndex(0);
            txtstudentstrength.setText("");

            Class_Load();
            savebutton.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                            

    private void clearbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // Clear input fields for class details
        txtclassname.setSelectedIndex(0);
        txtsection.setSelectedIndex(0);
        txtstudentstrength.setText("");
        savebutton.setEnabled(true);

    }                                           

    private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // Update existing class details in the database
        try {
            d = (DefaultTableModel) jTable1.getModel();
            int selectIndex = jTable1.getSelectedRow();

            String id = d.getValueAt(selectIndex, 0).toString();
            String classname = txtclassname.getSelectedItem().toString();
            String sectionname = txtsection.getSelectedItem().toString();
            String studentstrength = txtstudentstrength.getText();

            pst = con.prepareStatement("update class set classname=?,section=?,student_strength=? where id=?");

            pst.setString(1, classname);
            pst.setString(2, sectionname);
            pst.setString(3, studentstrength);
            pst.setString(4, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "class details edited successfully...");
            savebutton.setEnabled(true);

            txtsection.setSelectedIndex(0);
            txtclassname.setSelectedIndex(0);
            txtstudentstrength.setText("");
            txtsection.requestFocus();

            Class_Load();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                          

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {                                    
        // Navigate to the main menu
        Main m = new Main(id, uname, usertype);
        m.setVisible(true);
        this.setVisible(false);
    }                                   

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {                                    
        // Navigate to the "About Project" page
        About a = new About();
        a.setVisible(true);
        this.setVisible(false);
    }                                   

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
        // Load selected row details into the input fields
        d = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();

        String id = d.getValueAt(selectIndex, 0).toString();
        txtclassname.setSelectedItem(d.getValueAt(selectIndex, 1).toString());
        txtsection.setSelectedItem(d.getValueAt(selectIndex, 2).toString());
        txtstudentstrength.setText(d.getValueAt(selectIndex, 3).toString());

        savebutton.setEnabled(false);

    }                                    

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {                                     
        // Open developer's GitHub profile in the default browser
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/naveenkumar-j"));
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                    

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {                                     
        // Logout and navigate to the Login page
        Login l = new Login();
        l.setVisible(true);
        this.setVisible(false);
    }                                    

    /**
     * Main method to launch the Classes form.
     * 
     * @param args Command-line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Classes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Classes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Classes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Classes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Classes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton clearbutton;
    private javax.swing.JButton deletebutton;
    private javax.swing.JButton editbutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSTableMetro jTable1;
    private javax.swing.JButton savebutton;
    private javax.swing.JComboBox<String> txtclassname;
    private javax.swing.JComboBox<String> txtsection;
    private javax.swing.JTextField txtstudentstrength;
    // End of variables declaration                   
}
