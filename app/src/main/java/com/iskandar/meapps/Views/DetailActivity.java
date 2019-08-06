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

import com.iskandar.meapps.Presenter.RealmHelper;
import com.iskandar.meapps.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etNim, etNama, etKelas, etTelepon, etEmail, etSosmed;
    String anim, anama, akelas, atelepon, aemail, asosmed;
    Integer id;
    Button btn_ubah, btn_hapus, btn_kembali;
    RealmHelper realmHelper;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_ubah);

        // Set up
        Realm.init(getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("mahasiswa.db").schemaVersion(0).build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        // Inisialisasi
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etKelas = findViewById(R.id.etKelas);
        etTelepon = findViewById(R.id.etTelepon);
        etEmail = findViewById(R.id.etEmail);
        etSosmed = findViewById(R.id.etSosmed);

        btn_ubah = findViewById(R.id.btnUpdate);
        btn_hapus = findViewById(R.id.btnHapus);
        btn_kembali = findViewById(R.id.btnCancel);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        anim = getIntent().getStringExtra("nim");
        anama = getIntent().getStringExtra("nama");
        akelas = getIntent().getStringExtra("kelas");
        atelepon = getIntent().getStringExtra("telepon");
        aemail = getIntent().getStringExtra("email");
        asosmed = getIntent().getStringExtra("sosmed");

        etNim.setText(anim);
        etNama.setText(anama);
        etKelas.setText(akelas);
        etTelepon.setText(atelepon);
        etEmail.setText(aemail);
        etSosmed.setText(asosmed);

        btn_kembali.setOnClickListener(this);
        btn_hapus.setOnClickListener(this);
        btn_ubah.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_ubah){
            realmHelper.update(id, Integer.parseInt(etNim.getText().toString()), etNama.getText().toString(), etKelas.getText().toString(), Integer.parseInt(etTelepon.getText().toString()), etEmail.getText().toString(), etSosmed.getText().toString());
            Toast.makeText(DetailActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
            etNim.setText("");
            etNama.setText("");
            etKelas.setText("");
            etTelepon.setText("");
            etEmail.setText("");
            etSosmed.setText("");
            finish();
        }else if (v == btn_hapus) {
            realmHelper.delete(id);
            Toast.makeText(DetailActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
            finish();
        }else if (v == btn_kembali) {
            startActivity(new Intent(DetailActivity.this, MahasiswaActivity.class));
            finish();
        }
    }
}
