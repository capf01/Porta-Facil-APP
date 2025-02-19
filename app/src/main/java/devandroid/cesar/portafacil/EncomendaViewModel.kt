package devandroid.cesar.portafacil.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import devandroid.cesar.portafacil.data.EncomendaRepository
import devandroid.cesar.portafacil.Encomenda
class EncomendaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EncomendaRepository
    val todasEncomendas: LiveData<List<Encomenda>>

    init {
        repository = EncomendaRepository(application)
        todasEncomendas = repository.getTodasEncomendas()
    }

    fun inserir(encomenda: Encomenda) = viewModelScope.launch {
        repository.inserir(encomenda)
    }

    fun atualizar(encomenda: Encomenda) = viewModelScope.launch {
        repository.atualizar(encomenda)
    }

    fun deletar(encomenda: Encomenda) = viewModelScope.launch {
        repository.deletar(encomenda)
    }

    fun verificarPrazos() = viewModelScope.launch {
        repository.getTodasEncomendas()
            .observeForever { encomendas: List<Encomenda>? -> // Explicit type here
                encomendas?.forEach { encomenda ->
                    val dataRecebimento = encomenda.dataRecebimento ?: return@forEach
                    val diasRestantes =
                        (dataRecebimento - System.currentTimeMillis()) / (1000 * 60 * 60 * 24)
                    if (diasRestantes <= 2) {
                        // ... your alert logic
                    }
                }
            }
    }
}