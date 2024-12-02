/**
 * Default constructor for the Subject class.
 * Initializes the Subject page and loads subject data.
 */
public Subject() {
    initComponents(); // Initialize GUI components
    Connect(); // Establish connection to the database
    Subject_Load(); // Load subjects from the database
    setIconImage(); // Set the window icon
    setTitle("Subject page"); // Set the window title
}

/**
 * Sets the application icon.
 */
private void setIconImage() {
    setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
}

// Variables to store user-specific data
int id; // User ID
String uname; // Username
String usertype; // User type (e.g., Admin, Student, Guest)

/**
 * Overloaded constructor for the Subject class.
 * Initializes the Subject page with user details and permissions.
 * 
 * @param id        The user ID.
 * @param username  The username of the logged-in user.
 * @param utype     The user type (e.g., Admin, Student, Guest).
 */
public Subject(int id, String username, String utype) {
    setIconImage(); // Set the window icon
    setTitle("Subject page"); // Set the window title
    initComponents(); // Initialize GUI components
    Connect(); // Establish connection to the database
    Subject_Load(); // Load subjects from the database
    this.uname = username; // Set the username
    jLabel20.setText(uname); // Display the username in the GUI

    this.usertype = utype; // Set the user type
    jLabel30.setText(utype); // Display the user type in the GUI

    this.id = id; // Set the user ID

    // Adjust button permissions based on user type
    if (utype.equals("Student")) {
        savebutton.setEnabled(false);
        editbutton.setEnabled(false);
        deletebutton.setEnabled(false);
        clearbutton.setEnabled(false);
    }

    if (utype.equals("Guest")) {
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

// Variables for database connectivity
Connection con; // Database connection object
PreparedStatement pst; // PreparedStatement for SQL queries
ResultSet rs; // ResultSet to store query results
DefaultTableModel d; // DefaultTableModel for populating tables

/**
 * Establishes a connection to the database using credentials from a properties file.
 */
public void Connect() {
    try {
        // Load properties file containing database credentials
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\schoolmanagementsystem\\application.properties"));

        // Extract database details from the properties file
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        // Load the MySQL database driver and establish a connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);
        System.out.println("Database connection successful.");
    } catch (IOException e) {
        // Handle errors in loading the properties file
        System.out.println("Error loading properties file: " + e.getMessage());
    } catch (SQLException ex) {
        // Log SQL-related exceptions
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        // Log errors related to the database driver
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
}

/**
 * Loads subject data from the database and populates the table in the GUI.
 */
public void Subject_Load() {
    int c; // Variable to store the number of columns in the ResultSet
    try {
        // Execute query to fetch all subject records
        pst = con.prepareStatement("select * from subject");
        rs = pst.executeQuery();

        // Get metadata to determine the number of columns
        ResultSetMetaData rsd = rs.getMetaData();
        c = rsd.getColumnCount();

        // Clear the existing rows in the table model
        d = (DefaultTableModel) jTable1.getModel();
        d.setRowCount(0);

        // Loop through the ResultSet and add each row to the table model
        while (rs.next()) {
            Vector v2 = new Vector();
            for (int i = 1; i <= c; i++) {
                v2.add(rs.getString("id")); // Add the subject ID
                v2.add(rs.getString("subjectcode")); // Add the subject code
                v2.add(rs.getString("subjectname")); // Add the subject name
            }
            d.addRow(v2); // Add the row to the table model
        }
    } catch (SQLException ex) {
        // Log SQL-related exceptions
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new rojerusan.RSTableMetro();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtsubjectcode = new javax.swing.JTextField();
        txtsubject = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        savebutton = new javax.swing.JButton();
        editbutton = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        clearbutton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Code", "Name"
            }
        ));
        jTable1.setColorBackgoundHead(new java.awt.Color(51, 153, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel4.setText("Developed by:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Lindsay Blood");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("LOGOUT");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("USERNAME:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("USER TYPE:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("USERNAME");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("USERTYPE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(368, 368, 368)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(28, 28, 28))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(130, 130, 130))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 0, 550, 430));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setForeground(new java.awt.Color(51, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Subject Details:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 293, -1));

        txtsubjectcode.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtsubjectcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel2.add(txtsubjectcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 128, 269, 31));

        txtsubject.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtsubject.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel2.add(txtsubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 212, 269, 33));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Subject Code");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 99, 143, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Subject Name");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 183, 162, -1));

        savebutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        savebutton.setText("SAVE");
        savebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        savebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(savebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 280, 98, 41));

        editbutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editbutton.setText("EDIT");
        editbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(editbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 280, 97, 41));

        deletebutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deletebutton.setText("DELETE");
        deletebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 357, 98, 40));

        clearbutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearbutton.setText("CLEAR");
        clearbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(clearbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 357, 94, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/book.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 50, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 430));

        jMenu1.setText("Main Menu");
        jMenu1.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jMenu1MenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

  private void savebuttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    // Handle the save button action
    try {
        // Get the input values for subject code and subject name
        String code = txtsubjectcode.getText();
        String subject = txtsubject.getText();

        // Prepare the SQL statement to insert subject details
        pst = con.prepareStatement("insert into subject(subjectcode,subjectname)values(?,?)");
        pst.setString(1, code); // Set subject code
        pst.setString(2, subject); // Set subject name

        // Execute the SQL statement
        pst.executeUpdate();
        JOptionPane.showMessageDialog(this, "Subject details added successfully...");

        // Reload the subject table to reflect the new entry
        Subject_Load();
    } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                          

private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    // Handle the edit button action
    try {
        // Get the selected row and the ID of the subject
        d = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        String id = d.getValueAt(selectIndex, 0).toString();

        // Get the updated values for subject code and subject name
        String code = txtsubjectcode.getText();
        String subject = txtsubject.getText();

        // Prepare the SQL statement to update subject details
        pst = con.prepareStatement("update subject set subjectcode=?,subjectname=? where id=?");
        pst.setString(1, code); // Set subject code
        pst.setString(2, subject); // Set subject name
        pst.setString(3, id); // Set subject ID

        // Execute the SQL statement
        pst.executeUpdate();
        JOptionPane.showMessageDialog(this, "Subject details edited successfully...");

        // Enable save button and clear input fields
        savebutton.setEnabled(true);
        txtsubject.setText("");
        txtsubjectcode.setText("");
        txtsubjectcode.requestFocus();

        // Reload the subject table to reflect the updated entry
        Subject_Load();
    } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                          

private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    // Handle the delete button action
    try {
        // Get the selected row and the ID of the subject
        d = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        String id = d.getValueAt(selectIndex, 0).toString();

        // Prepare the SQL statement to delete the subject
        pst = con.prepareStatement("delete from subject where id=?");
        pst.setString(1, id); // Set subject ID

        // Execute the SQL statement
        pst.executeUpdate();
        JOptionPane.showMessageDialog(this, "Subject details deleted successfully...");

        // Enable save button and clear input fields
        savebutton.setEnabled(true);
        txtsubjectcode.setText("");
        txtsubject.setText("");

        // Reload the subject table to reflect the deleted entry
        Subject_Load();
    } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                            

private void clearbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                            
    // Clear all input fields and enable save button
    txtsubjectcode.setText("");
    txtsubject.setText("");
    savebutton.setEnabled(true);
}                                           

private void jMenu1MenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {                                      
    // Placeholder for menu key pressed event
}                                     

private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {                                    
    // Navigate to the main page
    Main m = new Main(id, uname, usertype);
    m.setVisible(true);
    this.setVisible(false);
}                                   

private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {                                    
    // Navigate to the About page
    About a = new About();
    a.setVisible(true);
    this.setVisible(false);
}                                   

private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
    // Handle table row click to populate input fields
    d = (DefaultTableModel) jTable1.getModel();
    int selectIndex = jTable1.getSelectedRow();

    // Populate input fields with selected row data
    String id = d.getValueAt(selectIndex, 0).toString();
    txtsubjectcode.setText(d.getValueAt(selectIndex, 1).toString());
    txtsubject.setText(d.getValueAt(selectIndex, 2).toString());

    // Disable save button while editing
    savebutton.setEnabled(false);
}                                    

private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {                                     
    // Open a GitHub page in the default web browser
    try {
        Desktop.getDesktop().browse(new URI("https://github.com/naveenkumar-j"));
    } catch (IOException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    } catch (URISyntaxException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
}                                    

private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {                                     
    // Navigate to the Login page
    Login l = new Login();
    l.setVisible(true);
    this.setVisible(false);
}                                    

/**
 * Main method to run the application.
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
        java.util.logging.Logger.getLogger(Subject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(Subject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(Subject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(Subject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new Subject().setVisible(true);
        }
    });
}

    // Variables declaration - do not modify                     
    private javax.swing.JButton clearbutton;
    private javax.swing.JButton deletebutton;
    private javax.swing.JButton editbutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTextField txtsubject;
    private javax.swing.JTextField txtsubjectcode;
    // End of variables declaration                   
}
