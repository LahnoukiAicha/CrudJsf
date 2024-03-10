package Dao;

import Model.Idol;
import Utils.ConnectionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IdolDaoImpl implements IdolDao{
    public static String req;
    public static Connection conn = ConnectionDb.connectionBD();
    public static ResultSet res;
    public static PreparedStatement preparedStatement;
    @Override
    public List<Idol> getIdolsListfromDB() {
        List<Idol> idolBeans = new ArrayList<>();
        try{
            req="select * from idoltable";
            preparedStatement = conn.prepareStatement(req);
            res=preparedStatement.executeQuery();
            while(res.next()){
                int id=res.getInt("id");
                String firstName=res.getString("firstName");
                String lastName=res.getString("lastName");
                String role=res.getString("role");
                String email=res.getString("email");
                String passwd=res.getString("passwd");
                idolBeans.add(new Idol(id,firstName,lastName,role,email,passwd));
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return idolBeans;
    }

    @Override
    public Idol getIdolsById(int id) {
        Idol idolBean =null;
        try{
            req="select * from idoltable where id=?";
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, id);
            res = preparedStatement.executeQuery();
            while (res.next()) {
                String firstName=res.getString("firsName");
                String lastName=res.getString("lastName");
                String role=res.getString("role");
                String email=res.getString("email");
                String passwd=res.getString("passwd");
                idolBean = new Idol(id,firstName,lastName,role,email,passwd);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return idolBean;
    }

    @Override
    public void InsertIdolInDB(Idol idolBean) {
        try{
            req="insert into idoltable (firstName,lastName,role,email,passwd) values (?,?,?,?,?)";
            preparedStatement=conn.prepareStatement(req);
            preparedStatement.setString(1, idolBean.getFirstName());
            preparedStatement.setString(2, idolBean.getLastName());
            preparedStatement.setString(3, idolBean.getRole());
            preparedStatement.setString(4, idolBean.getEmail());
            preparedStatement.setString(5, idolBean.getPasswd());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateIdol(Idol idolBean) {
        try{
            req="update idoltable set firstName=?,lastName=?,role=?, email=?, passwd=? where id=?";
            preparedStatement =conn.prepareStatement(req);
            preparedStatement.setString(1, idolBean.getFirstName());
            preparedStatement.setString(2, idolBean.getLastName());
            preparedStatement.setString(3, idolBean.getRole());
            preparedStatement.setString(4, idolBean.getEmail());
            preparedStatement.setString(5, idolBean.getPasswd());
            preparedStatement.setInt(6, idolBean.getId());
            int rowsAffected= preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Un Idol a été mis à jour avec succès.");
            }

        }
        catch(SQLException e){
         System.out.println("Erreur lors de la mise à jour de l'Idol : " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public boolean deleteIdol(int id) {
        boolean rowDeleted = false;
        try{
            req="delete from idoltable where id=?";
            preparedStatement=conn.prepareStatement(req);
            preparedStatement.setInt(1,id);
            rowDeleted=preparedStatement.executeUpdate()>0;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return rowDeleted;
    }
    @Override
    public String testEmail(Idol idol) {
        try {
            String req = "SELECT email FROM idoltable WHERE id != ? AND email = ?";
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, idol.getId());
            preparedStatement.setString(2, idol.getEmail());
            res = preparedStatement.executeQuery();
            if (res.next()) {
                return "Error: Email already exists.";
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Database connection error.";
        }
    }


}
