package edu.icet.ecom.service;


import edu.icet.ecom.service.custom.Borrow_service;
import edu.icet.ecom.service.custom.impl.BookService_impl;
import edu.icet.ecom.service.custom.impl.BorrowService_Impl;
import edu.icet.ecom.service.custom.impl.ReturnBook_ServiceImpl;
import edu.icet.ecom.service.custom.impl.UserService_impl;
import edu.icet.ecom.util.Service_type;

public class ServiceFactory implements SuperService {
    private static ServiceFactory instence;
    private ServiceFactory(){}
    public static ServiceFactory getInstance(){
        return instence == null? instence = new ServiceFactory(): instence;
    }

    public <T extends SuperService>T getServiceType(Service_type service_type){
        switch (service_type){

            case BOOK: return (T) BookService_impl.getInstance();
            case USER: return (T) UserService_impl.getInstance();
            case BORROW: return (T) BorrowService_Impl.getInstance();
            case RETURNBOOK: return  (T) ReturnBook_ServiceImpl.getInstance();
        }
        return null;

    }



}
