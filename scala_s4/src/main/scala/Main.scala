@main def hello(): Unit =
  val Loic = new BankAccount()
  
  // Effectuer un dépôt de 200
  val newBalanceAfterDeposit = Loic.deposit(200)
  println(s"Solde après dépôt: $newBalanceAfterDeposit")

  // Effectuer un retrait de 30
  val newBalanceAfterWithdrawal = Loic.withdraw(30)
  println(s"Solde après retrait: $newBalanceAfterWithdrawal")

  // Effectuer un retrait non autorisé
  val newBalanceAfterInvalidWithdrawal = Loic.withdraw(300)
  println(s"Solde après retrait non autorisé: $newBalanceAfterInvalidWithdrawal")