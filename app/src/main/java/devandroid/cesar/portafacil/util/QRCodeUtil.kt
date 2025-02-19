import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.WriterException

object QRCodeUtil {
    fun gerarQRCode(texto: String, largura: Int, altura: Int): Bitmap? {
        return try {
            val bitMatrix: BitMatrix = MultiFormatWriter().encode(
                texto,
                BarcodeFormat.QR_CODE,
                largura,
                altura
            )

            val bitmap = Bitmap.createBitmap(largura, altura, Bitmap.Config.RGB_565)
            val preto = 0xFF000000.toInt()
            val branco = 0xFFFFFFFF.toInt()

            for (x in 0 until largura) {
                for (y in 0 until altura) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) preto else branco)
                }
            }
            bitmap
        } catch (e: WriterException) {
            e.printStackTrace()
            null
        }
    }
}
