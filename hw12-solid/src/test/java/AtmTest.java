import org.junit.*;

import java.util.HashMap;

public class AtmTest {
    @Test
    public void AllPositiveTest() throws BanknoteCountException, AtmLowBalanceException, AtmBanknotTypeException, AtmGetMoneyException {
        System.out.println("---AllPositiveTest begin");
        HashMap<BanknoteType, Integer> money = new HashMap<>();
        money.put(BanknoteType.Coin5000, 1);
        money.put(BanknoteType.Coin1000, 20);
        money.put(BanknoteType.Coin200, 5);
        money.put(BanknoteType.Coin100, 4);
        money.put(BanknoteType.Coin50, 5);
        ClassicAtm atm = new ClassicAtm(money);
        System.out.println(atm.getBalance());
        System.out.println(atm.getMoney(10500));
        System.out.println(atm.getBalance());
        System.out.println(atm.getMoney(15200));
        System.out.println(atm.getBalance());
        System.out.println(atm.getMoney(450));
        System.out.println(atm.getBalance());
        System.out.println("---AllPositiveTest end");
    }

    @Test
    public void AtmLowBalanceExceptionTest() throws BanknoteCountException, AtmBanknotTypeException, AtmGetMoneyException {
        System.out.println("---AtmLowBalanceExceptionTest begin");
        HashMap<BanknoteType, Integer> money = new HashMap<>();
        money.put(BanknoteType.Coin5000, 1);
        ClassicAtm atm = new ClassicAtm(money);
        System.out.println(atm.getBalance());
        try {
            System.out.println("get 5050");
            atm.getMoney(5050);
        }
        catch (AtmLowBalanceException e) {
            System.out.println(e.getMessage());
            System.out.println("Success!");
        }
        catch (Exception e) {
            System.out.println("Not success...");
            throw e;
        }
        System.out.println("---AtmLowBalanceExceptionTest end");
    }

    @Test
    public void AtmBanknotTypeExceptionTest() throws AtmLowBalanceException, BanknoteCountException, AtmGetMoneyException {
        System.out.println("---AtmBanknotTypeExceptionTest begin");
        HashMap<BanknoteType, Integer> money = new HashMap<>();
        money.put(BanknoteType.Coin5000, 2);
        ClassicAtm atm = new ClassicAtm(money);
        System.out.println(atm.getBalance());
        try {
            System.out.println("get 5020");
            atm.getMoney(5020);
        }
        catch (AtmBanknotTypeException e) {
            System.out.println(e.getMessage());
            System.out.println("Success!");
        }
        catch (Exception e) {
            System.out.println("Not success...");
            throw e;
        }
        System.out.println("---AtmBanknotTypeExceptionTest end");
    }

    @Test
    public void AtmGetMoneyExceptionTest() throws BanknoteCountException, AtmBanknotTypeException, AtmLowBalanceException {
        System.out.println("---AtmGetMoneyExceptionTest begin");
        HashMap<BanknoteType, Integer> money = new HashMap<>();
        money.put(BanknoteType.Coin5000, 1);
        ClassicAtm atm = new ClassicAtm(money);
        System.out.println(atm.getBalance());
        try {
            System.out.println("get 1000");
            atm.getMoney(1000);
        }
        catch (AtmGetMoneyException e) {
            System.out.println(e.getMessage());
            System.out.println("Success!");
        }
        catch (Exception e) {
            System.out.println("Not success...");
            throw e;
        }
        System.out.println("---AtmBanknotTypeExceptionTest end");
    }
}
