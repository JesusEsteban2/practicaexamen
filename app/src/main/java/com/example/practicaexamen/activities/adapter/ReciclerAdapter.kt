package com.example.preacticaexamen.activities.adapter


import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.practicaexamen.R
import com.example.practicaexamen.activities.Datos.Movimiento
import com.example.practicaexamen.activities.activities.MainActivity.Companion.dataSet
import com.example.practicaexamen.databinding.MoveListBinding


class ReciclerAdapter (val onClickListener: (position:Int) -> Unit ) :
    RecyclerView.Adapter<ReViewHolder>() {
    lateinit var bindingMoveList:MoveListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReViewHolder {
        // Prepara el binding de recipe_list
        bindingMoveList = MoveListBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        //Devuelve el viewholder construido
        return ReViewHolder(bindingMoveList)

    }

    /**
     * Sobre escritura de onBindViewHolder
     * Pasa el evento OnClick al Holder y renderiza la linea.
     * @param holder Holder del ReciclerView
     * @param position Linea a renderizar
     */
    override fun onBindViewHolder(holder: ReViewHolder, position: Int) {

        holder.render(dataSet[position])
        holder.itemView.setOnClickListener { onClickListener(position) }

    }

    /**
     * Sobre escritura de getItemCount
     * Devuelve la longitud de la lista
     */
    override fun getItemCount(): Int = dataSet.size

    /**
     * Actualiza los valores del dataset utilizado para el ReciclerView
     * Notifica el cambio de los datos para que se actualice la visualización
     */
    fun updateItems(results: List<Movimiento>) {
        dataSet = results
        notifyDataSetChanged()
    }

}


class ReViewHolder(val binding: MoveListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun render(mov: Movimiento) {

        //Visualiza el contenido de la receta.
        if (mov.cantidad>0) {
            binding.simbolIcon.setImageDrawable(binding.root.context.getDrawable(R.drawable.add_24))
        }else {
            binding.simbolIcon.setImageDrawable(binding.root.context.getDrawable(R.drawable.less_24))
        }

        binding.identi.setText(mov.id.toString())
        binding.cantidad.setText(mov.cantidad.toString())
        binding.fecha.setText(mov.fecha.toString())
    }


}