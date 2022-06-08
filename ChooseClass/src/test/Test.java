package test;

import util.assist.*;
import java.io.IOException;

import static util.control.Regist.assist;

public class Test {

    public static void main(String[] args) throws IOException, LoginException, NeedLoginException {


        assist = new SchoolworkAssist("201711140121", "CoccaGuo1998");
           // assist.getTableCourses(assist.getSemesters().get(0)).forEach(tableCourse -> System.out.println(tableCourse.getName()));课表
//        assist.getCancelCourses().forEach(cancelCourse -> System.out.println(cancelCourse.getName()));
//          assist.getElectiveCourses(true).forEach(electiveCourse -> System.out.println(electiveCourse.getName()));
       // assist.getPlanCourses(true).forEach(planCourse -> System.out.println(planCourse.getName()));
        //System.out.println(assist.fetchSelectInfo().getIsValidTimeRange().equals("0"));
        //System.out.println(assist.fetchClassroomArrangement());
        assist.getPlanCourses(true).forEach(planCourse -> System.out.println());

    }


}
