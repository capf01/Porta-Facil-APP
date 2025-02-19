import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.lifecycle.LiveData
import devandroid.cesar.portafacil.Encomenda
import devandroid.cesar.portafacil.Item
@Dao
interface EncomendaDao {
    @Insert
    suspend fun inserir(encomenda: Encomenda)

    @Update
    suspend fun atualizar(encomenda: Encomenda)

    @Delete
    suspend fun deletar(encomenda: Encomenda)

    @Query("SELECT * FROM encomenda_table ORDER BY dataRecebimento DESC")
    fun getTodasEncomendas(): LiveData<List<Encomenda>>

    // Outras consultas que você pode precisar

    @Query("SELECT * FROM encomenda_table WHERE id = :id")
    suspend fun getEncomendaPorId(id: Int): Encomenda?

    @Query("SELECT COUNT(*) FROM encomenda_table")
    suspend fun getTotalEncomendas(): Int

    @Query("SELECT * FROM encomenda_table WHERE descricao LIKE :descricao")
    fun buscarEncomendasPorDescricao(descricao: String): LiveData<List<Encomenda>>

    // Exemplo de uso de transação (para operações mais complexas)
    @Transaction
    suspend fun inserirEncomendaComItens(encomenda: Encomenda, itens: List<Item>) {
        inserir(encomenda)
        // Outras operações relacionadas (ex: inserir itens da encomenda em outra tabela)
    }
}