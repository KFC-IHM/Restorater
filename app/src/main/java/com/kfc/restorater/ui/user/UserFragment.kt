package com.kfc.restorater.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kfc.restorater.databinding.FragmentUserBinding
import com.kfc.restorater.factory.ViewModelFactory

class UserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private var _binding: FragmentUserBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        userViewModel = ViewModelFactory.create(UserViewModel::class.java)
        binding.viewmodel = userViewModel

        if (userViewModel.loginRepository.user.get() == null) {
            // redirect to login (@+id/navigation_login) (action_navigation_user_to_navigation_login)
            findNavController().navigate(com.kfc.restorater.R.id.navigation_login)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}