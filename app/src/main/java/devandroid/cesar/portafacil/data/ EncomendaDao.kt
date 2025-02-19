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
}