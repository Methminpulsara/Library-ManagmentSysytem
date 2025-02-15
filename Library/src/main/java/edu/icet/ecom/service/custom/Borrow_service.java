package edu.icet.ecom.service.custom;

import edu.icet.ecom.model.Borrow;
import edu.icet.ecom.service.SuperService;

public interface Borrow_service extends  SuperService{
    String getLastBarrowID();
    boolean placeOrder(Borrow borrow);


}
