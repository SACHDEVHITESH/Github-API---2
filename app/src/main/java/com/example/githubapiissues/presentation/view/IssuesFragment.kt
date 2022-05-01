package com.example.githubapiissues.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapiissues.data.remote.api.models.IssuesModel
import com.example.githubapiissues.presentation.viewmodel.IssuesViewModel
import githubapiissues.R
import githubapiissues.databinding.IssuesFragmentBinding

class IssuesFragment : Fragment() {

    private val issuesViewModel by activityViewModels<IssuesViewModel>()
    private lateinit var issuesViewer: RecyclerView
    private lateinit var binding: IssuesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.issues_fragment, container, false)
        binding.issuesViewModel = issuesViewModel
        issuesViewer = binding.root.findViewById(R.id.issuesRecyclerView)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        issuesViewModel.getIssuesInfo().observe(this) {
            showIssues(it)
        }
    }

    private fun showIssues(issuesList: List<IssuesModel>?) {
        val issuesListAdapter = IssuesListAdapter()
        issuesViewer.layoutManager = LinearLayoutManager(context)
        issuesViewer.adapter = issuesListAdapter

        if (issuesList != null) {
            issuesListAdapter.setData(issuesList)
            binding.progressBar.visibility = View.GONE
        }
    }

}
