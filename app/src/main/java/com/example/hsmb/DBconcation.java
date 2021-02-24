package com.example.hsmb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBconcation extends SQLiteOpenHelper {
    public static final String ACCOUNT_FOR_PILGIRM = "accountForPilgirm";
    public static final String IDACCOUNT = "IDAccount";
    public static final String FIRST_NAME = "FirstName";
    public static final String LAST_NAME = "LastName";
    public static final String AGE = "Age";
    public static final String NATIONALITY = "Nationality";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String MEDICAL_BACKGROUND = "Medical_background";
    public static final String DIAGNOSIS = "diagnosis";
    public static final String IDLOCATION = "Idlocation";
    private SQLiteDatabase myDataBase;
    final static String DBname="HSMB.db";
    final static int Version=1;
    public final static String DATABASE_PATH = "/data/data/com.example.hsmb/databases/";
    private final Context myContext;
    public DBconcation(Context context){
        super(context, DBname, null ,Version);    this.myContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStetment= "create Table " + ACCOUNT_FOR_PILGIRM + " (" + IDACCOUNT + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIRST_NAME + " TEXT, " + LAST_NAME + " TEXT, " + AGE + " INTEGER, " + NATIONALITY + " INTEGER, " + USERNAME + " TEXT, " + PASSWORD + " TEXT, " + MEDICAL_BACKGROUND + " TEXT,\n" +
                "\t" + DIAGNOSIS + " TEXT, " + IDLOCATION + " INTEGER)";
        db.execSQL(createTableStetment);



    }
    private boolean checkDataBase()
    {
        boolean checkDB = false;
        try
        {
            String myPath = DATABASE_PATH + DBname;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        }
        catch(SQLiteException e)
        {
        }
        return checkDB;
    }
    private void copyDataBase() throws IOException
    {

        InputStream mInput = myContext.getAssets().open(DBname);
        String outFileName = DATABASE_PATH + DBname;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[2024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }
    public void db_delete()
    {
        File file = new File(DATABASE_PATH + DBname);
        if(file.exists())
        {
            file.delete();
            System.out.println("delete database file.");
        }
    }
    //Open database
    public void openDatabase() throws SQLException
    {
        String myPath = DATABASE_PATH + DBname;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDataBase()throws SQLException
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
        {
            Log.v("Database Upgrade", "Database version higher than old.");
            db_delete();
        }

    }
    public List<AccountPligrim> getAllRecoredLogin(String username){
        List<AccountPligrim> array= new ArrayList();
        String qure="SELECT * FROM "+ACCOUNT_FOR_PILGIRM+" WHERE username="+"+username";
        // this getReadableDatabse make lock then one process want read it is can't.
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res = db.rawQuery(qure,null);
        if(res.moveToFirst()){
            do{
                int id = res.getInt(0);
                String first=res.getString(1);
                String last=res.getString(2);
                int age=res.getInt(3);
                String na=res.getString(4);
                String Userame=res.getString(5);
                String password=res.getString(6);
                String BackMedical=res.getString(7);
                String diognos=res.getString(8);
                int idL=res.getInt(9);
                AccountPligrim account=new AccountPligrim(id,first,last,age,na,Userame,password,BackMedical,diognos,idL);
                array.add(account);

            }while (res.moveToNext());
        }
        else{
            //if failure then show massge
            Toast.makeText(myContext,"you aren't register in applaction", Toast.LENGTH_LONG);

        }
        //close both Cursor and db when done
        res.close();
        db.close();

        return array;

    }
    public void delete(){
        SQLiteDatabase db= this.getReadableDatabase();
        db.delete(ACCOUNT_FOR_PILGIRM,null,null);
    }
    public boolean addOne(AccountPligrim account){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(FIRST_NAME,account.getFirstName());
        cv.put(LAST_NAME,account.getLastNmae());
        cv.put(AGE,account.getAge());
        cv.put(NATIONALITY,account.getNationality());
        cv.put(USERNAME,account.getUsername());
        cv.put(PASSWORD,account.getPassword());
        cv.put(MEDICAL_BACKGROUND,account.getMedical_background());
        cv.put(DIAGNOSIS,account.getDiagnosis());
        cv.put(IDLOCATION,account.getIdLocation());
       long insert= db.insert(ACCOUNT_FOR_PILGIRM,null,cv);
       db.close();
       if(insert==-1){
           return false;
       }
       else {
           return true;
       }
    }

}
