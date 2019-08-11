package util.control;

/**
 * 注意一定要使用同一个filter。也就是不要用默认的匿名声明方式。
 * @param <T>
 */
public abstract class Filter<T> {
    T old;
     String compare(T t){
        if (old == null)old = t;
        String res = compareEach(old,t);
           old = t;
       return res;
    }
    public abstract String compareEach(T t1,T t2);//相同返回null
}
