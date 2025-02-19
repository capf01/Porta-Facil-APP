import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Encomenda(
    @PrimaryKey val id: Int,
    val destinatario: String,
    val codigo: String,
    val status: String,
    val dataRecebimento: Long
)
