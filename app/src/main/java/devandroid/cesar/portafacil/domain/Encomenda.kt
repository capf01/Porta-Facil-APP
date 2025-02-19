import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "encomenda_table")
data class Encomenda(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,  // Permite null para inserções automáticas
    val destinatario: String,
    val codigo: String,
    val status: String,
    val dataRecebimento: Date // Alterado para Date
)
