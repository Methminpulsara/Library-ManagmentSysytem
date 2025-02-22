package edu.icet.ecom.repository.custom;

import edu.icet.ecom.entity.Return_Book_Entity;
import edu.icet.ecom.repository.CrudDao;

import java.util.List;

public interface ReturnBook_Dao extends CrudDao <Return_Book_Entity , String> {
    public int getlastRecordID();
    List<String> getFineDetil();
    String findUser(int returnID);
    boolean udapetStatus (String  UserId, String status);

    List<String> getUserid();
}
