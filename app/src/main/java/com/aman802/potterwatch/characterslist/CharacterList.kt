package com.aman802.potterwatch.characterslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.aman802.potterwatch.R
import com.aman802.potterwatch.databinding.FragmentCharacterListBinding

class CharacterList : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentCharacterListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_list, container, false)
        val context = requireContext()
        val characterListViewModelFactory = CharacterListViewModelFactory(context)
        val characterListViewModel = ViewModelProvider(this, characterListViewModelFactory).get(CharacterListViewModel::class.java)
        binding.characterListViewModel = characterListViewModel

        val adapter = CharacterListAdapter(ItemClickListener { characterModel ->
            characterListViewModel.onCharacterClicked(characterModel)
        })
        binding.characterList.adapter = adapter

        characterListViewModel.progressBarVisible.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
                binding.characterList.visibility = View.GONE
            }
            else {
                binding.progressBar.visibility = View.GONE
                binding.characterList.visibility = View.VISIBLE
            }
        })

        characterListViewModel.characterList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        characterListViewModel.navigateToCharacterOverview.observe(viewLifecycleOwner, Observer { characterModel ->
            characterModel?.let {
                this.findNavController().navigate(CharacterListDirections.actionCharacterListToCharacterOverview(characterModel.getId(), characterModel.getName()))
                characterListViewModel.onCharacterOverviewNavigated()
            }
        })

        binding.lifecycleOwner = this

        return binding.root

    }

}
