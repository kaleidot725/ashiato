package kaleidot725.ashiato.ui.main.settinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.SettinglistFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SettingListFragment : Fragment() {
    private val listViewModel: SettingListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<SettinglistFragmentBinding>(
            inflater,
            R.layout.settinglist_fragment,
            container,
            false
        ).apply {
            vm = listViewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.menu_recycler_view)
        listViewModel.menus.observe(viewLifecycleOwner, Observer {
            recycler.adapter = MenuAdapter(this, it)
            recycler.layoutManager = GridLayoutManager(context, 2)
            recycler.setHasFixedSize(true)
        })

        listViewModel.event.observe(viewLifecycleOwner, Observer { event ->
            when (event) {
                SettingListViewModel.NavEvent.Setting -> navigateSetting()
                SettingListViewModel.NavEvent.License -> navigateLicense()
                SettingListViewModel.NavEvent.Contact -> navigateContact()
                SettingListViewModel.NavEvent.Privacy -> navigatePrivacy()
            }
        })

        listViewModel.load()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        listViewModel.event.removeObservers(this)
        super.onDestroy()
    }

    private fun navigateSetting() {
        val f = findNavController()
        findNavController().navigate(R.id.action_settinglist_fragment_to_settingFragment)
    }

    private fun navigateContact() {
        findNavController().navigate(R.id.action_settinglist_fragment_to_contactFragment)
    }

    private fun navigatePrivacy() {
        findNavController().navigate(R.id.action_settinglist_fragment_to_privacyFragment)
    }

    private fun navigateLicense() {
        LibsBuilder()
            .withActivityTitle("License")
            .withShowLoadingProgress(false)
            .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR).start(context)
    }
}
