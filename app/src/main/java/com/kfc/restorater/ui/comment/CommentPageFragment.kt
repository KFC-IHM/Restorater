package com.kfc.restorater.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kfc.restorater.databinding.FragmentCommentPageBinding
import com.kfc.restorater.factory.ViewModelFactory

class CommentPageFragment : Fragment() {

    private lateinit var commentViewModel: CommentPageViewModel
    private var _binding: FragmentCommentPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCommentPageBinding.inflate(inflater, container, false)
        commentViewModel = ViewModelFactory.create(CommentPageViewModel::class.java)
        binding.viewmodel = commentViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        findNavController()
        if (container != null) {
            commentViewModel.navController = findNavController()
        }
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}