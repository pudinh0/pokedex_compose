package adel.mendez.pokedex_compose.view.components

import adel.mendez.pokedex_compose.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ImagePickerWithPreview(onUriChanged: (uri: Uri?) -> Unit, currentUri : Uri?, pickerModifier: Modifier?, previewModifier: Modifier?){


    val context = LocalContext.current
    val selectorModifier = pickerModifier ?: Modifier
    val imagePreviewModifier = previewModifier ?: Modifier

    var imageBitmap  by remember { mutableStateOf<ImageBitmap?>(null) }
    LaunchedEffect(currentUri){
        currentUri?.let {
            uri ->
            imageBitmap = withContext(Dispatchers.IO){
                try {
                    val bitmap: Bitmap? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                        val source = ImageDecoder.createSource ( context.contentResolver, uri)
                        ImageDecoder.decodeBitmap(source)
                    } else{
                        context.contentResolver.openInputStream(uri)?.use {
                            BitmapFactory.decodeStream(it)
                        }
                    }
                    bitmap?.asImageBitmap()
                }catch (e: Exception){
                    e.printStackTrace()
                    null
                }
            }
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri ->  onUriChanged(uri)}
    )

    Button({imagePickerLauncher.launch(
        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
    )}){
        Text("Selecciona una imagen")
    }

    imageBitmap?.let {
        Image(bitmap = it,
            contentDescription = "Image preview",
            modifier = imagePreviewModifier,
            contentScale = ContentScale.FillHeight
        )
    }

    if (imageBitmap == null) {
        Image(
            painterResource(R.drawable.wtp),
            contentDescription = "Image placeholder",
            modifier = imagePreviewModifier
        )
    }
}