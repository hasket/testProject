class BankAccount {

    protected int amount;
    protected String currency;

    public void replenishBalance(int amount) {
        this.amount += amount;
        System.out.println("Счет пополнен на " + amount + " " + currency);
    }

    public void withdrawCash(int amount) {
    }

    public void showBalance() {
    }
}

class DebitAccount extends BankAccount {

    public DebitAccount(int amount, String currency) {
        if (amount < 0) {
            System.out.println("Баланс дебетового счета не может быть меньше 0");
        } else {
            this.amount = amount;
            this.currency = currency;
        }
    }

    @Override
    public void withdrawCash(int amount) {
        if (amount > this.amount) {
            System.out.println("У вас недостаточно средств для снятия суммы " + amount + " " + currency);
        } else {
            this.amount -= amount;
            System.out.println("Вы сняли " + amount + " " + currency);
        }
    }

    @Override
    public void showBalance() {
        System.out.println("На вашем счету осталось " + amount + " " + currency);
    }
}

class CreditAccount extends BankAccount {

    public int creditLimit;

    public CreditAccount(int amount, String currency, int creditLimit) {
        this.amount = amount;
        this.currency = currency;
        this.creditLimit = creditLimit;
    }

    @Override
    public void withdrawCash(int amount) {
        if (this.amount - amount < -creditLimit) {
            System.out.println("У вас недостаточно средств для снятия суммы " + amount + " " + currency);
        } else {
            this.amount -= amount;
            System.out.println("Вы сняли " + amount + " " + currency);
        }
    }

    @Override
    public void showBalance() {
        if (amount >= 0) {
            System.out.println("На вашем счету " + amount + " " + currency);
        } else {
            System.out.println("Ваша задолженность по кредитному счету составялет " + Math.abs(amount) + currency);
        }
    }
}

class Bank {

    // создать метод createNewAccount, который принимает на вход строку с типом аккаунта и строку с создаваемой валютой
    public BankAccount createNewAccount(String accountType, String currency) {
        BankAccount bankAccount = new BankAccount();

        switch (accountType) {
            // если тип "debit_account"
            case "debit_account":
                // вывести сообщение "Ваш дебетовый счет создан"
                System.out.println("Ваш дебетовый счет создан");
                // создать дебетовый аккаунт в выбранной валюте и с нулевым балансом
                BankAccount debtAcc = new DebitAccount(0, currency);
                return debtAcc;
            // если тип "credit_account"
            case "credit_account":
                // посчитать кредитный лимит в зависимости от валюты
                int creditLimit = calculateCreditLimit(currency);
                // вывести сообщение "Кредитный счет создан. Ваш лимит по счету {limit} {currency}"
                System.out.println("Кредитный счет создан. Ваш лимит по счету " + creditLimit + " " + currency);
                // создать кредитный аккаунт в выборанной валюты и с посчитанным кредитным лимитом
                BankAccount creditAcc = new CreditAccount(bankAccount.amount, currency, creditLimit);
                return creditAcc;
            // иначе
            default:
                // вывести сообщение "Неверно указа тип создаваемого счета"
                System.out.println("Неверно указа тип создаваемого счета");
                // создать пустой объект BankAccount()
                BankAccount emptyAcc = new BankAccount();
                return emptyAcc;
        }
    }

    // создать метод closeAccount, который принимает на вход переменную типа BankAccount
    public void closeAccount(BankAccount bankAccount) {

        // если переданный аккаунт дебетовый
        if (bankAccount instanceof DebitAccount) {
            if (bankAccount.amount == 0)
                // если на счету нет денег вывести сообщение "Ваш дебетовый счет закрыт"
                System.out.println("Ваш дебетовый счет закрыт");
            else
                // иначе вывести сообщение "Ваш дебетовый счет закрыт. Вы можете получить остаток по вашему счету в размере {amount} {currency} в отделении банка"
                System.out.println("Ваш дебетовый счет закрыт. Вы можете получить остаток по вашему счету в размере " + ((DebitAccount) bankAccount).amount + " " + ((DebitAccount) bankAccount).currency + " в отделении банка");
        } else if (bankAccount instanceof CreditAccount) {  // если переданный аккаунт кредитный
            if (bankAccount.amount == 0) {
                // если на счету нет денег вывести сообщение "Ваш кредитный счет закрыт"
                System.out.println("Ваш кредитный счет закрыт");
            } else if (bankAccount.amount > 0) {
                // если на счету положительный баланс вывести сообщение "Ваш кредитный счет закрыт. Вы можете получить остаток по вашему счету в размере {amount} {currency} в отделении банка"
                System.out.println("Ваш кредитный счет закрыт. Вы можете получить остаток по вашему счету в размере " + ((CreditAccount) bankAccount).amount + " " + ((CreditAccount) bankAccount).currency + " в отделении банка");
            } else if (bankAccount.amount < 0) {
                // если на счету отрицательный баланс вывести сообщение "Вы не можете закрыть кредитный счет потому как на нем еще есть задолженность. Ваша задолженность по счету составляет {amount} {currency}"
                System.out.println("Вы не можете закрыть кредитный счет потому как на нем еще есть задолженность. Ваша задолженность по счету составляет " + ((CreditAccount) bankAccount).amount + " " + ((CreditAccount) bankAccount).currency);
            }
        } else {
            // иначе вывести сообщение "Пока что мы не можем закрыть данный вид счета"
            System.out.println("Пока что мы не можем закрыть данный вид счета");
        }
    }

    private int calculateCreditLimit(String currency){
        switch (currency){
            case "RUB":
                return 100000;
            case "USD":
                return 1250;
            case "EUR":
                return 1000;
            default:
                return 0;
        }
    }


}