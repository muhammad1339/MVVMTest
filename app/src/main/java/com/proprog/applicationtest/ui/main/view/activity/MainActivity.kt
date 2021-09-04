package com.proprog.applicationtest.ui.main.view.activity

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.proprog.applicationtest.R
import com.proprog.applicationtest.data.model.StackResponse
import com.proprog.applicationtest.data.repository.StackRepository
import com.proprog.applicationtest.databinding.ActivityMainBinding
import com.proprog.applicationtest.ui.main.adapter.StackAnswersAdapter
import com.proprog.applicationtest.ui.main.view.fragment.LoadingDialogFragment
import com.proprog.applicationtest.ui.main.viewmodel.StackViewModel
import com.proprog.applicationtest.ui.main.viewmodel.StackViewModelFactory
import com.proprog.applicationtest.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var stackViewModel: StackViewModel
    lateinit var loadingDialogFragment: LoadingDialogFragment
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var view: View
    private lateinit var stackAdapter: StackAnswersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        view = activityMainBinding.root
        setContentView(view)
        stackAdapter = StackAnswersAdapter(this)
        val stackRepository = StackRepository()

        stackViewModel = ViewModelProvider(this, StackViewModelFactory(stackRepository))
            .get(StackViewModel::class.java)
        loadingDialogFragment = LoadingDialogFragment().apply {
            isCancelable = false
        }

//        var str = "my string "
//        str.let {
//            Log.d(TAG, "onCreate-str: ${it.length}")
//        }
        activityMainBinding.btnFetchAnswers.setOnClickListener {
            var siteName = activityMainBinding.editSiteName.text.toString()
            if (TextUtils.isEmpty(siteName)) {
                siteName = "stackoverflow"
            }
            activityMainBinding.rvStackAnswers.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.HORIZONTAL
                    )
                )
                adapter = stackAdapter
            }
            stackAdapter.updateList(ArrayList())
            fetchAnswers(siteName)
        }

    }

    private fun fetchAnswers(siteName: String) {
        stackViewModel.getStackAnswers("1", "30", siteName)
            .observe(this, {
//                Log.d(TAG, "onCreate: ${it}")
                if (it.status == Status.LOADING) {
                    Log.d(Companion.TAG, "fetchAnswers: loading ........")
                    loadingDialogFragment.show(supportFragmentManager, LoadingDialogFragment.TAG)
                }
                if (it.status == Status.SUCCESS) {
                    val stackResponse = it.data as StackResponse
                    stackAdapter.updateList(stackResponse.items.toMutableList())
                    loadingDialogFragment.dismiss()
                    Log.d(TAG, "fetchAnswers: end success ----")
                }
                if (it.status == Status.ERROR) {
                    Log.d(TAG, "fetchAnswers: failed XXXXXXXX")
                    loadingDialogFragment.dismiss()
                }

            })

    }

    companion object {
        private val TAG: String = "MainActivity"
    }
}