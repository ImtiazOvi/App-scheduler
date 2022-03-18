package com.meimtiaz.searchapp

import android.content.pm.PackageInfo
import android.os.Bundle
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.meimtiaz.common.base.BaseFragment
import com.meimtiaz.common.extfun.clickWithDebounce
import com.meimtiaz.common.extfun.setUpVerticalRecyclerView
import com.meimtiaz.common.utils.autoCleared
import com.meimtiaz.searchapp.databinding.FragmentSearchAppBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchAppFragment:BaseFragment<FragmentSearchAppBinding>(), AppSelectionAdapter.OnAdapterClickListener {

    override fun viewBindingLayout(): FragmentSearchAppBinding = FragmentSearchAppBinding.inflate(layoutInflater)

    private var adapter by autoCleared<AppSelectionAdapter>()
    private var packages = listOf<PackageInfo>()

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

        packages = requireActivity().packageManager.getInstalledPackages(0)
        adapter = AppSelectionAdapter(requireContext())
        adapter.setOnAdapterClickListener(this)
        adapter.setInstalledAppList(packages)
        requireContext().setUpVerticalRecyclerView(binding.appRv, adapter)
        binding.appSearchEt.afterTextChange()
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
        val searchResultAppList: MutableList<PackageInfo> = ArrayList()
        for (appPackage in packages) {
            if (appPackage.applicationInfo.loadLabel(requireActivity().packageManager).toString().lowercase().contains(text.toString().lowercase())) {
                searchResultAppList.add(appPackage)
            }
        }
        adapter.setInstalledAppList(searchResultAppList)
    }


    override fun onLocationItemClick(packageInfo: PackageInfo) {}

}