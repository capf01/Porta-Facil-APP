@Entity(tableName = "encomenda_table")
data class Encomenda(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val destinatario: String,
    val codigo: String,
    val status: String,
    val dataRecebimento: Long
)