package com.espol.lenguajesprogramacionfinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class PerrosPerdidosAdapter(private val context: Context,
                            private val dataSource: ArrayList<PerrosPerdidosModelListView>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rowView = inflater.inflate(R.layout.list_item_perros_perdidos, parent, false)

        val razaTextView = rowView.findViewById(R.id.razaPerroPerdidoListView) as TextView

        val sexoTextView = rowView.findViewById(R.id.sexoPerroPerdidoListView) as TextView

        val edadTextView = rowView.findViewById(R.id.edadPerroPerdidoListView) as TextView

        val descrTextView = rowView.findViewById(R.id.descripcionPerroPerdidoListView) as TextView

        val perroPerdidoImageView = rowView.findViewById(R.id.imagenPerroPerdidoListView) as ImageView

        // 1
        val perro = getItem(position) as PerrosPerdidosModelListView

// 2
        razaTextView.text = perro.raza
        sexoTextView.text = perro.sexo
        edadTextView.text = perro.edad
        descrTextView.text = perro.descr

// 3
        Picasso.get().load("../../../../res/fotosPerros/${perro.id}").placeholder(R.mipmap.ic_launcher).into(perroPerdidoImageView)


        return rowView
    }

}


