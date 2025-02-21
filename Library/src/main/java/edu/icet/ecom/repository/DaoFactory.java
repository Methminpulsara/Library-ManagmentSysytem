package edu.icet.ecom.repository;

import edu.icet.ecom.repository.custom.impl.*;
import edu.icet.ecom.util.Dao_type;

public class DaoFactory {
    private static DaoFactory instence ;
    private  DaoFactory(){}
    public static DaoFactory getInstance(){
        return instence == null? instence =new DaoFactory():instence;
    }

    public <T extends SuperDao>T getDao_type(Dao_type daoType){
        switch (daoType){
            case  BOOK: return (T) BookDao_impl.getInstance();
            case USER:  return (T) UserDao_impl.getInstance();
            case BORROW: return (T) BorrowDao_Impl.getInstance();
            case RETURNBOOK: return (T) ReturnBookDao_Impl.getInstance();
            case FINE: return (T) FineDao_Impl.getInstance();
        }
        return null;
    }


}


