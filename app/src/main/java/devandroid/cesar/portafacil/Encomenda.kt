package devandroid.cesar.portafacil

import java.util.Date // If you're using Date

data class Encomenda(
    val id: Int,
    val descricao: String,
    val dataRecebimento: Long? = null // Milliseconds since epoch, nullable
    // Or if you're using Date:
    // val dataRecebimento: Date? = null
)