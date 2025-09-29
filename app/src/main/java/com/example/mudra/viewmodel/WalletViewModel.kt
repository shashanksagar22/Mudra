import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.example.mudra.data.ExpenseEntity
import com.example.mudra.repository.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class WalletViewModel(private val repository: ExpenseRepository) : ViewModel() {


    val expensesFlow = repository.getAllExpenses()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val walletBalanceFlow = repository.getWalletFlow()
        .map { it?.balance ?: 0.0 }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

    fun initializeWalletIfNeeded(initialBalance: Double = 0.0) {
        viewModelScope.launch { repository.initializeWalletIfNeeded(initialBalance) }
    }

    fun addExpense(name: String, amount: Double, dateMillis: Long) {
        viewModelScope.launch {
            repository.addExpenseAndUpdateWallet(
                ExpenseEntity(name = name, amount = amount, dateMillis = dateMillis)
            )
        }
    }

    fun addMoneyToWallet(amount: Double) {
        viewModelScope.launch { repository.addMoneyToWallet(amount) }
    }

    fun deleteExpense(expense: ExpenseEntity) {
        viewModelScope.launch { repository.deleteExpenseAndUpdateWallet(expense) }
    }
}
