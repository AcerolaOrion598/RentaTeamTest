package com.example.rentateamtest.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rentateamtest.R
import com.example.rentateamtest.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textAbout: TextView = binding.textAbout
        textAbout.text = getString(R.string.text_about)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}