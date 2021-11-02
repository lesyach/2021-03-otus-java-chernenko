import java.util.Random;

public class CalculatorTest {
    public CalculatorTest() {
        System.out.println("Im ready");
    }

    private int a;
    private int b;
    private Random random = new Random();

    @Before
    public void GenerateA() {
        a = random.nextInt(20)-5;
        System.out.println("a = " + a);
    }

    @Before
    public void GenerateB() {
        b = random.nextInt(20)-5;
        System.out.println("b = " + b);
    }

    @Test
    public void CheckMultiplication() throws Exception {
        int result = Calculator.Multiplication(a,b);
        if(a * b != result)
            throw new Exception("Exception Multiplication");
        System.out.println("a * b = " + result);
        System.out.println("Multiplication OK");
    }

    @Test
    public void CheckAddition() throws Exception {
        int result = Calculator.Addition(a,b);
        if(a + b != result)
            throw new Exception("Exception Addition");
        System.out.println("a + b = " + result);
        System.out.println("Addition OK");
    }

    @Test
    public void CheckDivision() throws Exception {
        int result = Calculator.Division(a,b);
        if(a / b != result)
            throw new Exception("Exception Division");
        System.out.println("a / b = " + result);
        System.out.println("Division OK");
    }

    @Test
    public void CheckSubtraction() throws Exception {
        int result = Calculator.Subtraction(a,b);
        if(a - b != result)
            throw new Exception("Exception Subtraction");
        System.out.println("a - b = " + result);
        System.out.println("Subtraction OK");
    }

    @Test
    public void CheckDivisionZero() throws Exception {
        try {
            Calculator.Division(a,0);
            throw new Exception("Exception Division Zero");
        }
        catch (Exception e) {
            System.out.println("Division Zero OK");
        }
    }

    @Test
    public void CheckBadDivisionZero() throws Exception {
        Calculator.Division(a,0);
    }

    @After
    public void End() {
        System.out.println("Im done");
    }
}
