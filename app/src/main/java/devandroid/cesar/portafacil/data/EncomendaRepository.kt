package devandroid.cesar.portafacil.data

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EncomendaRepository(application: Application) {
    private val encomendaDao: EncomendaDao
    private val todasEncomendas: LiveData<List<Encomenda>>

    init {
        val db = AppDatabase.getDatabase(application)
        encomendaDao = db.encomendaDao()
        todasEncomendas = encomendaDao.getTodasEncomendas()
    }

    fun getTodasEncomendas(): LiveData<List<Encomenda>> = todasEncomendas

    fun inserir(encomenda: Encomenda) {
        CoroutineScope(Dispatchers.IO).launch {
            encomendaDao.inserir(encomenda)
        }
    }

    fun atualizar(encomenda: Encomenda) {
        CoroutineScope(Dispatchers.IO).launch {
            encomendaDao.atualizar(encomenda)
        }
    }

    fun deletar(encomenda: Encomenda) {
        CoroutineScope(Dispatchers.IO).launch {
            encomendaDao.deletar(encomenda)
        }
    }
}
