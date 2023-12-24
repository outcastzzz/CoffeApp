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
import com.register.coffeapp.databinding.FragmentSignInBinding
import com.register.coffeapp.domain.entities.User
import com.register.coffeapp.presentation.CoffeeApp
import com.register.coffeapp.presentation.MainViewModel
import com.register.coffeapp.presentation.ViewModelFactory
import javax.inject.Inject

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding == null")

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
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]

        viewModel.error.observe(viewLifecycleOwner) {
            if(it) {
                launchCafeListFragment()
            } else {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Неправильный логин или пароль",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.buttonNext.setOnClickListener {
            val user = User(
                binding.etLogin.text.toString(),
                binding.etPasswordSign.text.toString()
            )

            viewModel.signIn(user)
        }

        binding.registerButton.setOnClickListener {
            launchRegisterFragment()
        }
    }

    private fun launchCafeListFragment() {
        findNavController().navigate(R.id.action_signInFragment_to_coffeeListFragment)
    }

    private fun launchRegisterFragment() {
        findNavController().navigate(R.id.action_signInFragment_to_registerFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}