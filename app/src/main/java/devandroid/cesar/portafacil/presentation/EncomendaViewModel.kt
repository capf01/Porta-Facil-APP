import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import devandroid.cesar.portafacil.Encomenda
import devandroid.cesar.portafacil.data.EncomendaRepository // Certifique-se de importar o repositório

class EncomendaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EncomendaRepository
    val todasEncomendas: LiveData<List<Encomenda>>

    init {
        repository = EncomendaRepository(application) // Inicializa o repositório
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
        // Correção: use observeForever para obter os dados do LiveData
        repository.getTodasEncomendas().observeForever { encomendas ->
            encomendas?.forEach { encomenda ->
                // Correção: verifique se dataRecebimento não é nulo antes de calcular
                encomenda.dataRecebimento?.let { dataRecebimento ->
                    val diasRestantes = TimeUnit.MILLISECONDS.toDays(dataRecebimento - System.currentTimeMillis())

                    if (diasRestantes in 0..2) {
                        // Enviar alerta (exemplo: emitir um evento LiveData ou usar NotificationManager)
                    }
                }
            }
        }
    }
}