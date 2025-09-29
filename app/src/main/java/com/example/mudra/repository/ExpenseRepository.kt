package com.example.mudra.repository

import com.example.mudra.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ExpenseRepository(
    private val expenseDao: ExpenseDao,
    private val walletDao: WalletDao
) {
    fun getAllExpenses(): Flow<List<ExpenseEntity>> = expenseDao.getAllExpenses()
    fun getWalletFlow(): Flow<WalletEntity?> = walletDao.getWallet()

    suspend fun initializeWalletIfNeeded(initialBalance: Double) {
        val wallet = walletDao.getWallet().first()
        if (wallet == null) {
            walletDao.insertWallet(WalletEntity(balance = initialBalance))
        }
    }

    suspend fun addExpenseAndUpdateWallet(expense: ExpenseEntity) {
        expenseDao.insertExpense(expense)
        val wallet = walletDao.getWallet().first() // safely get current wallet once
        wallet?.let {
            val newBalance = it.balance - expense.amount
            walletDao.insertWallet(WalletEntity(balance = newBalance))
        }
    }

    suspend fun addMoneyToWallet(amount: Double) {
        val wallet = walletDao.getWallet().first()
        wallet?.let {
            val newBalance = it.balance + amount
            walletDao.insertWallet(WalletEntity(balance = newBalance))
        }
    }

    suspend fun deleteExpenseAndUpdateWallet(expense: ExpenseEntity) {
        expenseDao.deleteExpense(expense.id)
        val wallet = walletDao.getWallet().first() // safely get current wallet once
        wallet?.let {
            val newBalance = it.balance + expense.amount
            walletDao.insertWallet(WalletEntity(balance = newBalance))
        }
    }
}
