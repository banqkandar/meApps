package com.iskandar.meapps.Presenter;
/*
 * tgl pengerjaan : 5 agustus 2019
 * nim : 10116121
 * nama : mohammad iskandar
 * kelas : IF-3
 * */
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iskandar.meapps.Models.MahasiswaModel;
import com.iskandar.meapps.R;
import com.iskandar.meapps.Views.DetailActivity;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {
    private List<MahasiswaModel> mahasiswaModels;
    Context context;

    public MahasiswaAdapter(Context context, List<MahasiswaModel> mahasiswaModels){
        this.context = context;
        this.mahasiswaModels = mahasiswaModels;
    }

    @Override
    public MahasiswaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MahasiswaAdapter.MyViewHolder holder, int position) {
        final MahasiswaModel model = mahasiswaModels.get(position);
        holder.nim.setText(model.getNim().toString());
        holder.nama.setText(model.getNama());
        holder.kelas.setText(model.getKelas());
        holder.telepon.setText(model.getTelepon().toString());
        holder.email.setText(model.getEmail());
        holder.sosmed.setText(model.getSosmed());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("id", model.getId().toString());
                intent.putExtra("nim", model.getNim().toString());
                intent.putExtra("nama", model.getNama());
                intent.putExtra("kelas", model.getKelas());
                intent.putExtra("telepon", model.getTelepon().toString());
                intent.putExtra("email", model.getEmail());
                intent.putExtra("sosmed", model.getSosmed());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mahasiswaModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nim, nama, kelas, telepon, email, sosmed;

        public MyViewHolder(View itemView){
            super(itemView);
            nim = itemView.findViewById(R.id.tvNim);
            nama = itemView.findViewById(R.id.tvNama);
            kelas = itemView.findViewById(R.id.tvKelas);
            telepon = itemView.findViewById(R.id.tvTelepon);
            email = itemView.findViewById(R.id.tvEmail);
            sosmed = itemView.findViewById(R.id.tvSosmed);
        }
    }
}
