package com.meimtiaz.searchapp

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.meimtiaz.common.base.BaseFragment
import com.meimtiaz.common.extfun.IntentKey
import com.meimtiaz.common.extfun.clickWithDebounce
import com.meimtiaz.common.extfun.setUpVerticalRecyclerView
import com.meimtiaz.common.utils.autoCleared
import com.meimtiaz.searchapp.databinding.FragmentSearchAppBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchAppFragment:BaseFragment<FragmentSearchAppBinding>(), AppSelectionAdapter.OnAdapterClickListener {

    override fun viewBindingLayout(): FragmentSearchAppBinding = FragmentSearchAppBinding.inflate(layoutInflater)

    private var adapter by autoCleared<AppSelectionAdapter>()
    private val args by navArgs<SearchAppFragmentArgs>()


    override fun initializeView(savedInstanceState: Bundle?) {
        /** @toolBarInc common toolbar title text changed **/
        binding.toolBarInc.toolbarTitleTv.text =
            getString(com.meimtiaz.assets.R.string.title_search_app)

        /** @ClickWithDebounce prevent double click at the same time
         *  after click pop back stack **/
        binding.toolBarInc.toolbarBackIV.clickWithDebounce {
            findNavController().popBackStack()
        }

        /** @ClickWithDebounce prevent double click at the same time
         *  after click clearing searched text **/
        binding.clearAppSearchEtTextIv.clickWithDebounce {
            binding.appSearchEt.setText("")
        }

        adapter = AppSelectionAdapter(requireContext())
        adapter.setOnAdapterClickListener(this)
        adapter.setInstalledAppList(getUserInstalledApplications())
        requireContext().setUpVerticalRecyclerView(binding.appRv, adapter)
        binding.appSearchEt.afterTextChange()
        binding.appSearchEt.setText(args.selectedAppName)
    }


    /**
     * ...search application by entered text
     * ...set delay 1s after write character
     */
    private fun EditText.afterTextChange() {
        this.doOnTextChanged { text, _, _, count ->
            filter(text = text.toString())
            binding.clearAppSearchEtTextIv.isVisible = text?.isNotEmpty() == true
        }
    }

    /**
     * ...filter list after getting appSearchEt text
     */
    private fun filter(text: String?) {
        val searchResultAppList: MutableList<ApplicationInfo> = ArrayList()
        for (appPackage in getUserInstalledApplications()) {
            if (appPackage.loadLabel(requireActivity().packageManager).toString().lowercase().contains(text.toString().lowercase())) {
                searchResultAppList.add(appPackage)
            }
        }
        adapter.setInstalledAppList(searchResultAppList)
    }

    /**
     * Return list of installed user applications
     */
    private fun getUserInstalledApplications(): List<ApplicationInfo> {
        // Get installed applications
        val packageManager: PackageManager = requireContext().packageManager
        val installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        // Remove system apps
        val it = installedApplications.iterator()
        while (it.hasNext()) {
            val appInfo = it.next()
            if (appInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0) {
                it.remove()
            }
        }

        // Return installed applications
        return installedApplications
    }

    override fun onLocationItemClick(packageInfo: ApplicationInfo) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(IntentKey.selectedAppInfo, packageInfo)
        findNavController().popBackStack()
    }


}