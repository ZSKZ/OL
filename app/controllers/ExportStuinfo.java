package controllers;

import java.util.Date;
import java.util.List;
import play.modules.excel.RenderExcel;
import models.airport.StuInfo;
import play.Logger;
import play.mvc.Controller;
import play.mvc.With;


<<<<<<< HEAD
public class ExportStuinfo  extends Controller {
=======
	public static void exportStu(String username) {
		try {
			//WritableWorkbook book = Workbook.createWorkbook(new File(
				//	"..//Desktop/接机学生信息.xls"));
			
			WritableWorkbook book = Workbook.createWorkbook(new File(
					"../接机学生信息.xls"));		
			
			WritableSheet sheet = book.createSheet("第一页", 0);
>>>>>>> f96bcab57ecec46f3a0fae768bdcd75f3bad6050

    public static void index() {
    	List<StuInfo> contacts = StuInfo.findAll();
        render(contacts);
    }
    
    public static void exportStu(String username) { 
    	request.format = "xlsx";
    	List<StuInfo> stu = StuInfo.findAll();
    	String __FILE_NAME__ = "test.xlsx";
    	renderArgs.put(RenderExcel.RA_ASYNC, true);
        render(__FILE_NAME__,stu);
    	
    }

}              

