package com.franalarza.tryavanzado.ui.commons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.franalarza.tryavanzado.databinding.ItemHeroListBinding
import com.franalarza.tryavanzado.domain.Hero

class HeroListAdapter : ListAdapter<Hero, HeroListAdapter.HeroViewHolder>(HeroDiffCallBack()) {

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


    class HeroViewHolder(private val binding: ItemHeroListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            with(binding) {
                heroName.text = hero.name
            }

        }
    }

    class HeroDiffCallBack : DiffUtil.ItemCallback<Hero>() {
        override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem == newItem
        }

    }
}