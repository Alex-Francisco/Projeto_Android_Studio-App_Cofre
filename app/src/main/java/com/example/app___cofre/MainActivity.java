package com.example.app___cofre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtServicoProg;
    EditText edtNomeProg;
    EditText edtUsuarioProg;
    EditText edtSenhaProg;
    int quantidadeRegistros;
    int registroAtual;
    int idCredencialAtual;

    credencialModel credencial = new credencialModel();

    bdModel bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtServicoProg = (TextView) findViewById(R.id.txtservico);
        edtNomeProg = (EditText) findViewById(R.id.edtservico);
        edtUsuarioProg = (EditText) findViewById(R.id.edtusuario);
        edtSenhaProg = (EditText) findViewById(R.id.edtsenha);

        carregarRegistroZero();

    }

    public void clickBtnDeletar(View v) {
        credencial.setID(idCredencialAtual);
        credencial.setNome(edtNomeProg.getText().toString());
        credencial.setUsuario(edtUsuarioProg.getText().toString());
        credencial.setSenha(edtSenhaProg.getText().toString());
        bd = new bdModel(getApplicationContext());
        bd.delete(bd.getTabela(), credencial);
        limpar();
        carregarRegistroZero();
    }

    public void clickBtnAlterar(View v) {
        credencial.setID(idCredencialAtual);
        credencial.setNome(edtNomeProg.getText().toString());
        credencial.setUsuario((edtUsuarioProg.getText().toString()));
        credencial.setSenha(edtSenhaProg.getText().toString());
        bd = new bdModel(getApplicationContext());
        bd.update(bd.getTabela(), credencial);
        carregarRegistroZero();
    }

    public void clickBtnCadastrar(View v) {
        credencial.setNome(edtNomeProg.getText().toString());
        credencial.setUsuario(edtUsuarioProg.getText().toString());
        credencial.setSenha(edtSenhaProg.getText().toString());
        bd = new bdModel(getApplicationContext());
        bd.insert(bdModel.getTabela(), credencial);
        carregarRegistroZero();
    }

    public void clickBtnNovo(View v) {
        limpar();
    }

    public void clickBtnAnterior(View v) {
        if (quantidadeRegistros != 0) {
            if (registroAtual > 0) {
                registroAtual = registroAtual - 1;
                carregarDados(registroAtual);
            }
        }
    }

    public void clickBtnProximo(View v) {
        if (quantidadeRegistros != 0) {
            if (registroAtual < quantidadeRegistros - 1) {
                registroAtual = registroAtual + 1;
                carregarDados(registroAtual);
            }
        }
    }

    public void limpar() {
        edtNomeProg.setText("");
        edtUsuarioProg.setText("");
        edtSenhaProg.setText("");
        txtServicoProg.setText("Serviço:");
        edtNomeProg.requestFocus();
    }

    public void carregarDados(int i) {
        bd = new bdModel(getApplicationContext());
        ArrayList<credencialModel> arrayCredencialModel;
        arrayCredencialModel = bd.select();
        quantidadeRegistros = arrayCredencialModel.size();
        if (quantidadeRegistros != 0) {
            credencialModel credencialModel = arrayCredencialModel.get(i);
            txtServicoProg.setText("Serviço " + String.valueOf(credencialModel.getID()) + ":");
            idCredencialAtual = credencialModel.getID();
            edtNomeProg.setText(credencialModel.getNome());
            edtUsuarioProg.setText(credencialModel.getUsuario());
            edtSenhaProg.setText(credencialModel.getSenha());
        }
    }

    public void carregarRegistroZero() {
        registroAtual = 0;
        carregarDados(registroAtual);
    }
}

