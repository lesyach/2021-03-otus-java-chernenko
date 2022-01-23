public interface BanknoteCell {
    int getBanknoteCount() ;
    void addBanknotes(int banknoteCount);
    void removeBanknotes(int banknoteCount) throws BanknoteCountException;
    BanknoteType getBanknoteType();
}
