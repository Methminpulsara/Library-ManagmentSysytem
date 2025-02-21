package edu.icet.ecom.service.custom;

import edu.icet.ecom.model.FineDetail;
import edu.icet.ecom.service.SuperService;

import java.sql.SQLException;

public interface Fine_service extends SuperService {

    boolean save (FineDetail fineDetail) throws SQLException;
}
