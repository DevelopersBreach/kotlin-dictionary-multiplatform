import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.home.HomeScreenUI
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.home.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToTopicList: () -> Unit,
) {
    val topicsState = viewModel.topics.collectAsState()

    HomeScreenUI(
        topics = topicsState.value,
        onViewAllClick = navigateToTopicList,
    )
}