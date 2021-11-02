import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void main(String[] classNames) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        runTests(CalculatorTest.class);
    }

    private static void runTests(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("Run tests class: " + clazz.getName());

        int successTests = 0, failedTests = 0;

        for(Method method : GetMethodsByAnnotation(clazz, Test.class)) {
            System.out.println("TEST: " + method.getName());
            Object clazzObject = clazz.getDeclaredConstructor().newInstance();

            for(Method methodBefore : GetMethodsByAnnotation(clazz, Before.class)) {
                InvokeMethod(methodBefore, clazzObject);
            }

            if(InvokeMethod(method, clazzObject))
                successTests++;
            else
                failedTests++;

            for(Method methodAfter : GetMethodsByAnnotation(clazz, After.class)) {
                InvokeMethod(methodAfter, clazzObject);
            }
        }

        System.out.println("-----RESULTS-----");
        System.out.println("SUCCESS: " + successTests);
        System.out.println("FAILED: " + failedTests);
        System.out.println("ALL: " + (successTests + failedTests));
    }

    private static List<Method> GetMethodsByAnnotation(Class clazz, Class annotaton) {
        Method[] methods = clazz.getMethods();
        List<Method> result = new ArrayList<>();
        for(Method method : methods) {
            if(method.isAnnotationPresent(annotaton))
                result.add(method);
        }
        return result;
    }

    private static boolean InvokeMethod(Method method, Object object) {
        try {
            method.invoke(object);
            System.out.println(method.getName() + " OK");
            return true;
        } catch (Throwable e) {
            System.out.println(method.getName() + " FAIL: " + e.toString());
            return false;
        }
    }
}