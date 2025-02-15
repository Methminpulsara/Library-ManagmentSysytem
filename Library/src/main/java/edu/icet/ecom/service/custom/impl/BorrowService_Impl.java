package edu.icet.ecom.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.ecom.entity.BorrowEntity;
import edu.icet.ecom.model.Borrow;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.custom.Borrow_Dao;
import edu.icet.ecom.service.custom.Book_service;
import edu.icet.ecom.service.custom.Borrow_service;
import edu.icet.ecom.util.CrudUtil;
import edu.icet.ecom.util.Dao_type;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;



public class BorrowService_Impl implements Borrow_service {


    @Inject
    public static  Book_service bookService;

    private static BorrowService_Impl instence;

    public static BorrowService_Impl getInstance(){
        return instence == null? instence = new BorrowService_Impl(): instence;
    }



    @Inject
   private Borrow_Dao dao ;

    ModelMapper mapper = new ModelMapper();

    @Override
    public String getLastBarrowID() {
        String lastID = dao.getLastID();
        return lastID;

    }

    @Override
    public boolean placeOrder(Borrow borrow) {
      BorrowEntity entity = mapper.map(borrow,BorrowEntity.class);
        return dao.save(entity);
    }

}
