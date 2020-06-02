package com.aman802.potterwatch.characteroverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.aman802.potterwatch.R
import com.aman802.potterwatch.databinding.FragmentCharacterOverviewBinding

class CharacterOverview : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentCharacterOverviewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_overview, container, false)
        val context = requireContext()
        val arguments = CharacterOverviewArgs.fromBundle(requireArguments())
        val characterOverviewViewModelFactory = CharacterOverviewViewModelFactory(context, arguments.characterId)
        val characterOverviewViewModel = ViewModelProvider(this, characterOverviewViewModelFactory).get(CharacterOverviewViewModel::class.java)
        binding.characterOverviewViewModel = characterOverviewViewModel

        val adapter = CharacterOverviewAdapter()
        binding.characterOverview.adapter = adapter

        binding.characterName.text = arguments.characterName

        characterOverviewViewModel.progressBarVisible.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
                binding.characterOverview.visibility = View.GONE
            }
            else {
                binding.progressBar.visibility = View.GONE
                binding.characterOverview.visibility = View.VISIBLE
            }
        })

        characterOverviewViewModel.keyValuePairModelList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root

    }

}
