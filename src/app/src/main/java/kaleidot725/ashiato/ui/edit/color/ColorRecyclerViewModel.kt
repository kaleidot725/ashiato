package kaleidot725.ashiato.ui.edit.color

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.ashiato.data.service.picture.Color
import kaleidot725.ashiato.data.service.picture.ColorEditor
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kotlinx.coroutines.launch

class ColorRecyclerViewModel(
    private val pictureEditor: PictureEditor,
    private val colorEditor: ColorEditor,
    private val color: Color
) : ViewModel() {

    private val _detail: MutableLiveData<String> = MutableLiveData()
    val detail: LiveData<String> get() = _detail

    private val _txtColor: MutableLiveData<Int> = MutableLiveData()
    val txtColor: LiveData<Int> get() = _txtColor

    private val _enabled: MutableLiveData<Boolean> = MutableLiveData()
    val enabled: LiveData<Boolean> get() = _enabled

    private val compositeDisposable = CompositeDisposable()

    init {
        _txtColor.value =
            if (color.value == android.graphics.Color.WHITE) android.graphics.Color.BLACK else color.value
        _detail.value = color.detail
        _enabled.value = (colorEditor.lastEnabled == color)
        compositeDisposable.add(
            colorEditor.enabled.subscribe { _enabled.postValue((it == color)) }
        )
    }

    fun click(v: View) {
        viewModelScope.launch {
            colorEditor.enable(color)
            pictureEditor.modifyColor(color.value)
            pictureEditor.commit()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}