package controllers;

import play.Logger;
import play.Play;
import play.data.binding.As;
import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.i18n.Lang;
import play.libs.Codec;
import play.libs.Crypto;
import play.mvc.*;
import play.*;
import play.mvc.*;

import java.text.SimpleDateFormat;
import java.util.*;

import org.codehaus.groovy.tools.shell.commands.ShowCommand;
import org.h2.store.Data;

import models.*;
import models.airport.School;
import models.airport.StuInfo;
import models.airport.VolInfo;
import models.airport.getPickupUserInformation;
import models.airport.getStudentsInformation;
import play.data.validation.Required;
import play.db.jpa.GenericModel.JPAQuery;
import play.db.jpa.JPABase;
//import static play.modules.excel.Excel.*;

public class PickUp extends Application {
	public static void index() {
		render();
	}
	public static void registerStudentInfo() {
		render();
	}
	
	public static void registerVolunteerInfo(){
		render();
		
		
	}
	public static void presentVolunteerInfo(@Required  @MaxSize(36) String pkUsername,
			@Required String pkSex,@Email @Required String pkEmail,
			@Required @MinSize(8) @MaxSize(10) long pkTelphone,
			@Required @MinSize(1) @MaxSize(10) String pkCar,
			@Required @MinSize(8) @MaxSize(10) int pkDate,
			@Required  String pkCheat) {
//		VolInfo uf = new VolInfo(pkUsername, pkSex, pkEmail, pkTelphone,
//				pkCar, pkDate, pkCheat);
//		System.out.println("看看id"+uf.id);
//		flash.success("成功!");
//		long id=uf.id;
//		render(id);
		//renderTemplate("");
	}
	public static void presentStuInfo(StuInfo s) {
		s.save();
		long id=s.id;
		flash.success("添加信息成功");
		List<School> l = School.findAll();
		render(id,l);
	}
	public static void showUserInfor(){
		System.out.print("11");
		List<VolInfo> us = VolInfo.findAll();
		//renderArgs.put("us", "showUserInfor.xls");
		render(us);
	}
	public static void showStudentsInfor(){
		List<StuInfo> si = StuInfo.findAll();
		render(si);
	}
	public static void expStuInfor(){
		List<School> l = School.findAll();
		getStudentsInformation xls = new getStudentsInformation();
		xls.write();
		render(l);
    }
	public static void expUserInfor(){
		getPickupUserInformation xls = new getPickupUserInformation();
		xls.write();
		render();
    }
	public static void detail(long id){
		VolInfo ub= VolInfo.find("id = ?", id).first();
		render(ub);
	}
	public static void disStuDetail(long id){
		StuInfo stu = StuInfo.find("id=?", id).first();
		render(stu);
	}
	public static void changePuUserInfo(long id){
		VolInfo ub = VolInfo.find("id=?", id).first();
		render(ub);
	}
	public static void changePuStudents(long id){
		StuInfo stu = StuInfo.find("id=?", id).first();
		render(stu);
	}
	public static void updateUserInfo(VolInfo uf){
		System.out.println("user id......."+uf.id);
		 uf.save();
		 render();
	}
	public static void updateStuSuccess(StuInfo st){
		System.out.println("students cahnge id "+st.id);
		st.save();
		render();
	}
//	public static void formatDate(long id){
//		studentsInfor s = studentsInfor.find("id=?", id).first();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		 try{
//			 Date date=sdf.parse(s.siDate);
//			 //System.out.println("kaka;;;;;"+date);   
//		 }catch(Exception ex){
//			 //System.out.println(ex.getMessage());   
//		 }
//	}
}
