package util.assist;

import util.control.Regist;

import java.io.IOException;

/**
 * Created by xuhongxu on 16/4/6.
 *
 * NeedLoginException
 *
 * @author Hongxu Xu
 * @version 0.1
 */
public class NeedLoginException extends Exception {
    NeedLoginException(String msg) {
        super(msg);
    }

    NeedLoginException()  {
        super();
        try {
            Regist.assist.login();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
