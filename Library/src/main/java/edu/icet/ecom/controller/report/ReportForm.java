package edu.icet.ecom.controller.report;

import edu.icet.ecom.db_connection.Db_Connection;
import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportForm {
    public void btnUserReportOnAction(ActionEvent actionEvent) {
        JasperDesign design = null;
        try {
            design = JRXmlLoader.load("src/main/resources/report/user3.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, Db_Connection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint,"All_Users.pdf");
            JasperViewer.viewReport(jasperPrint,false);
        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }
    }
}
