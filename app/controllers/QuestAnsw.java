package controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.sun.xml.bind.util.Which;

import models.City;
import models.Comments;
import models.Country;
import models.Province;
import models.Ques;
import models.QuesSchool;
import models.Tag;
import play.mvc.Controller;

public class QuestAnsw extends Controller {
	public static void index() {
		render();
	}
	public static void QuesIndex() {
		List<Tag> t = Tag.findAll();
		List<Country> c = Country.findAll();
		List<Province> p = Province.findAll();
		List<City> ci = City.findAll();
		List<QuesSchool> q = QuesSchool.findAll();
		render(q, t, c, p, ci);
	}
	public static void dispAddQues(String title, String Tag, String content,
			String QuesSchool, String date,Long id) {
//		
//		if(!Ques.isTitle(title)){
//			validation.keep();
//			params.flash();
//			flash.error("错误:请输入标题名");
//			QuesIndex();
//		}else if(!Ques.isContent(content)){
//			validation.keep();
//			params.flash();
//			flash.error("错误:请输入内容");
//			QuesIndex();
//		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
		String d = (df.format(Calendar.getInstance().getTime()));
		
		
		Ques newQues = new Ques(title, Tag, QuesSchool, content, d);
		List<Comments> addCom = Comments.findAll();
		System.out.println(addCom+"1111111111");
		render(newQues,addCom);
	}

	public static void comments(String comment, String author, int answerNum,
			int praiseNum, int focu, long question_id,long quesid,long neQuesid) {
		Comments ac = new Comments(comment,author,answerNum,praiseNum,focu,quesid,neQuesid);
		String comments = ac.comment;
		renderHtml(comments);
		
	}
	public static void searchPage(Long id, Long question_id) {
		List<Tag> t = Tag.findAll();
		List<Country> c = Country.findAll();
		List<Province> p = Province.findAll();
		List<City> ci = City.findAll();
		List<QuesSchool> q = QuesSchool.findAll();
		List<Ques> aQues = Ques.findAll();
		List<Comments> aComen = Comments.findAll();
		render(q, t, c, p, ci,aQues,aComen);
	}
	public static void searchQues(String ques) {
		System.out.println("0000"+ques);
		List<Ques> anq = Ques.find("SELECT a FROM Ques a WHERE content LIKE ?",
				"%" + ques + "%").fetch();
		String s = "";
		Iterator iterator = anq.iterator();
		while(iterator.hasNext()){
			Ques q = (Ques) iterator.next();
			s = s+"标题："+ q.title+"<br />";
			s = s+"标签："+ q.label+"<br />";
			s = s+"学校："+q.school+"<br />";
			s = s+"内容："+ q.content+"<br /><br />";
		}
		renderHtml(s);
	}
	public static void searchTag(String tag) {
		List<Ques> t = Ques.find("SELECT a FROM Ques a WHERE label LIKE ?",
				"%" + tag + "%").fetch();
		 render(t);
	}
}
