package Dao;

import Model.Idol;

import java.util.List;

public interface IdolDao {
    public List<Idol> getIdolsListfromDB();
    public Idol getIdolsById(int id);
    public  void InsertIdolInDB(Idol idol);
    public void updateIdol(Idol idol);
    public  boolean deleteIdol(int id);
    public String testEmail(Idol idol);
}
