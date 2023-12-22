package com.register.coffeapp.presentation.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.register.coffeapp.R
import com.register.coffeapp.databinding.FragmentRegisterBinding
import com.register.coffeapp.domain.entities.AuthRequest
import com.register.coffeapp.presentation.CoffeeApp
import com.register.coffeapp.presentation.MainViewModel
import com.register.coffeapp.presentation.ViewModelFactory
import javax.inject.Inject

class RegisterFragment: Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding
        get() = _binding ?: throw RuntimeException("FragmentRegisterBinding == null")

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as CoffeeApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]

        binding.buttonRegister.setOnClickListener {
            if(checkInput() || checkPasswords()) {
                viewModel.register(
                    AuthRequest(
                        binding.etEmail.text.toString(),
                        binding.etPassword.text.toString()
                    )
                )
                launchSignInFragment()
            }
        }
    }

    private fun checkInput(): Boolean {
        with(binding) {
            if(etEmail.text.isBlank() || etPassword.text.isBlank() || etPasswordAgain.text.isBlank()) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Заполните все поля!",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            } else {
                return false
            }
        }
    }

    private fun checkPasswords(): Boolean {
        with(binding) {
            if (etPassword.text.toString() == etPasswordAgain.text.toString()) {
                return true
            } else {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Пароли должны совпадать",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        }
    }

    private fun launchSignInFragment() {
        findNavController().navigate(R.id.action_registerFragment_to_signInFragment)
    }

}