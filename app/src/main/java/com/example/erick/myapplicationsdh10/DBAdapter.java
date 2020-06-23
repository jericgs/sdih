package com.example.erick.myapplicationsdh10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Erick on 17/07/2015.
 */

public class DBAdapter {

    //Tabela Persistencia
    private static final String NOME_TABELA_PERSISTENCIA = "persistencia";
    public static final String VAR_ID = "id";
    public static final String VAR_CHAVE = "chave";

    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NOME = "My1"; //10
    private static final int DATA_VERSION = 1;

    private static final String DATABASE_CREATE_TABLE_PERSISTENCIA = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA_PERSISTENCIA + " ( "
            + VAR_ID + " TEXT PRIMARY KEY NOT NULL, "
            + VAR_CHAVE + " TEXT NOT NULL);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{

        DatabaseHelper(Context context){
            super(context, DATABASE_NOME, null, DATA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(DATABASE_CREATE_TABLE_PERSISTENCIA);
            }catch (SQLException e){
                e.printStackTrace();
                Log.i("Informação", e.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(TAG, "atualizamdo a base de dados sa versão " + oldVersion + "para a " + newVersion + ", o que destruirá todos os dados antigos");

            db.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA_PERSISTENCIA);
            onCreate(db);

        }
    }

    public DBAdapter abrir()throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void fechar(){
        DBHelper.close();
    }

    public long insertTabelaPersistencia(String id, String chave){ //numero do telefone
        ContentValues iniciarValues = new ContentValues();
        iniciarValues.put(VAR_ID, id);
        iniciarValues.put(VAR_CHAVE, chave);
        return db.insert(NOME_TABELA_PERSISTENCIA, null, iniciarValues);
    }

    public Cursor getAllPersistencia(){
        return db.query(NOME_TABELA_PERSISTENCIA, new String[]{VAR_ID, VAR_CHAVE}, null, null, null, null, null);
    }


    public boolean deletePersistencia(String id){

        return db.delete(NOME_TABELA_PERSISTENCIA, VAR_ID + "=" + id, null) > 0;
    }

}
