package test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import util.assist.ExamArrangement;
import util.assist.SchoolworkAssist;
import util.control.Regist;

import static util.assist.SchoolworkAssist.*;

public class Test {
    public static void main(String[] args) throws Exception {
       Regist.assist = new SchoolworkAssist("201711140121","CoccaGuo1998");
//        System.out.println("getStudentInfo().getSpecialityCode() " + Regist.assist.getStudentInfo().getSpecialityCode());
//        System.out.println("getStudentInfo().getAcademicYear() " + Regist.assist.getStudentInfo().getAcademicYear());
//        System.out.println("getStudentInfo().getSchoolTerm() "  + Regist.assist.getStudentInfo().getSchoolTerm());
//        System.out.println("fetchSelectInfo().getXqM() " + Regist.assist.fetchSelectInfo().getXqM());
//        System.out.println("getStudentInfo().getYear()  " + Regist.assist.fetchSelectInfo().getYear());
//        System.out.println("getStudentInfo().getXktype() " + Regist.assist.fetchSelectInfo().getXktype());
//        System.out.println("getStudentInfo().getEndTime() " + Regist.assist.fetchSelectInfo().getEndTime());
//        System.out.println("getStudentInfo().getGrade() " + Regist.assist.fetchSelectInfo().getGrade());
//        System.out.println("getStudentInfo().getIsNjactive() " + Regist.assist.fetchSelectInfo().getIsNjactive());
//        System.out.println("getStudentInfo().getIsValidTimeRange() " + Regist.assist.fetchSelectInfo().getIsValidTimeRange());
//        System.out.println("getStudentInfo().getIsXjls() " + Regist.assist.fetchSelectInfo().getIsXjls());
//        System.out.println("getStudentInfo().getIsYxqxxk() " + Regist.assist.fetchSelectInfo().getIsYxqxxk());
//        System.out.println("getStudentInfo().getIsNjactive() " + Regist.assist.fetchSelectInfo().getIsNjactive());
//        System.out.println("getStudentInfo().getIsNjactive() " + Regist.assist.fetchSelectInfo().getIsNjactive());
//        System.out.println("getStudentInfo().getIsNjactive() " + Regist.assist.fetchSelectInfo().getIsNjactive());
//        System.out.println("getStudentInfo().getIsNjactive() " + Regist.assist.fetchSelectInfo().getIsNjactive());
//        System.out.println("getStudentInfo().getIsNjactive() " + Regist.assist.fetchSelectInfo().getIsNjactive());
//        System.out.println("getStudentInfo().getIsNjactive() " + Regist.assist.fetchSelectInfo().getIsNjactive());
//        System.out.println("getStudentInfo().getIsNjactive() " + Regist.assist.fetchSelectInfo().getIsNjactive());
//        System.out.println(Arrays.asList(Regist.assist.getCookies()).get(0));
        //Regist.assist.getSemesters().forEach(s -> System.out.println(s.getName()));
//        ArrayList<TableCourse> tableCourses = null;
//        try {
//            tableCourses = Regist.assist.getTableCourses(Regist.assist.getSemesters().get(0));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        tableCourses.forEach(t -> {
//            System.out.println();
//            System.out.println(t.getTeacher());
//            ArrayList<OneClass> classes = t.parseTime();
//            System.out.println(classes.size());
//            System.out.println(t.getName());
//            System.out.println(t.getLocationTime());
//            for (OneClass c:
//                classes ) {
//                System.out.println("周"+c.getWeekday());
//                System.out.println("地点："+c.getPlace());
//                System.out.println("周数：");
//                for (int a:
//                     c.getWeeks()) {
//                    System.out.print(a+" ");
//                }
//                System.out.println("开始时间："+c.getStartTime());
//                System.out.println("结束时间："+c.getEndTime());
//            }
//        });


//        ArrayList<TableCourse> list = Regist.assist;
//        for (TableCourse t:
//             list) {
//            System.out.println(t.toString());;
//        }
//       ArrayList<Semester> s = Regist.assist.getSemesters();
//        for (Semester ss:
//             s) {
//            System.out.println(ss.getName()+ss.getYear()+ " "+ ss.getTerm());
//        }
//       ArrayList<ExamScore> examScores =  Regist.assist.getExamScores(2018,1,false);
//       for (ExamScore e:
//       examScores){
//           System.out.println(e.getCourseName()+" "+e.getScore());
//       }
//
//        Connection.Response res = Jsoup.connect("http://jsty.bnu.edu.cn/view/sport/student/index")
//                .timeout(Regist.assist.getTimeout())
//                .cookies(Regist.assist.getCookies())
//                .header(HEADER_CONTENT_TYPE, CONTENT_TYPE)
//                .header(HEADER_REFERER, REFERER)
//                .header(USER_AGENT_HEADER, USER_AGENT)
//                .method(Connection.Method.GET)
//                .execute();
//        System.out.println(res.body());

         Regist.assist.getExamArrangement(Regist.assist.getExamRounds().get(0)).forEach(examArrangement -> System.out.println(examArrangement.toString() ));
//        ExamArrangement examArrangement =null;
//        examArrangement.getTimeString();
//        examArrangement.getCourse();
//        examArrangement.getClassification();
//        examArrangement.getExamType();
//        examArrangement.getSeat();
//        examArrangement.getEndTime();
//        examArrangement.getCredit();
//        examArrangement.getLocation();
    }
}
