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
 *
 * @lindsay blood
 */
public class Mark extends javax.swing.JFrame {

    /**
     * Creates new form Mark
     */
    public Mark() {
        initComponents(); // Initialize UI components
        Connect(); // Establish database connection
        Subject_Load(); // Load subjects into the dropdown
        Examterm_Load(); // Load exam terms into the dropdown
        Mark_Load(); // Load marks data into the table
        Class_Load(); // Load classes into the dropdown
        setIconImage(); // Set the application icon
        setTitle("Mark page"); // Set the window title
    }

    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png"))); // Set application icon
    }

    int id; // User ID
    String uname; // Username
    String usertype; // User type (e.g., Admin, Teacher, Student)

    /**
     * Constructor with user details to manage role-based access control
     */
    public Mark(int id, String username, String utype) {
        setIconImage(); // Set application icon
        setTitle("Mark page"); // Set window title
        initComponents(); // Initialize UI components
        Connect(); // Establish database connection
        Subject_Load(); // Load subjects into the dropdown
        Examterm_Load(); // Load exam terms into the dropdown
        Mark_Load(); // Load marks data into the table
        Class_Load(); // Load classes into the dropdown

        this.uname = username;
        jLabel20.setText(uname); // Display username in UI

        this.usertype = utype;
        jLabel30.setText(utype); // Display user type in UI

        this.id = id;

        // Disable buttons and fields for "Student" and "Guest" roles
        if (utype.equals("Student") || utype.equals("Guest")) {
            savebutton.setEnabled(false); // Disable save button
            editbutton.setEnabled(false); // Disable edit button
            deletebutton.setEnabled(false); // Disable delete button
            clearbutton.setEnabled(false); // Disable clear button

            // Disable input fields to prevent manual edits
            txtid.setEditable(false);
            txtmark.setEditable(false);
            txtterm.setEnabled(false);
            txtclass.setEnabled(false);
            txtsubject.setEnabled(false);

            // Show a read-only access message
            JOptionPane.showMessageDialog(this, "You have read-only access to this page.");
        } else {
            // Enable buttons for privileged users (e.g., Admin, Teacher)
            savebutton.setEnabled(true);
            editbutton.setEnabled(true);
            deletebutton.setEnabled(true);
            clearbutton.setEnabled(true);
        }
    }

    Connection con; // Database connection object
    PreparedStatement pst; // Statement object for executing SQL queries
    ResultSet rs; // Result set for storing query results
    DefaultTableModel d; // Table model for displaying data in the UI

    /**
     * Method to establish a database connection
     */
    public void Connect() {
        try {
            // Load properties file containing database configurations
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\schoolmanagementsystem\\application.properties"));

            // Retrieve database connection details from properties
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            // Load the JDBC driver and connect to the database
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
     * Method to load subjects into the dropdown
     */
    public void Subject_Load() {
        try {
            pst = con.prepareStatement("SELECT DISTINCT subjectname FROM subject");
            rs = pst.executeQuery();

            // Populate subject dropdown
            while (rs.next()) {
                txtsubject.addItem(rs.getString("subjectname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method to load exam terms into the dropdown
     */
    public void Examterm_Load() {
        try {
            pst = con.prepareStatement("SELECT DISTINCT examterm FROM exam");
            rs = pst.executeQuery();

            // Populate exam term dropdown
            while (rs.next()) {
                txtterm.addItem(rs.getString("examterm"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method to load classes into the dropdown
     */
    public void Class_Load() {
        try {
            pst = con.prepareStatement("SELECT DISTINCT classname FROM class");
            rs = pst.executeQuery();

            // Populate class dropdown
            while (rs.next()) {
                txtclass.addItem(rs.getString("classname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method to load marks into the table
     */
    public void Mark_Load() {
        int c; // Number of columns in the result set
        try {
            pst = con.prepareStatement("SELECT * FROM mark"); // Query to fetch all marks
            rs = pst.executeQuery();

            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();

            // Initialize the table model and clear existing data
            d = (DefaultTableModel) jTable1.getModel();
            d.setRowCount(0);

            // Populate table rows with marks data
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("studentid")); // Add student ID
                    v2.add(rs.getString("studentname")); // Add student name
                    v2.add(rs.getString("subject")); // Add subject
                    v2.add(rs.getString("class")); // Add class
                    v2.add(rs.getString("marks")); // Add marks
                    v2.add(rs.getString("term")); // Add exam term
                }
                d.addRow(v2); // Add the row to the table
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtmark = new javax.swing.JTextField();
        txtterm = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtsubject = new javax.swing.JComboBox<>();
        txtclass = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JLabel();
        clearbutton = new javax.swing.JButton();
        savebutton = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        editbutton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new rojerusan.RSTableMetro();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mark Details:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        txtmark.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel2.add(txtmark, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 240, 30));

        txtterm.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtterm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jPanel2.add(txtterm, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 240, 30));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Term");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, -1, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Student ID");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Student Name");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Subject");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Class");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Marks");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, -1));

        txtid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel2.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 240, 30));

        txtname.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel2.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 240, 30));

        txtsubject.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtsubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jPanel2.add(txtsubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 240, 30));

        txtclass.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtclass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jPanel2.add(txtclass, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 240, 30));

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });
        jPanel2.add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 30, 30));

        clearbutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearbutton.setText("CLEAR");
        clearbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(clearbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 520, 80, 40));

        savebutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        savebutton.setText("SAVE");
        savebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        savebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(savebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 80, 40));

        deletebutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deletebutton.setText("DELETE");
        deletebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 520, 80, 40));

        editbutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editbutton.setText("EDIT");
        editbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(editbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, 80, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mark.png"))); // NOI18N
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 600));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("USER TYPE:");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 90, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("LOGOUT");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 60, 30));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("USERNAME");
        jLabel30.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 90, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("USERNAME:");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 90, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("USERNAME");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 90, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Subject", "Class", "Marks", "Term"
            }
        ));
        jTable1.setColorBackgoundHead(new java.awt.Color(51, 153, 255));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 500, 260));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Lindsay Blood");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 570, -1, -1));

        jLabel7.setText("Developed by:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 570, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 600));

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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        Login l = new Login();
        l.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Desktop.getDesktop().browse(new URI("https://github.com/naveenkumar-j"));
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
        About a = new About();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        Main m = new Main(id, uname, usertype);
        m.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenu1MouseClicked
	
private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {                                          
    try {
        // Get the entered Student ID
        String sid = txtid.getText().trim();

        if (sid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student ID");
            return;
        }

        // Prepare the SQL query
        pst = con.prepareStatement("SELECT * FROM student WHERE studentid = ?");
        pst.setString(1, sid);
        rs = pst.executeQuery();

        // Check if a record is found
        if (!rs.next()) {
            JOptionPane.showMessageDialog(this, "Student NOT FOUND");
            txtname.setText("");
            txtclass.setSelectedIndex(-1); // Reset dropdown
        } else {
            // Populate fields with the data from the database
            String name = rs.getString("studentname");
            String classname = rs.getString("studentclass");

            txtname.setText(name.trim());
            txtclass.setSelectedItem(classname.trim()); // Ensure classname exists in dropdown
        }
    } catch (SQLException ex) {
        Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error fetching student details: " + ex.getMessage());
    }
}  
                                         

    private void savebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebuttonActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            String id = txtid.getText();
            String name = txtname.getText();
            String subject = txtsubject.getSelectedItem().toString();
            String classname = txtclass.getSelectedItem().toString();
            String mark = txtmark.getText();
            String term = txtterm.getSelectedItem().toString();

            pst = con.prepareStatement("insert into mark(studentid,studentname,subject,class,marks,term)values(?,?,?,?,?,?)");
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, subject);
            pst.setString(4, classname);
            pst.setString(5, mark);
            pst.setString(6, term);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Marks details added successfully...");

            txtid.setText("");
            txtname.setText("");
            txtsubject.setSelectedIndex(-1);
            txtclass.setSelectedIndex(-1);
            txtmark.setText("");
            txtterm.setSelectedIndex(-1);

            txtid.requestFocus();

            Mark_Load();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_savebuttonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        d = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        // String id = d.getValueAt(selectIndex, 0).toString();
        txtid.setText(d.getValueAt(selectIndex, 0).toString());
        txtname.setText(d.getValueAt(selectIndex, 1).toString());
        txtsubject.setSelectedItem(d.getValueAt(selectIndex, 2).toString());
        txtclass.setSelectedItem(d.getValueAt(selectIndex, 3).toString());
        txtmark.setText(d.getValueAt(selectIndex, 4).toString());
        txtterm.setSelectedItem(d.getValueAt(selectIndex, 5).toString());
        savebutton.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void clearbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbuttonActionPerformed
        // TODO add your handling code here:
        txtid.setText("");
        txtname.setText("");
        txtsubject.setSelectedIndex(-1);
        txtclass.setSelectedIndex(-1);
        txtmark.setText("");
        txtterm.setSelectedIndex(-1);

        txtid.requestFocus();

        Mark_Load();
        savebutton.setEnabled(true);

    }//GEN-LAST:event_clearbuttonActionPerformed

    private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbuttonActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            d = (DefaultTableModel) jTable1.getModel();
            int selectIndex = jTable1.getSelectedRow();

            String id = txtid.getText();
            String name = txtname.getText();
            String subject = txtsubject.getSelectedItem().toString();
            String classname = txtclass.getSelectedItem().toString();
            String mark = txtmark.getText();
            String term = txtterm.getSelectedItem().toString();

            pst = con.prepareStatement("update mark set studentid=?,studentname=?,subject=?,class=?,marks=?,term=? where studentid=?");
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, subject);
            pst.setString(4, classname);
            pst.setString(5, mark);
            pst.setString(6, term);
            pst.setString(7, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Mark details edited successfully...");
            savebutton.setEnabled(true);

            txtid.setText("");
            txtname.setText("");
            txtsubject.setSelectedIndex(-1);
            txtclass.setSelectedIndex(-1);
            txtmark.setText("");
            txtterm.setSelectedIndex(-1);

            txtid.requestFocus();

            Mark_Load();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editbuttonActionPerformed

    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebuttonActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            d = (DefaultTableModel) jTable1.getModel();
            int selectIndex = jTable1.getSelectedRow();

            String id = d.getValueAt(selectIndex, 0).toString();

            pst = con.prepareStatement("delete from mark where studentid=?");
            pst.setString(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Mark details deleted successfully...");
            savebutton.setEnabled(true);
            txtid.setText("");
            txtname.setText("");
            txtsubject.setSelectedIndex(-1);
            txtclass.setSelectedIndex(-1);
            txtmark.setText("");
            txtterm.setSelectedIndex(-1);

            txtid.requestFocus();

            Mark_Load();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deletebuttonActionPerformed

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(Mark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mark().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearbutton;
    private javax.swing.JButton deletebutton;
    private javax.swing.JButton editbutton;
    private javax.swing.JDesktopPane jDesktopPane1;
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
    private javax.swing.JLabel searchButton;
    private javax.swing.JComboBox<String> txtclass;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtmark;
    private javax.swing.JTextField txtname;
    private javax.swing.JComboBox<String> txtsubject;
    private javax.swing.JComboBox<String> txtterm;
    // End of variables declaration//GEN-END:variables
}
