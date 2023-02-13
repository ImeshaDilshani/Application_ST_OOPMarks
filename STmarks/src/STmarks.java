import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class STmarks {
    private JTextField textID;
    private JTextField textName;
    private JTextField textCA;
    private JTextField textPractical;
    private JTextField textTheory;
    private JButton viewAllMarksButton;
    private JButton saveButton;
    private JPanel STmarks;
    private JLabel ESM;

    Connection con;
    PreparedStatement pst;

    public STmarks() {

        //code for save btn
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //first get student id, student name, CA , practical , theory enter by user
                String StudentID = textID.getText();
                String StudentName = textName.getText();
                String CA = textCA.getText();
                String Practical = textPractical.getText();
                String Theory = textTheory.getText();

                //convert student id, CA, practical, theory into integer
                int SID = Integer.parseInt(StudentID);
                int  SCA = Integer.parseInt(CA);
                int  SPractical = Integer.parseInt(Practical);
                int STheory = Integer.parseInt(Theory);

                String url="jdbc:mysql://localhost:3306/student's oop marks";
                String username="root";
                String password="";

                try {
                    //register the drive class
                    //open connection
                    Class.forName("com.mysql.jdbc.Driver");

                    //here student's oop marks is database name, root is username and password
                    Connection CONNECTION=DriverManager.getConnection("jdbc:mysql://localhost:3306/student's oop marks","root","Imesha@99");

                    //create statement for insert data into table
                    Statement STATEMENT=CONNECTION.createStatement();

                    String SQL = "INSERT INTO CLASS VALUES("+StudentID+","+StudentName+","+CA+","+Practical+","+Theory+")";

                    //create resultset
                    ResultSet resultSet=STATEMENT.executeQuery("select * from oop marks");

                    //execute query
                    while (resultSet.next()){
                        SID = resultSet.getInt("STD");
                        StudentName = resultSet.getString("StudentName").trim();
                        SCA = resultSet.getInt("SCA");
                        SPractical = resultSet.getInt("SPractical");
                        STheory = resultSet.getInt("STheory");

                        System.out.println("StudentID" + "StudentName" + "CA" + "Practical" + "Theory");
                    }

                    resultSet.close();
                    //close the statment
                    STATEMENT.close();
                    //close the connection
                    CONNECTION.close();

                }catch (Exception exception) {

                }

            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("STmarks");
        frame.setContentPane(new STmarks().STmarks);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
