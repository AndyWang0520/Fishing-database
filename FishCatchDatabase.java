import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class FishCatchDatabase extends JFrame {
    
    JTextField txtLocation;
    JTextField txtNumberOfFish;
    
    Connection con;
    Statement stmt;
    
    public FishCatchDatabase() {
        super("Fish Catch Database");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        txtLocation = new JTextField(20);
        txtNumberOfFish = new JTextField(20);
        
        add(new JLabel("Location: "));
        add(txtLocation);
        add(new JLabel("Number of Fish: "));
        add(txtNumberOfFish);
        
        JButton btnAddData = new JButton("Add Data");
        btnAddData.addActionListener(e -> addData());
        add(btnAddData);
        
        JButton btnShowData = new JButton("Show Data");
        btnShowData.addActionListener(e -> showData());
        add(btnShowData);
        
        connectToDB();
        
        setVisible(true);
    }
    
    public void connectToDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fish_catch_db", "root", "");
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
    
    public void addData() {
        try {
            String query = "INSERT INTO fish_catch (location, number_of_fish) VALUES ('" + txtLocation.getText() + "', " + txtNumberOfFish.getText() + ")";
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data added successfully!");
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
    
    public void showData() {
        try {
            String query = "SELECT * FROM fish_catch";
            ResultSet rs = stmt.executeQuery(query);
            String data = "Location\tNumber of Fish\n";
            while (rs.next()) {
                data += rs.getString("location") + "\t" + rs.getString("number_of_fish") + "\n";
            }
            JOptionPane.showMessageDialog(null, data);
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
    
    public static void main(String args[]) {
        new FishCatchDatabase();
    }
   
}