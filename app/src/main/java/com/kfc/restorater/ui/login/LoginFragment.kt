package com.kfc.restorater.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kfc.restorater.R
import com.kfc.restorater.databinding.FragmentLoginBinding
import com.kfc.restorater.factory.ViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelFactory.create(LoginViewModel::class.java)
        binding.viewmodel = loginViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //TODO

        // When user is logged in, redirect to user (@+id/navigation_user)
        loginViewModel.success.addOnPropertyChangedCallback(object :
            androidx.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: androidx.databinding.Observable?, propertyId: Int) {
                if (loginViewModel.success.get() == true) {
                    // redirect to user (@+id/navigation_user) (action_navigation_login_to_navigation_home)
                    findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
                }
            }
        })

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}