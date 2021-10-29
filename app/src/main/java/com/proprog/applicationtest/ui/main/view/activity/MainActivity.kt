package com.proprog.applicationtest.ui.main.view.activity

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.proprog.applicationtest.data.model.StackResponse
import com.proprog.applicationtest.databinding.ActivityMainBinding
import com.proprog.applicationtest.ui.BaseActivity
import com.proprog.applicationtest.ui.main.adapter.StackAnswersAdapter
import com.proprog.applicationtest.ui.main.adapter.StackPagingAdapter
import com.proprog.applicationtest.ui.main.view.fragment.LoadingDialogFragment
import com.proprog.applicationtest.ui.main.viewmodel.StackViewModel
import com.proprog.applicationtest.utils.ApiResult.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding, StackViewModel>() {

    private lateinit var loadingDialogFragment: LoadingDialogFragment
    private lateinit var stackAdapter: StackAnswersAdapter
    private lateinit var pagedStackAdapter: StackPagingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stackAdapter = StackAnswersAdapter(this)
        pagedStackAdapter = StackPagingAdapter(this)
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
                setHasFixedSize(true)
                adapter = pagedStackAdapter
//                adapter = stackAdapter
            }
            fetchAnswers(siteName)
        }

    }

    private fun fetchAnswers(siteName: String) {
        lifecycleScope.launch {
           // pagedStackAdapter.refresh()
            try {
//               pagedStackAdapter.loadStateFlow.collect {
//                   Log.d(TAG, "fetchAnswers: ${it.source}")
//               }

               viewModel.getStackAnswers(siteName).collect {
                   pagedStackAdapter.submitData(it)
                   Log.d(TAG, "fetchAnswers: <<_>>")
               }
           }catch (e:Exception) {
               Log.d(TAG, "fetchAnswers: ${e.message}")
           }

        }
        /**
        viewModel.getStackAnswers("1", "5", siteName)
        .observe(this, {
        if (it.status == Status.LOADING) {
        Log.d(TAG, "fetchAnswers: loading ........")
        loadingDialogFragment.show(supportFragmentManager, LoadingDialogFragment.TAG)
        }
        if (it.status == Status.SUCCESS) {
        val stackResponse = it.data as StackResponse
        stackAdapter.apply {
        updateList(stackResponse.items)
        notifyDataSetChanged()
        }
        loadingDialogFragment.dismiss()
        }
        if (it.status == Status.ERROR) {
        Log.d(TAG, "fetchAnswers: failed ${it.message}")
        loadingDialogFragment.dismiss()
        }

        })
         **/
    }

    companion object {
        private const val TAG: String = "MainActivity"
    }

    override fun getViewModelClass() = StackViewModel::class.java

    override fun bindLayout() = ActivityMainBinding.inflate(layoutInflater)
}