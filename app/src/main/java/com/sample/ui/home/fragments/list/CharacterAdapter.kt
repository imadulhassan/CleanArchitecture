package com.sample.ui.home.fragments.list


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.sample.databinding.ItemCharacterBinding
import com.sample.extn.extractName
import com.sample.models.RelatedTopic

@SuppressLint("NotifyDataSetChanged")
internal class CharacterAdapter(private val itemClickListener: (RelatedTopic) -> Unit) :
    Adapter<CharacterAdapter.ItemHolder>(),
    Filterable {

    private var characterList = mutableListOf<RelatedTopic>()
    private var characterListFiltered = mutableListOf<RelatedTopic>()

    inner class ItemHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(itemdata: RelatedTopic) {
            binding.idTVCourse.text = extractName(itemdata.text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindData(characterListFiltered[position])
        holder.itemView.setOnClickListener {
            itemClickListener(characterListFiltered[position])
        }
    }

    override fun getItemCount(): Int {
        return characterListFiltered.size
    }

    fun updateList(items: List<RelatedTopic>) {
        characterList = items.toMutableList()
        characterListFiltered = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val query = charSequence.toString()

                characterListFiltered = if (query.isEmpty()) {
                    characterList.toMutableList()
                } else {
                    characterList.filter {
                        it.text.contains(query, ignoreCase = true) || it.result.contains(query, ignoreCase = true)
                    }.toMutableList()
                }
                val filterResults = FilterResults()
                filterResults.values = characterListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                characterListFiltered = filterResults.values as MutableList<RelatedTopic>
                notifyDataSetChanged()
            }

        }
    }
}
