package br.com.rianperassoli.webmob.offtrail.ui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import br.com.rianperassoli.webmob.offtrail.R;
import br.com.rianperassoli.webmob.offtrail.model.Trilheiro;

@EViewGroup(R.layout.lista_trilheiros)
public class TrilheiroItemView extends LinearLayout {

    @ViewById
    TextView txtNome;

    @ViewById
    TextView txtMoto;

    @ViewById
    ImageView imvFoto;


    public TrilheiroItemView(Context context) {
        super(context);
    }

    public void bind(Trilheiro t) {
        txtNome.setText(t.getNome());
        txtMoto.setText(t.getMoto().getModelo() + " - " + t.getMoto().getCin());
        imvFoto.setImageBitmap(BitmapFactory.decodeByteArray(t.getFoto(), 0, t.getFoto().length));
    }
}