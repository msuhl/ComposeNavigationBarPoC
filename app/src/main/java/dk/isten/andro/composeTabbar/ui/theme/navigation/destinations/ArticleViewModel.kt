package dk.isten.andro.composeTabbar.ui.theme.navigation.destinations

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    init {
        val topicId: String = checkNotNull(savedStateHandle[ArticleDestination.articleId])
        Log.d("Svend ", topicId)


    }
}
