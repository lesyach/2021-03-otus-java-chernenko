public class DemoRunner {
//      ���. 03, 2022 11:59:55 PM TestLoggingProxy$TestLoggingInvocationHandler scanMethod
//      INFO: executed method: calculation, param: [1]
//      ���. 03, 2022 11:59:55 PM TestLoggingProxy$TestLoggingInvocationHandler scanMethod
//      INFO: executed method: calculation, param: [1, 2]
//      ���. 03, 2022 11:59:55 PM TestLoggingProxy$TestLoggingInvocationHandler scanMethod
//      INFO: executed method: calculation, param: [1, 2, qwerty]
//      ���. 03, 2022 11:59:55 PM TestLoggingProxy$TestLoggingInvocationHandler scanMethod
//      INFO: sorry not sorry
    public static void main(String[] classNames) {
        TestLoggingInterface logging = TestLoggingProxy.createInstance();
        logging.calculation(1);
        logging.calculation(1, 2);
        logging.calculation(1, 2, "qwerty");
        logging.calculation(1, 2, 3);
    }
}