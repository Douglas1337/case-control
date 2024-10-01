package br.skz.casecontrol.viewModels
import br.skz.casecontrol.classes.ProcessEntity

import ImportantDates
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.skz.casecontrol.classes.RetrofitInstance
import kotlinx.coroutines.launch

class ProcessViewModel : ViewModel() {
    private val _processes = mutableListOf<ProcessEntity>()
    val processes: List<ProcessEntity> get() = _processes

    private val _importantDates = mutableListOf<ImportantDates>()
    val importantDates: List<ImportantDates> get() = _importantDates

    fun addProcess(process: ProcessEntity) {
        _processes.add(process)
    }

    fun updateProcess(process: ProcessEntity) {
        _processes.replaceAll { if (it.id == process.id) process else it }
    }

    fun deleteProcess(processId: Int) {
        _processes.removeAll { it.id == processId }
    }

    fun fetchImportantDates(processNumber: String) {
        viewModelScope.launch {
            try {
                val dates = RetrofitInstance.api.getImportantDates(processNumber)
                _importantDates.clear()
                _importantDates.addAll(dates)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}