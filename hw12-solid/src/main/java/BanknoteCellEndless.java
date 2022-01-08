public class BanknoteCellEndless implements BanknoteCell{
    public BanknoteCellEndless(BanknoteType banknoteType, int banknoteCount) throws BanknoteCountException {
        if(banknoteCount >= 0)
            this.banknoteCount = banknoteCount;
        else
            throw new BanknoteCountException("Число банкнот не может быть меньше 0");
        this.banknoteType = banknoteType;
    }
    private int banknoteCount;
    private BanknoteType banknoteType;
    @Override
    public int getBanknoteCount() {
        return banknoteCount;
    }
    @Override
    public void addBanknotes(int banknoteCount) {
        this.banknoteCount += banknoteCount;
    }
    @Override
    public void removeBanknotes(int banknoteCount) throws BanknoteCountException {
        if(this.banknoteCount >= banknoteCount)
            this.banknoteCount -= banknoteCount;
        else
            throw new BanknoteCountException("Число банкнот в ячейке меньше запрашиваемого");
    }
    @Override
    public BanknoteType getBanknoteType() {
        return banknoteType;
    }
}
