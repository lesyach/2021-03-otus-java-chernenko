import java.util.*;
// обычный банкомат с бесконечным числом номинальных ячеек с бесконечной вместимостью
public class ClassicAtm implements Atm {

    private List<BanknoteCellEndless> depository = new ArrayList<>();

    public ClassicAtm() throws BanknoteCountException {
        for(BanknoteType banknoteType : BanknoteType.values())
            depository.add(new BanknoteCellEndless(banknoteType, 0));
    }

    public ClassicAtm(HashMap<BanknoteType, Integer> money) throws BanknoteCountException {
        for(BanknoteType banknoteType : BanknoteType.values())
            depository.add(new BanknoteCellEndless(banknoteType, 0));
        addMoney(money);
    }

    @Override
    // добавить пачку купюр
    public void addMoney(HashMap<BanknoteType, Integer> money) {
        for(BanknoteType banknoteType : money.keySet()) {
            addBanknotes(banknoteType, money.get(banknoteType));
        }
    }

    @Override
    // выдать пачку купюр
    public HashMap<BanknoteType, Integer> getMoney(int money) throws AtmLowBalanceException, AtmBanknotTypeException, AtmGetMoneyException, BanknoteCountException {
        if(getBalance() < money)
            throw new AtmLowBalanceException("Суммарный баланс меньше запрашиваемой суммы");
        if(money % getMinBanknoteType().coin != 0)
            throw new AtmBanknotTypeException("Запрашиваемая сумма не кратна минимальному номиналу банкнот");
        HashMap<BanknoteType, Integer> result = new HashMap<>();
        BanknoteType neededBanknoteType = getMaxBanknoteType();
        int residue = money;
        for(BanknoteType banknoteType : BanknoteType.values()) {
            int banknoteCountNeeded = residue / neededBanknoteType.coin;
            int banknoteCountExist = getBanknoteCountFromBanknoteCell(neededBanknoteType);
            if (banknoteCountNeeded > 0 && banknoteCountExist > 0) {
                if(banknoteCountNeeded >= banknoteCountExist) {
                    result.put(neededBanknoteType, banknoteCountExist);
                    residue = residue - (banknoteCountExist * neededBanknoteType.coin);
                }
                else {
                    result.put(neededBanknoteType, banknoteCountNeeded);
                    residue = residue - (banknoteCountNeeded * neededBanknoteType.coin);
                }
            }
            neededBanknoteType = getLessBanknoteType(neededBanknoteType);
        }
        if(residue > 0)
            throw new AtmGetMoneyException("Невозможно выдать запрашиваемую сумму");
        else
            for(BanknoteType banknoteType : result.keySet())
                removeBanknotes(banknoteType, result.get(banknoteType));
        return result;
    }

    @Override
    /* получить суммарный баланс */
    public int getBalance() {
        int result = 0;
        for(BanknoteCellEndless banknoteCellEndless : depository) {
            result += banknoteCellEndless.getBanknoteCount() * banknoteCellEndless.getBanknoteType().coin;
        }
        return result;
    }

    private void addBanknotes(BanknoteType banknoteType, int count) {
        for(BanknoteCellEndless banknoteCellEndless : depository) {
            if(banknoteCellEndless.getBanknoteType().equals(banknoteType)) {
                banknoteCellEndless.addBanknotes(count);
            }
        }
    }

    private void removeBanknotes(BanknoteType banknoteType, int count) throws BanknoteCountException {
        for(BanknoteCellEndless banknoteCellEndless : depository) {
            if(banknoteCellEndless.getBanknoteType().equals(banknoteType)) {
                banknoteCellEndless.removeBanknotes(count);
            }
        }
    }

    private BanknoteType getMaxBanknoteType() {
        BanknoteType maxBanknoteType = depository.get(0).getBanknoteType();
        for(int i = 1; i < depository.size(); i++) {
            if(depository.get(i).getBanknoteType().coin > maxBanknoteType.coin)
                maxBanknoteType = depository.get(i).getBanknoteType();
        }
        return maxBanknoteType;
    }

    private BanknoteType getMinBanknoteType() {
        BanknoteType minBanknoteType = depository.get(0).getBanknoteType();
        for(int i = 1; i < depository.size(); i++) {
            if(depository.get(i).getBanknoteType().coin < minBanknoteType.coin)
                minBanknoteType = depository.get(i).getBanknoteType();
        }
        return minBanknoteType;
    }

    private BanknoteType getLessBanknoteType(BanknoteType banknoteType) {
        BanknoteType lessBanknoteType = getMinBanknoteType();
        for(BanknoteCellEndless banknoteCellEndless : depository) {
            if(banknoteCellEndless.getBanknoteType().coin < banknoteType.coin &&
                    banknoteCellEndless.getBanknoteType().coin > lessBanknoteType.coin)
                lessBanknoteType = banknoteCellEndless.getBanknoteType();
        }
        return lessBanknoteType;
    }

    private int getBanknoteCountFromBanknoteCell(BanknoteType banknoteType) {
        for(BanknoteCellEndless banknoteCellEndless : depository) {
            if(banknoteCellEndless.getBanknoteType().equals(banknoteType))
                return banknoteCellEndless.getBanknoteCount();
        }
        return 0;
    }
}
