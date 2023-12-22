package com.register.coffeapp.presentation.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.register.coffeapp.R
import com.register.coffeapp.databinding.FragmentCafeListBinding
import com.register.coffeapp.databinding.FragmentRegisterBinding
import com.register.coffeapp.presentation.CoffeeApp
import com.register.coffeapp.presentation.MainViewModel
import com.register.coffeapp.presentation.ViewModelFactory
import com.register.coffeapp.presentation.adapters.CafeAdapter
import javax.inject.Inject

class CafeListFragment : Fragment() {

    private var _binding: FragmentCafeListBinding? = null
    private val binding: FragmentCafeListBinding
        get() = _binding ?: throw RuntimeException("FragmentCafeListBinding == null")

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

   private var token = ""

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
        _binding = FragmentCafeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
        viewModel.token.observe(viewLifecycleOwner) {
            token = "Bearer $it"
            viewModel.getCafeInfo(token)
        }

        viewModel.cafeList.observe(viewLifecycleOwner) {
            val adapter = CafeAdapter()
            adapter.setOnItemClickListener(object: CafeAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    viewModel.getMenu(token, it[position].id.toString())
                    launchMenuFragment()
                }
            })
            binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            binding.recyclerView.adapter = adapter
            adapter.submitList(it)
        }

    }

    private fun launchMenuFragment() {
        findNavController().navigate(R.id.action_coffeeListFragment_to_menuFragment)
    }
}