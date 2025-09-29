package com.example.mudra.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert suspend fun insertExpense(expense: ExpenseEntity): Long
    @Query("SELECT * FROM expenses ORDER BY dateMillis DESC")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>
    @Query("DELETE FROM expenses WHERE id = :id")
    suspend fun deleteExpense(id: Long): Int
}
