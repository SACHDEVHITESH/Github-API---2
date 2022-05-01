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
import com.example.githubapiissues.data.remote.api.models.CommentsModel
import com.example.githubapiissues.presentation.viewmodel.CommentsViewModel
import githubapiissues.R
import githubapiissues.databinding.CommentsFragmentBinding

class CommentsFragment : Fragment() {

    private val commentsViewModel by activityViewModels<CommentsViewModel>()
    private lateinit var commentsViewer: RecyclerView
    private lateinit var binding: CommentsFragmentBinding
    private val args by lazy { CommentsFragmentArgs.fromBundle(requireArguments()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.comments_fragment, container, false)
        binding.commentsViewModel = commentsViewModel
        commentsViewer = binding.root.findViewById(R.id.commentsRecyclerView)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        commentsViewModel.getCommentsInfo(args.number).observe(this) {
            if (it != null && it.isNotEmpty()) {
                showComments(it)
            } else {
                binding.progressBar.visibility = View.GONE
                binding.noComments.visibility = View.VISIBLE
                binding.commentsRecyclerView.visibility = View.GONE
            }
        }
    }

    private fun showComments(commentsList: List<CommentsModel>?) {
        val commentsListAdapter = CommentsListAdapter()
        commentsViewer.layoutManager = LinearLayoutManager(context)
        commentsViewer.adapter = commentsListAdapter

        if (commentsList != null) {
            commentsListAdapter.setData(commentsList)
            binding.progressBar.visibility = View.GONE
            binding.noComments.visibility = View.GONE
            binding.commentsRecyclerView.visibility = View.VISIBLE
        }
    }

}
