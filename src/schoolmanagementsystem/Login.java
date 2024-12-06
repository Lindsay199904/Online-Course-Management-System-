/*
 * Login.java
 * This class handles the Login page of the School Management System.
 * Users can log in with their username, password, and user type.
 */

package schoolmanagementsystem;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * GUI and logic for user login functionality.
 * Allows users to authenticate based on their credentials and navigate to the main application.
 * Developed by Lindsay M Blood.
 */
public class Login extends javax.swing.JFrame {

    // Database connection variables
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Constructor initializes components and sets up the login page.
     */
    public Login() {
        initComponents(); // Initialize UI components
        setIconImage(); // Set the application icon
        Connect(); // Establish a database connection
        setTitle("Login Page"); // Set the window title
    }

    /**
     * Establishes a connection to the database using credentials from a properties file.
     */
    public void Connect() {
        try {
            // Load database properties
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\schoolmanagementsystem\\application.properties"));

            // Retrieve database credentials
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            // Load the database driver and establish the connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection successful.");
        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Automatically generated method to initialize all components in the UI.
     * Includes labels, buttons, panels, and other GUI elements.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Create and configure UI components
        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtuname = new javax.swing.JTextField();
        txtutype = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        closebutton = new javax.swing.JButton();
        txtpass = new javax.swing.JPasswordField();
        submitbutton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        // Configure the window
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Main panel setup
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Sidebar panel setup
        jPanel2.setBackground(new java.awt.Color(0, 102, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // User image on the sidebar
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // User image
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 161, 213, 199));

        // Welcome message
        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36));
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Welcome User!");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 105, -1, -1));

        // Developer credits
        jLabel9.setForeground(new java.awt.Color(204, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Developed by");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 457, 91, -1));

        // Developer name link
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Lindsay Blood");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt); // Open developer link
            }
        });
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 479, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 520));

        // Application title
        jLabel3.setFont(new java.awt.Font("Calibri", 1, 36));
        jLabel3.setForeground(new java.awt.Color(51, 153, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("School Management System");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 19, -1, 50));

        // Page title
        jLabel4.setFont(new java.awt.Font("Calibri", 1, 36));
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Login Page");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 75, 177, 50));

        // Username input
        txtuname.setFont(new java.awt.Font("Calibri", 0, 18));
        txtuname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel1.add(txtuname, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 166, 263, 31));

        // User type dropdown
        txtutype.setFont(new java.awt.Font("Calibri", 0, 18));
        txtutype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Admin", "Teacher", "Student" }));
        txtutype.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtutype.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(txtutype, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 334, 263, 35));

        // Password input
        txtpass.setFont(new java.awt.Font("Calibri", 1, 18));
        txtpass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel1.add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 250, 263, 31));

        // Submit button
        submitbutton.setBackground(new java.awt.Color(51, 153, 255));
        submitbutton.setFont(new java.awt.Font("Calibri", 1, 24));
        submitbutton.setForeground(new java.awt.Color(255, 255, 255));
        submitbutton.setText("SUBMIT");
        submitbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitbutton.addActionListener(this::submitbuttonActionPerformed);
        jPanel1.add(submitbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(558, 387, 94, 40));

        // Close button
        closebutton.setBackground(new java.awt.Color(51, 153, 255));
        closebutton.setFont(new java.awt.Font("Calibri", 1, 24));
        closebutton.setForeground(new java.awt.Color(255, 255, 255));
        closebutton.setText("CLOSE");
        closebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closebutton.addActionListener(this::closebuttonActionPerformed);
        jPanel1.add(closebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 387, 88, 40));

        // Menus
        jMenu2.setText("About Project");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);
        setJMenuBar(jMenuBar1);

        // Set layout and pack components
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        pack();
        setLocationRelativeTo(null);
    }

// Action handler for the "Submit" button
private void submitbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    // Collect user input for username, password, and user type
    String username = txtuname.getText();
    String pass = txtpass.getText();
    String utype = txtutype.getSelectedItem().toString();

    try {
        // SQL query to validate login credentials
        pst = con.prepareStatement("select * from user where uname=? and password=? and utype=?");
        pst.setString(1, username);
        pst.setString(2, pass);
        pst.setString(3, utype);

        rs = pst.executeQuery();

        // Check if the credentials match a record
        if (rs.next()) {
            int id = rs.getInt("id"); // Get user ID
            this.setVisible(false); // Hide login form
            new Main(id, username, utype).setVisible(true); // Open the main dashboard
        } else {
            // Display error message and reset fields
            JOptionPane.showMessageDialog(this, "Username and password do not match.");
            txtuname.setText("");
            txtpass.setText("");
            txtutype.setSelectedIndex(0);
            txtuname.requestFocus();
        }
    } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                             

// Action handler for the "Close" button
private void closebuttonActionPerformed(java.awt.event.ActionEvent evt) {                                            
    this.setVisible(false); // Hide the login window
}                                           

// Action handler for the "About Project" menu
private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {                                    
    About a = new About(); // Open the "About" page
    a.setVisible(true);
    this.setVisible(false);
}                                   

// Action handler for the developer name link
private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {                                      
    try {
        // Open the developer's GitHub profile in the default web browser
        Desktop.getDesktop().browse(new URI("https://github.com/naveenkumar-j"));
    } catch (IOException | URISyntaxException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                     

// Action handler for the user type combo box
private void txtutypeActionPerformed(java.awt.event.ActionEvent evt) {                                         
    // Custom logic can be added if needed for specific user type selections
}                                        

// Set the application icon
private void setIconImage() {
    setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
}

/**
 * Main method to run the Login application.
 */
public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new Login().setVisible(true);
        }
    });
}
// Variable declarations for GUI components
private javax.swing.JButton closebutton; // Close button to exit the application
private javax.swing.JLabel jLabel1; // User icon label
private javax.swing.JLabel jLabel10; // Developer name clickable label
private javax.swing.JLabel jLabel11; // Username icon label
private javax.swing.JLabel jLabel12; // Password icon label
private javax.swing.JLabel jLabel13; // User type icon label
private javax.swing.JLabel jLabel2; // Welcome message label
private javax.swing.JLabel jLabel3; // Application title label
private javax.swing.JLabel jLabel4; // Login page title label
private javax.swing.JLabel jLabel5; // "Password" label
private javax.swing.JLabel jLabel6; // "Username" label
private javax.swing.JLabel jLabel7; // "User Type" label
private javax.swing.JLabel jLabel9; // "Developed by" label
private javax.swing.JMenu jMenu1; // Placeholder menu
private javax.swing.JMenu jMenu2; // "About Project" menu
private javax.swing.JMenu jMenu3; // Placeholder menu
private javax.swing.JMenuBar jMenuBar1; // Menu bar
private javax.swing.JPanel jPanel1; // Main panel
private javax.swing.JPanel jPanel2; // Side panel
private javax.swing.JButton submitbutton; // Submit button to login
private javax.swing.JPasswordField txtpass; // Password input field
private javax.swing.JTextField txtuname; // Username input field
private javax.swing.JComboBox<String> txtutype; // Dropdown for user type selection

// Database-related variables
private Connection con; // Database connection
private PreparedStatement pst; // PreparedStatement for SQL queries
private ResultSet rs; // ResultSet for database query results

}
