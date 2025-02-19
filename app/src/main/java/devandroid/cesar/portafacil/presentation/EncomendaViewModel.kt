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
        val encomendas = repository.buscarTodasEncomendas() // Função `suspend` no repositório
        encomendas.forEach { encomenda ->
            val diasRestantes = TimeUnit.MILLISECONDS.toDays(encomenda.dataRecebimento - System.currentTimeMillis())

            if (diasRestantes in 0..2) {
                // Enviar alerta (exemplo: emitir um evento LiveData ou usar NotificationManager)
            }
        }
    }
}
