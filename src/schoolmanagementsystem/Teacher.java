/**
 * Connects to the database and fetches teacher information. 
 * Initializes GUI components and sets up the teacher page.
 */
public class Teacher extends javax.swing.JFrame {

    /**
     * Default constructor for the Teacher class.
     * Initializes the form and loads teacher data.
     */
    public Teacher() {
        initComponents(); // Initialize GUI components
        Connect(); // Establish a database connection
        Teacher_Load(); // Load teacher details into the table
        setIconImage(); // Set the application icon
        setTitle("Teacher page"); // Set the window title
    }

    /**
     * Sets the application icon from a resource file.
     */
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
    }

    int id; // Holds the ID of the current user
    String uname; // Stores the username
    String usertype; // Stores the type of user (Admin, Teacher, etc.)

    /**
     * Constructor to initialize the Teacher form with user details.
     * @param id User ID
     * @param username Username of the logged-in user
     * @param utype Type of user (Admin, Guest, etc.)
     */
    public Teacher(int id, String username, String utype) {
        setIconImage(); // Set the application icon
        setTitle("Teacher page"); // Set the window title
        initComponents(); // Initialize GUI components
        Connect(); // Establish a database connection
        Teacher_Load(); // Load teacher details into the table

        this.uname = username; // Set the username
        jLabel20.setText(uname); // Display the username on the form

        this.usertype = utype; // Set the user type
        jLabel30.setText(utype); // Display the user type on the form

        this.id = id; // Store the user ID

        // Disable certain features based on user type
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

    // Database connection objects
    Connection con; // Connection object
    PreparedStatement pst; // For executing SQL queries
    ResultSet rs; // To hold query results
    DefaultTableModel d; // Table model for displaying data

    /**
     * Establishes a connection to the database using details from a properties file.
     */
    public void Connect() {
        try {
            // Load the properties file containing database credentials
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\schoolmanagementsystem\\application.properties"));

            // Extract database connection details
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
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
     * Loads teacher information from the database into the table.
     */
    public void Teacher_Load() {
        int c; // Holds the number of columns in the result set
        try {
            // Prepare the SQL query to fetch teacher details
            pst = con.prepareStatement("select * from teacher");
            rs = pst.executeQuery(); // Execute the query

            // Get metadata about the result set
            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount(); // Get the number of columns

            // Get the table model for the teacher table
            d = (DefaultTableModel) jTable1.getModel();
            d.setRowCount(0); // Clear existing rows in the table

            // Populate the table with data from the result set
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("id")); // Teacher ID
                    v2.add(rs.getString("name")); // Teacher name
                    v2.add(rs.getString("qualification")); // Teacher qualification
                    v2.add(rs.getString("salary")); // Teacher salary
                    v2.add(rs.getString("phone")); // Teacher phone number
                    v2.add(rs.getString("email")); // Teacher email address
                    v2.add(rs.getString("address")); // Teacher address
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtname = new javax.swing.JTextField();
        txtqualification = new javax.swing.JTextField();
        txtsalary = new javax.swing.JTextField();
        txtmobile = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtaddress = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        editbutton = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        savebutton = new javax.swing.JButton();
        clearbutton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new rojerusan.RSTableMetro();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 240, 30));
        jPanel2.add(txtqualification, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 240, 30));
        jPanel2.add(txtsalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 240, 30));
        jPanel2.add(txtmobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 240, 30));
        jPanel2.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 240, 30));
        jPanel2.add(txtaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 240, 30));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Teacher Details:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Address");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 150, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Name");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 70, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Qualification");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 110, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Salary");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 110, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mobile Number");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 150, -1));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Email");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 150, -1));

        editbutton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        editbutton.setText("EDIT");
        editbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(editbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 530, 90, 40));

        deletebutton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        deletebutton.setText("DELETE");
        deletebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 600, 90, 40));

        savebutton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        savebutton.setText("SAVE");
        savebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        savebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(savebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, 90, 40));

        clearbutton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        clearbutton.setText("CLEAR");
        clearbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(clearbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 600, 90, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/teacher.png"))); // NOI18N
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 670));

        jButton4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton4.setText("SAVE");
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 600, 90, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("USER TYPE:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("USER TYPE");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("LOGOUT");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, -1, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("USERNAME");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("USERNAME:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, 30, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Qualification", "Salary", "Mobile No", "Email", "Address"
            }
        ));
        jTable1.setColorBackgoundHead(new java.awt.Color(51, 153, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 570, 170));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Lindsay Blood");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 640, -1, -1));

        jLabel11.setText("Developed by:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 640, -1, -1));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

 private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {                                      
    // Log out the user and navigate to the Login page
    Login l = new Login();
    l.setVisible(true); // Show the Login form
    this.setVisible(false); // Hide the current form
}                                     

private void savebuttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    // Save teacher details into the database
    try {
        String name = txtname.getText(); // Get the teacher's name
        String qualification = txtqualification.getText(); // Get the qualification
        String salary = txtsalary.getText(); // Get the salary
        String mobile = txtmobile.getText(); // Get the mobile number
        String email = txtemail.getText(); // Get the email address
        String address = txtaddress.getText(); // Get the address

        // Prepare and execute the SQL insert statement
        pst = con.prepareStatement("insert into teacher(name,qualification,salary,phone,email,address)values(?,?,?,?,?,?)");
        pst.setString(1, name);
        pst.setString(2, qualification);
        pst.setString(3, salary);
        pst.setString(4, mobile);
        pst.setString(5, email);
        pst.setString(6, address);
        pst.executeUpdate(); // Execute the query

        // Show success message
        JOptionPane.showMessageDialog(this, "Teacher details added successfully...");

        // Clear input fields and reset focus
        txtname.setText("");
        txtqualification.setText("");
        txtsalary.setText("");
        txtmobile.setText("");
        txtemail.setText("");
        txtaddress.setText("");
        txtname.requestFocus();

        // Reload the teacher data into the table
        Teacher_Load();
    } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                          

private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    // Edit teacher details in the database
    try {
        d = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow(); // Get the selected row index
        String id = d.getValueAt(selectIndex, 0).toString(); // Get the ID of the selected teacher

        // Get updated values from input fields
        String name = txtname.getText();
        String qualification = txtqualification.getText();
        String salary = txtsalary.getText();
        String mobile = txtmobile.getText();
        String email = txtemail.getText();
        String address = txtaddress.getText();

        // Prepare and execute the SQL update statement
        pst = con.prepareStatement("update teacher set name=?,qualification=?,salary=?,phone=?,email=?,address=? where id=?");
        pst.setString(1, name);
        pst.setString(2, qualification);
        pst.setString(3, salary);
        pst.setString(4, mobile);
        pst.setString(5, email);
        pst.setString(6, address);
        pst.setString(7, id);
        pst.executeUpdate(); // Execute the query

        // Show success message
        JOptionPane.showMessageDialog(this, "Teacher details edited successfully...");
        savebutton.setEnabled(true);

        // Clear input fields and reset focus
        txtname.setText("");
        txtqualification.setText("");
        txtsalary.setText("");
        txtmobile.setText("");
        txtemail.setText("");
        txtaddress.setText("");
        txtname.requestFocus();

        // Reload the teacher data into the table
        Teacher_Load();
    } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                          

private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    // Delete teacher details from the database
    try {
        d = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow(); // Get the selected row index
        String id = d.getValueAt(selectIndex, 0).toString(); // Get the ID of the selected teacher

        // Prepare and execute the SQL delete statement
        pst = con.prepareStatement("delete from teacher where id=?");
        pst.setString(1, id);
        pst.executeUpdate(); // Execute the query

        // Show success message
        JOptionPane.showMessageDialog(this, "Teacher details deleted successfully...");
        savebutton.setEnabled(true);

        // Clear input fields
        txtname.setText("");
        txtqualification.setText("");
        txtsalary.setText("");
        txtmobile.setText("");
        txtemail.setText("");
        txtaddress.setText("");
        txtname.requestFocus();

        // Reload the teacher data into the table
        Teacher_Load();
    } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                            

private void clearbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                            
    // Clear all input fields
    txtname.setText("");
    txtqualification.setText("");
    txtsalary.setText("");
    txtmobile.setText("");
    txtemail.setText("");
    txtaddress.setText("");
    txtname.requestFocus(); // Set focus back to the name input field
}                                           

private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
    // Populate input fields with data from the selected row
    d = (DefaultTableModel) jTable1.getModel();
    int selectIndex = jTable1.getSelectedRow(); // Get the selected row index
    txtname.setText(d.getValueAt(selectIndex, 1).toString()); // Teacher name
    txtqualification.setText(d.getValueAt(selectIndex, 2).toString()); // Qualification
    txtsalary.setText(d.getValueAt(selectIndex, 3).toString()); // Salary
    txtmobile.setText(d.getValueAt(selectIndex, 4).toString()); // Mobile number
    txtemail.setText(d.getValueAt(selectIndex, 5).toString()); // Email
    txtaddress.setText(d.getValueAt(selectIndex, 6).toString()); // Address
    savebutton.setEnabled(false); // Disable the save button
}                                    

private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {                                    
    // Navigate to the main menu
    Main m = new Main(id, uname, usertype);
    m.setVisible(true); // Show the Main menu
    this.setVisible(false); // Hide the current form
}                                   

private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {                                    
    // Navigate to the About page
    About a = new About();
    a.setVisible(true); // Show the About form
    this.setVisible(false); // Hide the current form
}                                   

private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {                                     
    // Open the developer's GitHub page in the default browser
    try {
        Desktop.getDesktop().browse(new URI("https://github.com/naveenkumar-j"));
    } catch (IOException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    } catch (URISyntaxException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
}    
    
/**
 * Main method to launch the Teacher application.
 *
 * @param args Command-line arguments (not used in this application)
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
                break; // Use the Nimbus look and feel
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            new Teacher().setVisible(true); // Launch the Teacher page
        }
    });
}

    // Variables declaration - do not modify                     
    private javax.swing.JButton clearbutton;
    private javax.swing.JButton deletebutton;
    private javax.swing.JButton editbutton;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private rojerusan.RSTableMetro jTable1;
    private javax.swing.JButton savebutton;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtmobile;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtqualification;
    private javax.swing.JTextField txtsalary;
    // End of variables declaration                   
}
