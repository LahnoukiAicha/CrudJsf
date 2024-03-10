package Services;

import Dao.IdolDao;
import Dao.IdolDaoImpl;
import Model.Idol;

import java.util.List;

public class IdolService {

    private final IdolDao idolDao = new IdolDaoImpl();

    public List<Idol> getIdolsListService() {
        return idolDao.getIdolsListfromDB();
    }

    public Idol getIdolByIdService(int id) {
        return idolDao.getIdolsById(id);
    }

    public void addIdolService(Idol idol) {
        String errorMessage = idolDao.testEmail(idol);
        if (errorMessage != null) {
            throw new IllegalArgumentException(errorMessage);
        }
        idolDao.InsertIdolInDB(idol);
    }

    public void updateIdolService(Idol idol) {
        String errorMessage = idolDao.testEmail(idol);
        if (errorMessage != null) {
            throw new IllegalArgumentException(errorMessage);
        }
        idolDao.updateIdol(idol);
    }

    public void deleteIdolService(int id) {
        idolDao.deleteIdol(id);
    }

    public void testEmailService(Idol idol){
        idolDao.testEmail(idol);
    }

}
