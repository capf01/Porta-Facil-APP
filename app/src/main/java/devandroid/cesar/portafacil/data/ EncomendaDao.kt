package devandroid.cesar.portafacil.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface EncomendaDao {
    @Query("SELECT * FROM encomendas")
    fun getTodasEncomendas(): LiveData<List<Encomenda>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserir(encomenda: Encomenda)

    @Update
    suspend fun atualizar(encomenda: Encomenda)

    @Query("DELETE FROM encomendas WHERE id = :id")
    suspend fun deletar(id: Int)
}