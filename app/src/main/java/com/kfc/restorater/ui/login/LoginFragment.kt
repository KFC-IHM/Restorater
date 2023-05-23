package com.kfc.restorater.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kfc.restorater.databinding.FragmentLoginBinding

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

        // When user is logged in, redirect to user (@+id/navigation_user)
        loginViewModel.success.addOnPropertyChangedCallback(object :
            androidx.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: androidx.databinding.Observable?, propertyId: Int) {
                if (loginViewModel.success.get() == true) {
                    // redirect to user (@+id/navigation_user)
                    androidx.navigation.Navigation.findNavController(requireActivity(), com.kfc.restorater.R.id.nav_host_fragment_activity_main).navigate(com.kfc.restorater.R.id.navigation_user)
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