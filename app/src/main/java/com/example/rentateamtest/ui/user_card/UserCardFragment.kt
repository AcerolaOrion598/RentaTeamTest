package com.example.rentateamtest.ui.user_card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.rentateamtest.databinding.FragmentUserCardBinding
import com.example.rentateamtest.model.User

class UserCardFragment : Fragment() {

    private var _binding: FragmentUserCardBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentUser: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = requireArguments()
        currentUser = User(
            args.getInt("id"),
            args.getString("email")!!,
            args.getString("firstName")!!,
            args.getString("lastName")!!,
            args.getString("avatarUrl")!!
        )
        _binding = FragmentUserCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(currentUser.avatarUrl).into(binding.userAvatar)
        val names = "${currentUser.firstName} ${currentUser.lastName}"
        binding.userNames.text = names
        binding.userEmail.text = currentUser.email
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}