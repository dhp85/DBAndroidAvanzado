package com.keepcoding.dbandroidavanzado.presentation.ui.heros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keepcoding.dbandroidavanzado.R
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModel

class HomeAdapter(private val onClickListener: (HeroModel) -> Unit): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var heroList = mutableListOf<HeroModel>()

    fun updateList(list: List<HeroModel>) {
        val originalSize = heroList.size
        heroList.addAll(list)
        notifyItemRangeInserted(originalSize, list.size)
    }

    inner class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textViewHero = itemView.findViewById<TextView>(R.id.hero_name)
        private val imageViewHero = itemView.findViewById<ImageView>(R.id.photo)

        private lateinit var hero: HeroModel

        init {
            itemView.setOnClickListener{
                onClickListener(hero)
            }
        }

        fun bind(hero: HeroModel) {
            this.hero = hero
            textViewHero.text = hero.name
            imageViewHero.load(hero.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        holder.bind(heroList.get(position))
    }

    override fun getItemCount(): Int {
        return heroList.size
    }
}