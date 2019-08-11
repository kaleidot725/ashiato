package kaleidot725.highestpeaks.ui.edit.position

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.repository.PositionRepository
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.di.service.PositionEditor
import java.lang.IllegalArgumentException

class PositionViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val positionEditor : PositionEditor,
    private val positionRepository: PositionRepository
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PositionViewModel::class.java) {
            return PositionViewModel(pictureEditor, positionEditor, positionRepository) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}