package kaleidot725.highestpeaks.ui.preview

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dagger.android.support.AndroidSupportInjection
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.PreviewFragmentBinding
import kaleidot725.michetimer.model.repository.PictureRepository
import javax.inject.Inject

class PageFragment : Fragment() {

    companion object {
        fun newInstance(position : Int) = PageFragment().also {
            val bundle = Bundle()
            bundle.putInt("position", position)
            it.arguments = bundle
        }
    }

    @Inject
    lateinit var repository : PictureRepository

    private lateinit var viewModel: PageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.preview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt("position")
        val factory = PageViewModelFactory(repository, position ?: 0)
        viewModel = ViewModelProviders.of(this, factory).get(PageViewModel::class.java)

        val binding = DataBindingUtil.bind<PreviewFragmentBinding>(this.view as View)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel
    }
}