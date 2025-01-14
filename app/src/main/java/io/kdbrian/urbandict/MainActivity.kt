package io.kdbrian.urbandict

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.data.remote.firebase.WordsRepo
import io.kdbrian.urbandict.presentation.util.Resource
import io.kdbrian.urbandict.presentation.viewmodel.WordViewModel
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UrbanDictTheme {

                LocalViewModelStoreOwner.current?.let { owner ->

                    val wordsViewModel = viewModel<WordViewModel>(
                        viewModelStoreOwner = owner,
                        factory = WordViewModel.Factory(wordRepository = WordsRepo()),
                        key = "WordViewModel"
                    )
                    val scope = rememberCoroutineScope()
                    val words by wordsViewModel.livewords.collectAsState(initial = Resource.Loading())

                    val onWordAdd: (UrbanWord) -> Unit = { word ->
                        wordsViewModel.addWord(word)
                    }

                    var showAdd by remember { mutableStateOf(false) }
                    var showWord by remember { mutableStateOf(false) }
                    val selectedWord by remember { mutableStateOf<UrbanWord?>(null) }

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                        Surface(
                            onClick = {},
                            tonalElevation = 12.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.TopCenter)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(onClick = { showAdd = !showAdd }) {
                                    Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                                }
                            }
                        }

                        AnimatedContent(
                            targetState = words,
                            label = "words prev"
                        ) { wordsResource ->
                            when (wordsResource) {
                                is Resource.Error -> Text(text = wordsResource.message.toString())
                                is Resource.Loading -> CircularProgressIndicator()
                                is Resource.Success -> {
                                    Column {
                                        wordsResource.data?.forEach { word ->
                                            Text(
                                                text = word.word,
                                                modifier = Modifier.clickable { })
                                        }
                                    }
                                }
                            }

                        }

                        if (showAdd) {
                            val sheetState = rememberModalBottomSheetState()

                            var title by remember { mutableStateOf("") }
                            var description by remember { mutableStateOf("") }

                            ModalBottomSheet(onDismissRequest = {
                                scope.launch {
                                    sheetState.hide()
                                    showAdd = false
                                }
                            }, sheetState = sheetState) {

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp)
                                ) {

                                    OutlinedTextField(
                                        value = title,
                                        onValueChange = { title = it },
                                        label = { Text(text = "whats the word") },
                                        modifier = Modifier.fillMaxWidth()

                                    )

                                    OutlinedTextField(
                                        value = description,
                                        onValueChange = { description = it },
                                        label = { Text(text = "describe it!!") },
                                        modifier = Modifier.fillMaxWidth()

                                    )

                                    Button(onClick = {
                                        onWordAdd(
                                            UrbanWord(
                                                word = title,
                                                definition = description
                                            )
                                        )
                                        scope.launch {
                                            sheetState.hide()
                                            showAdd = false
                                        }
                                    }) {
                                        Text(text = "Add")
                                    }
                                }
                            }

                        }

                        if (showWord) {
                            val sheetState = rememberModalBottomSheetState()
                            ModalBottomSheet(
                                onDismissRequest = {
                                    scope.launch {
                                        sheetState.hide()
                                        showWord = false
                                    }
                                },
                                sheetState = sheetState
                            ) {

                                ConstraintLayout {

                                    val (title, desc, missing, likeGroup) = createRefs()


                                    selectedWord?.let { word ->

                                        Text(
                                            text = word.word,
                                            fontSize = 30.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            modifier = Modifier.constrainAs(title) {
                                                top.linkTo(parent.top, 24.dp)
                                                start.linkTo(parent.start, 24.dp)
                                            }
                                        )


                                        Text(
                                            text = word.definition,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Light,
                                            modifier = Modifier.constrainAs(desc) {
                                                top.linkTo(title.top, 24.dp)
                                                start.linkTo(parent.start, 24.dp)
                                            }
                                        )

                                        Row(
                                            modifier = Modifier
                                                .constrainAs(likeGroup) {
                                                    top.linkTo(desc.bottom, 30.dp)
                                                    bottom.linkTo(parent.bottom, 24.dp)
                                                    start.linkTo(parent.start, 24.dp)
                                                    end.linkTo(parent.end, 24.dp)
                                                }
                                                .wrapContentWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {

                                            IconButton(onClick = {}) {
                                                Icon(
                                                    imageVector = Icons.Outlined.ThumbUp,
                                                    contentDescription = null
                                                )
                                            }

                                        }
                                    } ?: kotlin.run {
                                        Text(
                                            text = "Missing content",
                                            modifier = Modifier.constrainAs(missing) {
                                                top.linkTo(parent.top)
                                                end.linkTo(parent.end)
                                                start.linkTo(parent.start)
                                                bottom.linkTo(parent.bottom)
                                            }
                                        )
                                    }

                                }

                            }


                        }

                    }

                }

            }
        }
    }
}
