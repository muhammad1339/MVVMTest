package com.proprog.applicationtest.ui.main.view.activity

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.idanatz.oneadapter.OneAdapter
import com.proprog.applicationtest.data.model.StackResponse
import com.proprog.applicationtest.databinding.ActivityMainBinding
import com.proprog.applicationtest.ui.BaseActivity
import com.proprog.applicationtest.ui.main.adapter.ItemsModule
import com.proprog.applicationtest.ui.main.view.fragment.LoadingDialogFragment
import com.proprog.applicationtest.ui.main.viewmodel.StackViewModel
import com.proprog.applicationtest.utils.ApiResult.*

class MainActivity : BaseActivity<ActivityMainBinding, StackViewModel>() {

    private lateinit var loadingDialogFragment: LoadingDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialogFragment = LoadingDialogFragment().apply {
            isCancelable = false
        }

        binding.btnFetchAnswers.setOnClickListener {
            var siteName = binding.editSiteName.text.toString()
            if (TextUtils.isEmpty(siteName)) {
                siteName = "stackoverflow"
            }
            binding.rvStackAnswers.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.HORIZONTAL
                    )
                )
            }
            fetchAnswers(siteName)
        }

    }

    private fun fetchAnswers(siteName: String) {
        // TODO Apply Pagination
        viewModel.getStackAnswers("1", "30", siteName)
            .observe(this, {
                if (it.status == Status.LOADING) {
                    Log.d(Companion.TAG, "fetchAnswers: loading ........")
                    loadingDialogFragment.show(supportFragmentManager, LoadingDialogFragment.TAG)
                }
                if (it.status == Status.SUCCESS) {
                    val stackResponse = it.data as StackResponse
                    val oneAdapter = OneAdapter(binding.rvStackAnswers) {
                        itemModules += ItemsModule()
                    }
                    oneAdapter.setItems(stackResponse.items.toMutableList())
                    loadingDialogFragment.dismiss()
                    Log.d(TAG, "fetchAnswers: end success ----")
                }
                if (it.status == Status.ERROR) {
                    Log.d(TAG, "fetchAnswers: failed ${it.message}")
                    loadingDialogFragment.dismiss()
                }

            })

    }

    companion object {
        private const val TAG: String = "MainActivity"
    }

    override fun getViewModelClass() = StackViewModel::class.java

    override fun bindLayout() = ActivityMainBinding.inflate(layoutInflater)
}