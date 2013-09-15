package models;

import java.awt.Label;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;
@Entity
public class Ques extends Model {
	@Required
	public String title;
	@Required
	public String content;
	@Required
	public String label;
	@Required
	public String school;
	@Required
	public String date;

	
	
	public Ques (String title, String Tag,String QuesSchool,String content, String date){
		this.title =title;
		this.content =content;
		this.label = Tag;
		this.school = QuesSchool;
		this.date =date;
		create();
	}
	public static Ques findByTitle(String title){
		return find("title",title).first();
	}
	public static boolean isTitle(String title){
		return findByTitle(title) == null;
	}
	
	public static Ques findByLabel(String label){
		return find("label",label).first();
	}
	public static boolean islabel(String label){
		return findByLabel(label) ==null;
	}
	public static Ques findByContent(String content){
		return find("content",content).first();
	}
	public static boolean isContent(String content){
		return findByContent(content) == null;
	}
	
	public static Ques findBySchool(String school){
		return find("school",school).first();
	}
	public static boolean isSchool(String school){
		return findBySchool(school) == null;
	}
}
