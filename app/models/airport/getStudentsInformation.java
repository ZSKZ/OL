package models.airport;
import java.io.File; 
import java.util.*; 

import com.sun.rowset.internal.Row;

import jxl.*;
import jxl.write.*; 
/**
 * <p>Title: 操作EXCEL文件</p>
 * <p>Description: 本实例演示使用jxl包实现对excel文件的操作</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: myExcel.java</p>
 * @version 1.0
 */
public class getStudentsInformation{
  Workbook workbook;
  Sheet sheet;
/**
 *<br>方法说明：写入文件操作
 *<br>输入参数：
 *<br>返回类型：
 */
  public void write(){
    try{
        //创建一个可写入的excel文件对象
        WritableWorkbook workbook = Workbook.createWorkbook(new File("studentsInfor.xls")); 
        //使用第一张工作表，将其命名为“留学生信息”
        WritableSheet sheet = workbook.createSheet("留学生信息", 0); 
        //表头
        short i = 0;
        //标题
        Label label0 = new Label(0, i, "姓名"); 
        sheet.addCell(label0); 
        Label label1 = new Label(1, i, "性别"); 
        sheet.addCell(label1); 
        Label label2 = new Label(2, i, "联系方式"); 
        sheet.addCell(label2); 
        Label label3 = new Label(3, i, "邮箱"); 
        sheet.addCell(label3); 
        Label label4 = new Label(4, i, "专业"); 
        sheet.addCell(label4); 
        Label label5 = new Label(5, i, "专业"); 
        sheet.addCell(label5); 
        Label label6 = new Label(6, i, "到达机场"); 
        sheet.addCell(label6); 
        Label label7 = new Label(7, i, "到达日期"); 
        sheet.addCell(label7);
        Label label8 = new Label(8, i, "到达时间"); 
        sheet.addCell(label8); 
        Label label9 = new Label(9, i, "是否接机"); 
        sheet.addCell(label9); 
        Label label10 = new Label(10, i, "是否住宿"); 
        sheet.addCell(label10); 
        Label label11 = new Label(11, i, "行李"); 
        sheet.addCell(label11); 
        Label label12 = new Label(12, i, "备注"); 
        sheet.addCell(label12); 
        
        List<StuInfo> stu =StuInfo.findAll();
        Iterator<StuInfo> iter = stu.iterator();
        StuInfo st;
        int k=1;
//    	while(iter.hasNext()){
//    		st = iter.next();
//    		//System.out.print(st.length()+"ll");
//    		Label l0 = new Label(0,k,st.siUsername);
//    		sheet.addCell(l0);
//    		Label l1 = new Label(1,k,st.siSex);
//    		sheet.addCell(l1);
//    		Label l2 = new Label(2,k,st.siTelphone);
//    		sheet.addCell(l2);
//    		Label l3 = new Label(3,k,st.siEmail);
//    		sheet.addCell(l3);
//    		Label l4 = new Label(4,k,String.valueOf(st.siSchool));
//    		sheet.addCell(l4);
//    		Label l5 = new Label(5,k,st.simajor);
//    		sheet.addCell(l5);
//    		Label l6 = new Label(6,k,st.siAirport);
//    		sheet.addCell(l6);
//    		Label l7 = new Label(7,k,String.valueOf(st.siDate));
//    		sheet.addCell(l7);
//    		Label l8 = new Label(8,k,st.siTime);
//    		sheet.addCell(l8);
//    		Label l9 = new Label(9,k,st.sipickup);
//    		sheet.addCell(l9);
//    		Label l20 = new Label(10,k,st.siLuggage);
//    		sheet.addCell(l20);
//    		Label l21 = new Label(11,k,st.siStay);
//    		sheet.addCell(l21);
//    		Label l22 = new Label(12,k,st.siRemarks);
//    		sheet.addCell(l22);
//    		k++;
//    	}
    	
       
        //关闭对象，释放资源
        workbook.write(); 
        workbook.close();
        
        
    }catch(Exception e){
      System.out.println(e);
    }
  }
/**
 *<br>方法说明：读取excel文件一行数据
 *<br>输入参数：int row指定的行数
 *<br>返回类型：String〔〕结果数组
 */  
  public String[] readLine(int row){
    try{
      //获取数据表列数
      int colnum = sheet.getColumns();
      String[] rest = new String[colnum];
      for(int i = 0; i < colnum; i++){
        String sTemp = read(i,row);
        if(sTemp!=null)
         rest[i] = sTemp;
      }
      return rest;
    }catch(Exception e){
      System.out.println("readLine err:"+e);
      workbook.close();
      return null;
    }
  }
/**
 *<br>方法说明：读取excel的指定单元数据
 *<br>输入参数：
 *<br>返回类型：
 */
  public String read(int col, int row){
    try{
      //获得单元数据
      Cell a2 = sheet.getCell(col,row); 
      String rest = a2.getContents();
      return rest;
    }catch(Exception e){
      System.out.println("read err:"+e);
      workbook.close();
      return null;
    }
  }
/**
 *<br>方法说明：主方法，演示程序用
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] arges){
    try{
      getStudentsInformation me = new getStudentsInformation();
      //生成一个可读取的excel文件对象
      me.workbook = Workbook.getWorkbook(new File("myfile.xls"));
      //使用第一个工作表
      me.sheet = me.workbook.getSheet(0);
      //读一行记录，并显示出来
      String[] ssTemp = me.readLine(1);
      for(int i=0;i<ssTemp.length;i++)
       System.out.println(ssTemp[i]);
      //写入数据
      me.write();
      
      me.workbook.close();
    }catch(Exception e){
      System.out.println(e);
    }
  }
   
}