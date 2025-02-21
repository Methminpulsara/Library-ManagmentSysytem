package edu.icet.ecom.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.ecom.db_connection.Db_Connection;
import edu.icet.ecom.entity.FineDetail_Entity;
import edu.icet.ecom.entity.User_entity;
import edu.icet.ecom.model.FineDetail;
import edu.icet.ecom.model.User;
import edu.icet.ecom.repository.custom.FineDao;
import edu.icet.ecom.service.custom.Fine_service;
import edu.icet.ecom.service.custom.ReturnBook_Service;
import edu.icet.ecom.service.custom.User_service;
import org.modelmapper.ModelMapper;

import java.sql.Connection;
import java.sql.SQLException;

public class FineService_Impl implements Fine_service {

    private static FineService_Impl instence;

    public static FineService_Impl getInstance(){
        return instence == null ? instence = new FineService_Impl(): instence;
    }

    @Inject
    FineDao fineDao;
    ModelMapper mapper = new ModelMapper();

    @Inject
    User_service userService;

    @Inject
    ReturnBook_Service returnBookService;




    @Override
    public boolean save(FineDetail fineDetail) throws SQLException {
        Connection connection = Db_Connection.getInstance().getConnection();

        try{
            connection.setAutoCommit(false);
            FineDetail_Entity entity = mapper.map(fineDetail,FineDetail_Entity.class);
            boolean isSave = fineDao.save(entity);
            if (isSave){
                boolean isPaymentAdded =userService.updatePayment(fineDetail.getUserID(),fineDetail.getFine());
                if (isPaymentAdded){
                    boolean isUpdate = returnBookService.updateStatus(fineDetail.getReturnID(),"returned");
                    if(isUpdate){
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }


    }
}
