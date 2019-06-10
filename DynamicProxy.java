import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Create by 李林浪 in 2019/5/21
 * Elegant Code...
 */
public class DynamicProxy implements InvocationHandler {
    private Object obj;
    public Object getProxySubject(Object obj){
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我帮你代理勒一些东西");
        return method.invoke(obj,args);
    }
}
