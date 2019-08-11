package util.control;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;


public abstract class Listener<T> {
   public abstract T refresh();//刷新的方法
    public abstract Filter<T> filter();//获取过滤器
  public abstract Alerter alerter();//提醒器

   private int freshTime = 60;//default 60s
    ListenService listenService;

   public void listen(){
       listenService = new ListenService();
       listenService.setPeriod(Duration.seconds(freshTime));
       listenService.start();
   }

   public void stop(){
       if (listenService!=null)listenService.cancel();
   }

    public void setFreshTime(int freshTime) {
        this.freshTime = freshTime;
    }

    private class ListenService extends ScheduledService<T>{
      @Override
      protected Task<T> createTask() {
          return new Task<T>() {
              @Override
              protected T call() throws Exception {

                T t = refresh();
                Filter<T> filter = filter();
                String res = filter.compare(t);//filter 中的设定
                if (res!=null){
                    Alerter  alerter = alerter();
                    alerter.alert(res);
                }
                  return t;
              }
          };
      }
  }
}
