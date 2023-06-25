package com.kingofraccoons.supermegacarsharing.ui.main.settings

import android.content.Context
import android.content.ContextWrapper
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dolatkia.animatedThemeManager.AppTheme
import com.kingofraccoons.supermegacarsharing.R
import com.kingofraccoons.supermegacarsharing.databinding.ItemThemeBinding
import com.kingofraccoons.supermegacarsharing.ui.themes.MyAppTheme

class ThemesAdapter(val appTheme: MutableLiveData<AppTheme>, var checkedPosition: Int = -1, val setTheme: (String, View) -> Unit) :
    ListAdapter<String, ThemesAdapter.ThemeViewHolder>(differ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        return ThemeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_theme, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ThemeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemThemeBinding.bind(view)

        private fun Context.getLifeCycleOwner() : AppCompatActivity? = when {
            this is ContextWrapper -> if (this is AppCompatActivity) this else this.baseContext.getLifeCycleOwner()
            else -> null
        }

        fun bind(nameTheme: String) {
            binding.root.context.getLifeCycleOwner()?.let {
                appTheme.observe(it) {
                    if (it is MyAppTheme) {
                        binding.textTheme.setTextColor(it.firstActivityTextColor(itemView.context))
                        binding.imageChecked.imageTintList = ColorStateList.valueOf(
                            it.firstActivityIconColor(itemView.context)
                        )
                    }
                }
            }

            binding.textTheme.text = nameTheme

            if (checkedPosition != -1) {
                binding.imageChecked.visibility =
                    if (checkedPosition == adapterPosition) View.VISIBLE else View.GONE
            }

            binding.root.setOnClickListener {
                if (checkedPosition != adapterPosition) {
                    notifyItemChanged(checkedPosition)
                    binding.imageChecked.visibility = View.VISIBLE
                    checkedPosition = adapterPosition
                }
                setTheme(nameTheme, binding.imageChecked)
            }
        }
    }


    companion object {
        val differ = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}