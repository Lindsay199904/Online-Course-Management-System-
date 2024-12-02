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
 * This class manages the User page, including adding, editing, deleting, and displaying users.
 *
 * @Lindsay M Blood
 */
public class User extends javax.swing.JFrame {

    /**
     * Constructor to initialize the User form and load existing users.
     */
    public User() {
        initComponents(); // Initializes UI components
        Connect();        // Establishes database connection
        User_Load();      // Loads user data into the table
        setIconImage();   // Sets the application icon
        setTitle("User page"); // Sets the title of the form
    }

    /**
     * Sets the application icon.
     */
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
    }

    // Variables to store the current user's information
    int id;              // Current user ID
    String uname;        // Current user's username
    String usertype;     // Current user's type (e.g., Admin, Student, Teacher)

    /**
     * Constructor to initialize the User form for a specific user.
     *
     * @param id       The ID of the current user.
     * @param username The username of the current user.
     * @param utype    The type of the current user.
     */
    public User(int id, String username, String utype) {
        setIconImage();
        setTitle("User page");

        initComponents(); // Initializes UI components
        Connect();        // Establishes database connection
        User_Load();      // Loads user data into the table

        // Set the current user's information
        this.uname = username;
        jLabel20.setText(uname); // Displays the username on the form

        this.usertype = utype;
        jLabel30.setText(utype); // Displays the user type on the form

        this.id = id;

        // Restrict actions based on user type
        if (utype.equals("Student") || utype.equals("Guest")) {
            savebutton.setEnabled(false);
            editbutton.setEnabled(false);
            deletebutton.setEnabled(false);
            clearbutton.setEnabled(false);
        } else {
            savebutton.setEnabled(true);
            editbutton.setEnabled(true);
            deletebutton.setEnabled(true);
            clearbutton.setEnabled(true);
        }
    }

    // Database-related variables
    Connection con;              // Database connection
    PreparedStatement pst;       // SQL PreparedStatement for executing queries
    ResultSet rs;                // ResultSet for holding query results
    DefaultTableModel d;         // Table model for displaying user data

    /**
     * Establishes a connection to the database.
     */
    public void Connect() {
        try {
            // Load properties file
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\schoolmanagementsystem\\application.properties"));

            // Get database connection details
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            // Load the database driver and establish a connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection successful.");
        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Loads user data from the database and displays it in the table.
     */
    public void User_Load() {
        int c; // Variable to store the column count
        try {
            // Execute SQL query to fetch all users
            pst = con.prepareStatement("select * from user");
            rs = pst.executeQuery();

            // Get metadata to determine the number of columns
            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();

            // Set the table model and clear existing data
            d = (DefaultTableModel) jTable1.getModel();
            d.setRowCount(0);

            // Loop through the ResultSet and add rows to the table
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("id"));       // User ID
                    v2.add(rs.getString("name"));     // User name
                    v2.add(rs.getString("phone"));    // User phone number
                    v2.add(rs.getString("address"));  // User address
                    v2.add(rs.getString("uname"));    // Username
                    v2.add(rs.getString("utype"));    // User type
                }
                d.addRow(v2); // Add the row to the table model
            }
        } catch (SQLException ex) {
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

        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtphone = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtaddress = new javax.swing.JTextField();
        txtuname = new javax.swing.JTextField();
        txtpass = new javax.swing.JPasswordField();
        txtutype = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        savebutton = new javax.swing.JButton();
        clearbutton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        editbutton = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("CREATE  ACCOUNT");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Create Account");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 317, 63));

        txtphone.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtphone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel2.add(txtphone, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 188, 263, 29));

        txtname.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel2.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 108, 264, 29));

        txtaddress.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtaddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel2.add(txtaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 262, 263, 29));

        txtuname.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtuname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel2.add(txtuname, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 342, 263, 29));

        txtpass.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtpass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 54, 54)));
        txtpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassActionPerformed(evt);
            }
        });
        jPanel2.add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 422, 263, 29));

        txtutype.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtutype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Admin", "Teacher", "Student", "Guest" }));
        txtutype.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 54, 54)));
        txtutype.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(txtutype, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 502, 263, 33));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Phone Number");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 155, 116, 27));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Name");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 75, 61, 27));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Address");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 229, 82, 27));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Username");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 309, 92, 27));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("User Type");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 469, 92, 27));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Password");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 389, 92, 27));

        savebutton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        savebutton.setText("SAVE");
        savebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        savebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(savebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, -1, 40));

        clearbutton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        clearbutton.setText("CLEAR");
        clearbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(clearbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 580, -1, 40));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Already Have account? Login here");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 638, 215, 34));

        editbutton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        editbutton.setText("EDIT");
        editbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(editbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 580, -1, 40));

        deletebutton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        deletebutton.setText("DELETE");
        deletebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 580, -1, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 365, 793));

        jLabel12.setText("Developed by:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(581, 771, -1, -1));

        jLabel13.setText("Naveenkumar J");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(664, 771, 90, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
        jLabel10.setMaximumSize(new java.awt.Dimension(30, 36));
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, 320, 180));

        jLabel11.setText("Developed by:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(933, 659, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Lindsay Blood");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.setName(""); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1016, 659, 90, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("USER TYPE: ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("USER TYPE ");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("LOGOUT");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, -1, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("USERNAME ");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 20, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("USERNAME: ");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 67, -1, 410));

        jMenu1.setText("Main Menu");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("About Project");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

   private void txtpassActionPerformed(java.awt.event.ActionEvent evt) {                                        
    // Action performed when the password text field receives an action event.
    // Currently, no custom logic is implemented.
}                                       

private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {                                     
    // Redirect to the Login screen when the label is clicked.
    Login l = new Login();
    l.setVisible(true);
    this.setVisible(false);
}                                    

private void savebuttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    // Action performed when the save button is clicked.
    try {
        // Retrieve user input from text fields and combo box.
        String name = txtname.getText();
        String phone = txtphone.getText();
        String address = txtaddress.getText();
        String uname = txtuname.getText();
        String password = txtpass.getText();
        String utype = txtutype.getSelectedItem().toString();

        // Insert user details into the database.
        pst = con.prepareStatement("insert into user(name,phone,address,uname,password,utype)values(?,?,?,?,?,?)");
        pst.setString(1, name);
        pst.setString(2, phone);
        pst.setString(3, address);
        pst.setString(4, uname);
        pst.setString(5, password);
        pst.setString(6, utype);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(this, "User added successfully...");

        // Clear the input fields and reset focus.
        txtname.setText("");
        txtphone.setText("");
        txtaddress.setText("");
        txtuname.setText("");
        txtpass.setText("");
        txtutype.setSelectedIndex(0);
        txtname.requestFocus();

        // Reload user data to reflect changes.
        User_Load();

    } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                          

private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    // Action performed when the edit button is clicked.
    try {
        // Retrieve selected user ID from the table.
        d = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        String id = d.getValueAt(selectIndex, 0).toString();

        // Retrieve updated user details from input fields.
        String name = txtname.getText();
        String phone = txtphone.getText();
        String address = txtaddress.getText();
        String uname = txtuname.getText();
        String password = txtpass.getText();
        String utype = txtutype.getSelectedItem().toString();

        // Update user details in the database.
        pst = con.prepareStatement("update user set name=?,phone=?,address=?,uname=?,utype=? where id=?");
        pst.setString(1, name);
        pst.setString(2, phone);
        pst.setString(3, address);
        pst.setString(4, uname);
        pst.setString(5, utype);
        pst.setString(6, id);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(this, "User details edited successfully...");
        savebutton.setEnabled(true);

        // Clear the input fields and reset focus.
        txtname.setText("");
        txtphone.setText("");
        txtaddress.setText("");
        txtuname.setText("");
        txtpass.setText("");
        txtutype.setSelectedIndex(0);
        txtname.requestFocus();

        // Reload user data to reflect changes.
        User_Load();

    } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                          

private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    // Action performed when the delete button is clicked.
    try {
        // Retrieve selected user ID from the table.
        d = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        String id = d.getValueAt(selectIndex, 0).toString();

        // Delete user from the database.
        pst = con.prepareStatement("delete from user where id=?");
        pst.setString(1, id);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(this, "User details deleted successfully...");
        savebutton.setEnabled(true);

        // Clear the input fields and reset focus.
        txtname.setText("");
        txtphone.setText("");
        txtaddress.setText("");
        txtuname.setText("");
        txtpass.setText("");
        txtutype.setSelectedIndex(0);
        txtname.requestFocus();

        // Reload user data to reflect changes.
        User_Load();

    } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                            

private void clearbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                            
    // Action performed when the clear button is clicked.
    // Clear all input fields and reset the form.
    txtname.setText("");
    txtphone.setText("");
    txtaddress.setText("");
    txtuname.setText("");
    txtpass.setText("");
    txtutype.setSelectedIndex(0);
    txtname.requestFocus();
    savebutton.setVisible(true);
}                                           

private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {                                      
    // Redirect to a GitHub profile when the label is clicked.
    try {
        Desktop.getDesktop().browse(new URI("https://github.com/naveenkumar-j"));
    } catch (IOException | URISyntaxException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                     

private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
    // Populate input fields with data from the selected table row.
    d = (DefaultTableModel) jTable1.getModel();
    int selectIndex = jTable1.getSelectedRow();
    txtname.setText(d.getValueAt(selectIndex, 1).toString());
    txtphone.setText(d.getValueAt(selectIndex, 2).toString());
    txtaddress.setText(d.getValueAt(selectIndex, 3).toString());
    txtuname.setText(d.getValueAt(selectIndex, 4).toString());
    txtutype.setSelectedItem(d.getValueAt(selectIndex, 5).toString());
    savebutton.setEnabled(false);
}                                    

private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {                                      
    // Redirect to a GitHub profile when the label is clicked.
    try {
        Desktop.getDesktop().browse(new URI("https://github.com/naveenkumar-j"));
    } catch (IOException | URISyntaxException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                     

private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {                                    
    // Redirect to the Main menu when the menu item is clicked.
    Main m = new Main(id, uname, usertype);
    m.setVisible(true);
    this.setVisible(false);
}                                   

private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {                                    
    // Redirect to the About page when the menu item is clicked.
    About a = new About();
    a.setVisible(true);
    this.setVisible(false);
}                                   

private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {                                      
    // Redirect to the Login screen when the label is clicked.
    Login l = new Login();
    l.setVisible(true);
    this.setVisible(false);
}                                     

  /**
 * The main method serves as the entry point for the application.
 *
 * @param args the command line arguments (not used in this application)
 */
public static void main(String args[]) {
    // Set the Nimbus look and feel for the application's user interface
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details, see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
        // Iterate through the installed look-and-feels and set the UI to Nimbus if available
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        // Log an error if the Nimbus class is not found
        java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        // Log an error if the Nimbus look-and-feel class cannot be instantiated
        java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        // Log an error if the application does not have access to the Nimbus class
        java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        // Log an error if the Nimbus look-and-feel is not supported
        java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    // Create and display the main form (User interface)
    java.awt.EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            new User().setVisible(true); // Instantiate the User form and make it visible
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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton savebutton;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtname;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtphone;
    private javax.swing.JTextField txtuname;
    private javax.swing.JComboBox<String> txtutype;
    // End of variables declaration                   
}
