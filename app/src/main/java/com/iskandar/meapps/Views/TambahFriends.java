package com.iskandar.meapps.Views;
/*
 * tgl pengerjaan : 5 agustus 2019
 * nim : 10116121
 * nama : mohammad iskandar
 * kelas : IF-3
 * */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iskandar.meapps.Models.MahasiswaModel;
import com.iskandar.meapps.Presenter.RealmHelper;
import com.iskandar.meapps.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TambahFriends extends AppCompatActivity implements View.OnClickListener {
    Button btnSimpan, btnTampil;
    EditText nim, nama, kelas, telepon, email, sosmed;
    String sNama, sKelas, sEmail, sSosmed;
    Integer sNim, sTelepon;
    Realm realm;
    RealmHelper realmHelper;
    MahasiswaModel mahasiswaModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_friends_tambah);

        //Inisialisasi
        btnSimpan = findViewById(R.id.btnSimpan);
        btnTampil = findViewById(R.id.btnTampil);
        nim = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);
        kelas = findViewById(R.id.kelas);
        telepon = findViewById(R.id.telepon);
        email = findViewById(R.id.email);
        sosmed = findViewById(R.id.sosmed);

        //Set up Realm
        Realm.init(getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("mahasiswa.db").schemaVersion(0).build();
        realm = Realm.getInstance(configuration);

        btnSimpan.setOnClickListener(this);
        btnTampil.setOnClickListener(this);

        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TambahFriends.this, MahasiswaActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnSimpan){
            sNim = Integer.parseInt(nim.getText().toString());
            sNama = nama.getText().toString();
            sKelas = kelas.getText().toString();
            sTelepon = Integer.parseInt(telepon.getText().toString());
            sEmail = email.getText().toString();
            sSosmed = sosmed.getText().toString();

            if (!sNim.equals("") && !sNama.isEmpty() && !sKelas.isEmpty() && !sTelepon.equals("") && !sEmail.isEmpty()&& !sSosmed.isEmpty()){
                mahasiswaModel = new MahasiswaModel();
                mahasiswaModel.setNim(sNim);
                mahasiswaModel.setNama(sNama);
                mahasiswaModel.setKelas(sKelas);
                mahasiswaModel.setTelepon(sTelepon);
                mahasiswaModel.setEmail(sEmail);
                mahasiswaModel.setSosmed(sSosmed);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(mahasiswaModel);

                Toast.makeText(TambahFriends.this, "Berhasil Disimpan!", Toast.LENGTH_SHORT).show();

                nim.setText("");
                nama.setText("");
                kelas.setText("");
                telepon.setText("");
                email.setText("");
                sosmed.setText("");
            }else {
                Toast.makeText(TambahFriends.this, "Terdapat inputan yang kosong", Toast.LENGTH_SHORT).show();
            }
        }else if (v == btnTampil){
            startActivity(new Intent(TambahFriends.this, MahasiswaActivity.class));
        }
    }
}
