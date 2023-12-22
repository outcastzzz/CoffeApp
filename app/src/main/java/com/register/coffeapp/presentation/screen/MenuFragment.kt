package com.register.coffeapp.presentation.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.register.coffeapp.R
import com.register.coffeapp.databinding.FragmentMenuBinding
import com.register.coffeapp.databinding.FragmentRegisterBinding
import com.register.coffeapp.domain.entities.OrderItem
import com.register.coffeapp.presentation.CoffeeApp
import com.register.coffeapp.presentation.MainViewModel
import com.register.coffeapp.presentation.ViewModelFactory
import com.register.coffeapp.presentation.adapters.MenuAdapter
import javax.inject.Inject

class MenuFragment : Fragment(), MenuAdapter.OnMenuItemCountChangedListener {

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding == null")

    private lateinit var viewModel: MainViewModel
    private val selectedItems: MutableMap<String, OrderItem> = mutableMapOf()

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
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]

        viewModel.menuList.observe(viewLifecycleOwner) {
            val adapter = MenuAdapter(requireActivity().applicationContext, it, this)
            binding.gridView.adapter = adapter
        }

        binding.buttonOrder.setOnClickListener {
            launchOrderFragment()
        }

    }

    override fun onCountChanged(orderItem: OrderItem) {
        if (orderItem.count > 0) {
            selectedItems[orderItem.name] = orderItem
        } else {
            selectedItems.remove(orderItem.name)
        }
    }

    private fun launchOrderFragment() {
        val orderArray = selectedItems.values.toTypedArray()
        val action = MenuFragmentDirections.actionMenuFragmentToOrderFragment(orderArray)
        findNavController().navigate(action)
    }

}