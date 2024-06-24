class BankAccount:
    private var balance: Int = 0
    def deposit(amount: Int): Int =
        if amount > 0 then balance = balance + amount
        balance
    end deposit

    def withdraw(amount: Int): Int =
        if amount > 0 && amount <= balance then
            balance = balance - amount
            balance
        else
            println("Provisions insuffisantes : Transaction rejetée")
            balance
    end withdraw
end BankAccount

val LoicCIC = new BankAccount()

LoicCIC.deposit(200)
LoicCIC.withdraw(30)
LoicCIC.withdraw(500)