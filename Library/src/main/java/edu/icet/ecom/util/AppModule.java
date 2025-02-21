package edu.icet.ecom.util;

import com.google.inject.AbstractModule;
import edu.icet.ecom.repository.custom.*;
import edu.icet.ecom.repository.custom.impl.*;
import edu.icet.ecom.service.custom.*;
import edu.icet.ecom.service.custom.impl.*;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(User_service.class).to(UserService_impl.class);
        bind(UserDao.class).to(UserDao_impl.class);

        bind(Book_service.class).to(BookService_impl.class);
        bind(BookDao.class).to(BookDao_impl.class);

        bind(Borrow_service.class).to(BorrowService_Impl.class);
        bind(Borrow_Dao.class).to(BorrowDao_Impl.class);

        bind (ReturnBook_Service.class).to(ReturnBook_ServiceImpl.class);
        bind (ReturnBook_Dao.class).to(ReturnBookDao_Impl.class);

        bind (Fine_service.class).to(FineService_Impl.class);
        bind(FineDao.class).to(FineDao_Impl.class);


    }
}
