package com.register.coffeapp.presentation.screen

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.register.coffeapp.R
import com.register.coffeapp.databinding.FragmentMenuBinding
import com.register.coffeapp.databinding.FragmentOrderBinding
import com.register.coffeapp.domain.entities.OrderItem
import com.register.coffeapp.presentation.CoffeeApp
import com.register.coffeapp.presentation.MainViewModel
import com.register.coffeapp.presentation.ViewModelFactory
import com.register.coffeapp.presentation.adapters.OrderAdapter
import javax.inject.Inject

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding: FragmentOrderBinding
        get() = _binding ?: throw RuntimeException("FragmentOrderBinding == null")

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
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]

        val args = OrderFragmentArgs.fromBundle(requireArguments())
        val orderItems = args.selectedItems.toList()

        Log.d("OrderTag", "$orderItems")

        val adapter = OrderAdapter()
        binding.rvOrder.adapter = adapter
        adapter.submitList(orderItems)

    }

}