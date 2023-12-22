package com.register.coffeapp.presentation.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.register.coffeapp.R
import com.register.coffeapp.databinding.FragmentRegisterBinding
import com.register.coffeapp.databinding.FragmentSignInBinding
import com.register.coffeapp.domain.entities.AuthRequest
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

        binding.buttonNext.setOnClickListener {

            viewModel.signIn(
                AuthRequest(
                    binding.etLogin.text.toString(),
                    binding.etPasswordSign.text.toString()
                )
            )
            launchCafeListFragment()
        }
    }

    private fun launchCafeListFragment() {
        findNavController().navigate(R.id.action_signInFragment_to_coffeeListFragment)
    }

}