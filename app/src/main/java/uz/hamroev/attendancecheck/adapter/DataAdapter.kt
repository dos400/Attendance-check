package uz.hamroev.attendancecheck.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.attendancecheck.databinding.ItemDataBinding
import uz.hamroev.attendancecheck.room.entity.Data

class DataAdapter(
    var context: Context,
    var list: List<Data>,
    var onNavClickListener: DataAdapter.OnDataClickListener,
) : RecyclerView.Adapter<DataAdapter.VhPredlog>() {


    inner class VhPredlog(var itemDataBinding: ItemDataBinding) :
        RecyclerView.ViewHolder(itemDataBinding.root) {


        fun onBind(data: Data, position: Int) {
            itemDataBinding.tvData.text = data.date

            itemDataBinding.main.setOnClickListener {
                onNavClickListener.onCLick(data, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhPredlog {
        return VhPredlog(ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VhPredlog, position: Int) {
        return holder.onBind(list[position], position)
    }

    interface OnDataClickListener {
        fun onCLick(predlogEntity: Data, position: Int)

    }


}