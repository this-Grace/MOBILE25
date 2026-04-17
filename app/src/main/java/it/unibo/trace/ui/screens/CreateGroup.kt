package it.unibo.trace.ui.screens

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.unibo.trace.R
import it.unibo.trace.ui.composables.Header
import it.unibo.trace.ui.composables.ImageCard
import it.unibo.trace.ui.composables.PrimaryButton
import it.unibo.trace.utils.ImageUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroupScreen(
    onBack: () -> Unit = {},
    onCreate: (String, Bitmap?) -> Unit = { _, _ -> }
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { imageBitmap = ImageUtils.loadBitmapFromUri(context, it) }
            showSheet = false
        }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            bitmap?.let { imageBitmap = it }
            showSheet = false
        }

    Scaffold(
        topBar = {
            Header(title = "NEW GROUP", onBackClick = onBack)
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            val painter = if (imageBitmap != null) {
                BitmapPainter(imageBitmap!!.asImageBitmap())
            } else {
                painterResource(id = R.drawable.ic_launcher_background)
            }

            ImageCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                title = title.ifEmpty { "Group Title" },
                subtitle = if (imageBitmap == null) "Tap to add photo" else "Tap to change photo",
                imagePainter = painter,
                showLikeIcon = false,
                onClick = { showSheet = true }
            )

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Group Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = "CREATE GROUP",
                onClick = { onCreate(title, imageBitmap) }
            )
        }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 48.dp, start = 16.dp, end = 16.dp, top = 8.dp)
            ) {
                Text(
                    text = "Choose image",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
                ListItem(
                    headlineContent = { Text("Take photo") },
                    leadingContent = { Icon(Icons.Default.PhotoCamera, null) },
                    modifier = Modifier.clickable { cameraLauncher.launch(null) }
                )
                ListItem(
                    headlineContent = { Text("Gallery") },
                    leadingContent = { Icon(Icons.Default.PhotoLibrary, null) },
                    modifier = Modifier.clickable { galleryLauncher.launch("image/*") }
                )
            }
        }
    }
}
