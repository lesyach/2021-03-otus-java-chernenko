import java.util.HashMap;

public interface Atm {
    void addMoney(HashMap<BanknoteType, Integer> money);
    HashMap<BanknoteType, Integer> getMoney(int money) throws AtmLowBalanceException, AtmBanknotTypeException, AtmGetMoneyException, BanknoteCountException;
    int getBalance();
}
