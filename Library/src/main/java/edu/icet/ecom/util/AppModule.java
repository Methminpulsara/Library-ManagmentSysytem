package edu.icet.ecom.util;

import com.google.inject.AbstractModule;
import edu.icet.ecom.repository.custom.BookDao;
import edu.icet.ecom.repository.custom.Borrow_Dao;
import edu.icet.ecom.repository.custom.UserDao;
import edu.icet.ecom.repository.custom.impl.BookDao_impl;
import edu.icet.ecom.repository.custom.impl.BorrowDao_Impl;
import edu.icet.ecom.repository.custom.impl.UserDao_impl;
import edu.icet.ecom.service.custom.Book_service;
import edu.icet.ecom.service.custom.Borrow_service;
import edu.icet.ecom.service.custom.User_service;
import edu.icet.ecom.service.custom.impl.BookService_impl;
import edu.icet.ecom.service.custom.impl.BorrowService_Impl;
import edu.icet.ecom.service.custom.impl.UserService_impl;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(User_service.class).to(UserService_impl.class);
        bind(UserDao.class).to(UserDao_impl.class);

        bind(Book_service.class).to(BookService_impl.class);
        bind(BookDao.class).to(BookDao_impl.class);

        bind(Borrow_service.class).to(BorrowService_Impl.class);
        bind(Borrow_Dao.class).to(BorrowDao_Impl.class);

    }
}
