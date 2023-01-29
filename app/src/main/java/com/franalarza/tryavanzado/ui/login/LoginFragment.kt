package com.franalarza.tryavanzado.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.franalarza.tryavanzado.data.local.PreferencesManager
import com.franalarza.tryavanzado.databinding.FragmentLoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginFragmentBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var sharedPreferences: PreferencesManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = PreferencesManager(requireContext())
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        binding.LoginButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            if (checkLocalToken() == true) {
                navigateToHeroesList()
            } else {
                viewModel.login(
                    binding.editTextLoginName.text.toString(),
                    binding.editTextLoginPassword.text.toString()
                )
            }
        }
    }

    private fun setObservers() {
        viewModel.liveToken.observe(viewLifecycleOwner) { it ->
            when (it) {
                is LoginState.Success -> {
                    navigateToHeroesList()
                    sharedPreferences.saveAuthToken(it.token)
                }

                is LoginState.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.ErrorMessage, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun checkLocalToken(): Boolean? {
        val localToken = sharedPreferences.fetchAuthToken()
        return localToken?.isNotEmpty()
    }

    private fun navigateToHeroesList() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHeroListFragment())
        binding.progressBar.visibility = View.VISIBLE
    }
}