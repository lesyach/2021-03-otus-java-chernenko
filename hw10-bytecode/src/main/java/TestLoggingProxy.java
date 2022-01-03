import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class TestLoggingProxy {
    public static TestLoggingInterface createInstance() {
        InvocationHandler handler = new TestLoggingInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(TestLoggingProxy.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class TestLoggingInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testLoggingInterface;
        private Logger logger = Logger.getLogger(TestLoggingInvocationHandler.class.getName());

        TestLoggingInvocationHandler(TestLoggingInterface testLoggingInterface) {
            this.testLoggingInterface = testLoggingInterface;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
            scanMethod(method, args);
            return method.invoke(testLoggingInterface, args);
        }

        private void scanMethod(Method m, Object[] args) throws NoSuchMethodException {
            if(TestLogging.class.getMethod(m.getName(), m.getParameterTypes()).isAnnotationPresent(Log.class))
                logger.info(String.format("executed method: %s, param: %s", m.getName(), Arrays.toString(args)));
//            else
//                logger.info("sorry not sorry");
        }
    }
}
