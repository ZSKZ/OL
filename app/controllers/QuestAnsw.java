package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import models.qa.Attention;
import models.qa.Comments;
import models.qa.FocusQues;
import models.qa.Ques;
import models.qa.QuesSchool;
import models.qa.QuestionArticle;
import models.qa.Tag;
import models.users.CSSA;
import models.users.SimpleUser;

public class QuestAnsw extends Application {
	public static void index() {
		render();
	}

	public static void QuesIndex() {
		List<Tag> t = Tag.findAll();
		List<QuesSchool> q = QuesSchool.findAll();
		render(q, t);
	}

	public static void dispAddQues(String title, String Tag, String content,
			String school, String date, Long id, long answerNum, long focusNum,
			String selfIntro) {
		long userid = Long.parseLong(session.get("logged"));
		String usertype = session.get("usertype");
		String username;
		String userprofile;
		String userselfIntro;
		if (usertype.equals("simple")) {
			SimpleUser simp = SimpleUser.findById(userid);
			username = simp.name;
			userprofile = simp.profile;
			userselfIntro = simp.selfIntro;
		} else {
			CSSA cssa = CSSA.findById(userid);
			username = cssa.name;
			userprofile = cssa.profile;
			userselfIntro = cssa.selfIntro;
		}
		/*
		 * if(!Ques.isTitle(title)){ validation.keep(); params.flash();
		 * flash.error("错误:请输入标题名"); QuesIndex(); }else
		 * if(!Ques.isContent(content)){ validation.keep(); params.flash();
		 * flash.error("错误:请输入内容"); QuesIndex(); }
		 */
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
		String d = (df.format(Calendar.getInstance().getTime()));
		Ques newQues = new Ques(title, Tag, school, content, d, userid,
				usertype, username, userprofile, answerNum, focusNum,
				userselfIntro);
		// List<Comments> addCom = Comments.findAll();
		List<Comments> listCom = Comments.find("quesid = ?", id).fetch();
		render(newQues, listCom);
	}

	public static void comments(String qid, String comment
			) {
		long quesid = Long.parseLong(qid);
		long comentUserid = Long.parseLong(session.get("logged"));
		String comentUsertype = session.get("usertype");
		String comentUsername;
		String comentUserprofile;
		String comentUserSelfIntro;
		if (comentUsertype.equals("simple")) {
			SimpleUser simp = SimpleUser.findById(comentUserid);
			comentUsername = simp.name;
			comentUserprofile = simp.profile;
			comentUserSelfIntro = simp.selfIntro;
		} else {
			CSSA cssa = CSSA.findById(comentUserid);
			comentUsername = cssa.name;
			comentUserprofile = cssa.profile;
			comentUserSelfIntro = cssa.selfIntro;
		}
		Ques ques = Ques.findById(quesid);
		ques.answerNum = ques.answerNum + 1;
		ques.save();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
		String d = (df.format(Calendar.getInstance().getTime()));

		 new Comments(quesid, comment, 0, comentUserid,
				comentUsertype, comentUsername, comentUserprofile,
				comentUserSelfIntro, d);
		renderHtml(comment);
	}

	public static void searchPage(long id, long question_id) {
		List<Tag> t = Tag.findAll();
		List<QuesSchool> q = QuesSchool.findAll();
		List<Ques> aQues = Ques.find("order by date desc").fetch(5);
		long pageCount = Ques.count() % 5 == 0 ? Ques.count() / 5 : (Ques
				.count() / 5 + 1);
		Iterator iterator = aQues.iterator();
		List<QuestionArticle> qArticles = new ArrayList<QuestionArticle>();
		while (iterator.hasNext()) {
			Ques ques = (Ques) iterator.next();
			List comments = Comments.find("quesid = ?", ques.id).fetch();
			Comments comment = comments.isEmpty() ? null : (Comments) comments
					.get(0);
			QuestionArticle qa = new QuestionArticle(ques, comment);
			qArticles.add(qa);
		}
		render(q, t, qArticles, pageCount);
	}

	public static void searchQues(String ques) {
		List<Tag> t = Tag.findAll();
		List<Ques> anq = Ques.find("SELECT a FROM Ques a WHERE title LIKE ?",
				"%" + ques + "%").fetch(5);
		long pageCount = Ques.count() % 5 == 0 ? Ques.count() / 5 : (Ques
				.count() / 5 + 1);
		Iterator iterator = anq.iterator();
		List<QuestionArticle> qArticles = new ArrayList<QuestionArticle>();
		while (iterator.hasNext()) {
			Ques qu = (Ques) iterator.next();
			List comments = Comments.find("quesid = ?", qu.id).fetch();
			Comments comment = comments.isEmpty() ? null : (Comments) comments
					.get(0);
			QuestionArticle qa = new QuestionArticle(qu, comment);
			qArticles.add(qa);
		}
		renderTemplate("QuestAnsw/searchPage.html", qArticles, t, pageCount);
	}

	public static void searchTag(String tag) {
		List<Ques> anq = Ques.find("SELECT a FROM Ques a WHERE label LIKE ?",
				"%" + tag + "%").fetch(5);
		long pageCount = Ques.count() % 5 == 0 ? Ques.count() / 5 : (Ques
				.count() / 5 + 1);
		Iterator iterator = anq.iterator();
		List<QuestionArticle> qArticles = new ArrayList<QuestionArticle>();
		while (iterator.hasNext()) {
			Ques qu = (Ques) iterator.next();
			List comments = Comments.find("quesid = ?", qu.id).fetch();
			Comments comment = comments.isEmpty() ? null : (Comments) comments
					.get(0);
			QuestionArticle qa = new QuestionArticle(qu, comment);
			qArticles.add(qa);
		}
		List<Tag> t = Tag.findAll();
		renderTemplate("QuestAnsw/searchPage.html", qArticles, t, pageCount);
	}

	public static void showQuesInfo(long id) {

		System.out.println("dddddddddd" + id);
		String userType = session.get("usertype");
		long userId = Long.parseLong(session.get("logged"));

		String username;
		String userprofile;
		String userSelfIntro;
		if (userType.equals("simple")) {
			SimpleUser su = SimpleUser.findById(userId);
			username = su.name;
			userprofile = su.profile;
			userSelfIntro = su.selfIntro;
		} else {
			CSSA cssa = CSSA.findById(userId);
			username = cssa.name;
			userprofile = cssa.profile;
			userSelfIntro = cssa.selfIntro;
		}

		Ques fQ = Ques.findById(id);
		List<Comments> listCom = Comments.find("quesid = ?", id).fetch();
		render(fQ, listCom, userType, userId, username, userprofile,
				userSelfIntro);
	}

	public static void AttentionQues(long userId, long quesId) {
		Attention att = new Attention(userId, quesId);
		render(att);
	}

	public static void popupUserInfo() {
		render();
	}

	public static void userInfoIndex() {
		render();
	}

	public static void Quespaging(int pageNum) {
		List<Tag> t = Tag.findAll();
		long pageCount = Ques.count() % 5 == 0 ? Ques.count() / 5 : (Ques
				.count() / 5 + 1);
		if (pageNum < 1) {
			pageNum = 1;
		} else if (pageNum >= pageCount) {
			pageNum = (int) pageCount;
		}
		List<Ques> aQues = Ques.find("order by date desc")
				.from((pageNum - 1) * 5).fetch(5);
		Iterator iterator = aQues.iterator();
		List<QuestionArticle> qArticles = new ArrayList<QuestionArticle>();
		while (iterator.hasNext()) {
			Ques ques = (Ques) iterator.next();
			List comments = Comments.find("quesid = ?", ques.id).fetch();
			Comments comment = comments.isEmpty() ? null : (Comments) comments
					.get(0);
			QuestionArticle qa = new QuestionArticle(ques, comment);
			qArticles.add(qa);
		}
		renderTemplate("QuestAnsw/searchPage.html", qArticles, t, pageCount,
				pageNum);
	}

	public static void editQues() {
		render();
	}

	public static void deleteQues(long id, int pageNum) {
		Ques dques = Ques.findById(id);
		dques.delete();
		List<Tag> t = Tag.findAll();
		long pageCount = Ques.count() % 5 == 0 ? Ques.count() / 5 : (Ques
				.count() / 5 + 1);
		if (pageNum < 1) {
			pageNum = 1;
		} else if (pageNum >= pageCount) {
			pageNum = (int) pageCount;
		}
		List<Ques> aQues = Ques.find("order by date desc")
				.from((pageNum - 1) * 5).fetch(5);
		Iterator iterator = aQues.iterator();
		List<QuestionArticle> qArticles = new ArrayList<QuestionArticle>();
		while (iterator.hasNext()) {
			Ques ques = (Ques) iterator.next();
			List comments = Comments.find("quesid = ?", ques.id).fetch();
			Comments comment = comments.isEmpty() ? null : (Comments) comments
					.get(0);
			QuestionArticle qa = new QuestionArticle(ques, comment);
			qArticles.add(qa);
		}
		renderTemplate("QuestAnsw/searchPage.html", qArticles, t, pageCount,
				pageNum);
	}

	public static void editComent() {
		render();
	}

	public static void deleteComent() {
		render();
	}

	public static void fcousOnQuestion(long id) {
		String fquserType = session.get("usertype");
		long userId = Long.parseLong(session.get("logged"));
		long fquserid;
		String fquserprofile;

		if (fquserType.equals("simple")) {
			SimpleUser simpleUser = SimpleUser.findById(userId);
			fquserid = simpleUser.id;
			fquserprofile = simpleUser.profile;
		} else {
			CSSA cssa = CSSA.findById(id);
			fquserid = cssa.id;
			fquserprofile = cssa.profile;
		}
		Ques ques = Ques.findById(id);
		ques.focusNum = ques.focusNum + 1;
		ques.save();

		FocusQues fQues = new FocusQues(fquserType, fquserid, fquserprofile, id);

		validation.keep();
		params.flash();
		showQuesInfo(id);

		// renderTemplate("QuestAnsw/showQuesInfo.html", fQues);
		// renderHtml(fQues);
	}

}
