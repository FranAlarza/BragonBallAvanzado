package com.franalarza.tryavanzado.ui.commons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.franalarza.tryavanzado.databinding.ItemHeroListBinding
import com.franalarza.tryavanzado.domain.HeroPresent

class HeroListAdapter(private val onItemClickListener: (HeroPresent) -> Unit) : ListAdapter<HeroPresent, HeroListAdapter.HeroViewHolder>(HeroDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            ItemHeroListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class HeroViewHolder(private val binding: ItemHeroListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var hero: HeroPresent
        init {
            binding.root.setOnClickListener {
                onItemClickListener(hero)
            }
        }

        fun bind(heroPresent: HeroPresent) {
            with(binding) {
                hero = heroPresent
                heroImage.load(heroPresent.photo)
                heroName.text = heroPresent.name
            }

        }
    }

    class HeroDiffCallBack : DiffUtil.ItemCallback<HeroPresent>() {
        override fun areItemsTheSame(oldItem: HeroPresent, newItem: HeroPresent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HeroPresent, newItem: HeroPresent): Boolean {
            return oldItem == newItem
        }

    }
}