import android.Manifest
import android.content.pm.PackageManager
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.journeyapps.barcodescanner.ScanOptions
import com.journeyapps.barcodescanner.ScanContract

@Composable
fun QRCodeScanner(onQRCodeScanned: (String) -> Unit) {
    val context = LocalContext.current
    var scanResult by remember { mutableStateOf("") }

    // Launcher para solicitar permissão da câmera
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            scannerLauncher.launch(ScanOptions())
        }
    }

    // Launcher para abrir o scanner
    val scannerLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        result.contents?.let {
            scanResult = it
            onQRCodeScanned(it)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                scannerLauncher.launch(ScanOptions())
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }) {
            Text("Escanear QR Code")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (scanResult.isNotEmpty()) {
            Text("Resultado: $scanResult", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
