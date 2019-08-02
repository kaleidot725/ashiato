package kaleidot725.highestpeaks.ui.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.repository.DeveloperRepository

class ContactViewModelFactory(developerRepository : DeveloperRepository) : ViewModelProvider.Factory {
    private val developerRepository : DeveloperRepository = developerRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == ContactViewModel::class.java) {
            return ContactViewModel(developerRepository.all()) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}