package kaleidot725.highestpeaks.main.settinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.model.repository.Menu

class MenuViewModelFactory(menu : Menu) : ViewModelProvider.Factory {
    private val menu : Menu = menu

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == MenuViewModel::class.java) {
            return MenuViewModel(menu) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}