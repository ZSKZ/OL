package controllers;

import java.util.List;

import models.airport.School;
import models.airport.StuInfo;
import models.airport.VolInfo;
import play.data.validation.Validation;
import play.mvc.*;
public class Airports extends Application {
	@Before(unless="index")
	public static void isLogged() {
		if (session.get("usertype") == null) {
			Application.index();
			
		}
	}
	public static void index() {
		render();
	}

	public static void addStuInfo() {
		List ls =  School.findAll();
		render(ls);
	}

	public static void doAddStuInfo(StuInfo s) {
		final Validation.ValidationResult validationResult = validation
				.valid(s);
		if(!validationResult.ok){
			validation.keep();
			params.flash();
			flash.error("请更正错误。");
			addStuInfo();
		}
		s.save();
		render();
	}

	public static void addVolInfo() {
		render();
	}

	public static void doAddVolInfo(VolInfo v) {
		final Validation.ValidationResult vr = validation.valid(v);
		if(!vr.ok){
			validation.keep();
			params.flash();
			flash.error("请更正错误。");
			addVolInfo();
		}
		v.save();
		render();

	}
	public static void exportStu() {
	}

}
