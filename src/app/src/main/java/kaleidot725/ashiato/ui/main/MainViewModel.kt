package kaleidot725.ashiato.ui.main

import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable
import kaleidot725.ashiato.di.repository.DateTimeRepository
import kaleidot725.ashiato.di.repository.LocationRepository
import kaleidot725.ashiato.di.repository.PictureRepository

class MainViewModel(private val navigator: MainNavigator,
                    private val pictureRepository: PictureRepository
) : ViewModel(), Disposable {

    private var disposed: Boolean = false

    val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            kaleidot725.ashiato.R.id.action_home -> {
                navigator.navigateHome()
                true
            }
            kaleidot725.ashiato.R.id.action_history -> {
                navigator.navigateHistory()
                true
            }
            kaleidot725.ashiato.R.id.action_setting -> {
                navigator.navigateSettingList()
                true
            }
            else -> {
                false
            }
        }
    }

    fun takePhoto(view: View) {
        pictureRepository.take(pictureRepository.newPicture())
        navigator.navigateCamera()
    }

    override fun dispose() {
        if (disposed) {
            throw IllegalStateException("already disposed")
        }

        disposed = true
    }

    override fun isDisposed(): Boolean {
        return disposed
    }
}