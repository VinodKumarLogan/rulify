package in.vinodkumarl.rulify.rulify;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static String blockedNumber = "";
    public static boolean checkSMSSend = false;
    public static String smsMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText number = (EditText) findViewById(R.id.phoneNumber);
        Button block = (Button) findViewById(R.id.blockButton);
        Button ublock = (Button) findViewById(R.id.unblockButton);
        final CheckBox smsSend = (CheckBox) findViewById(R.id.smsCheck);
        final EditText bmsg = (EditText) findViewById(R.id.blockMessage);

        block.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String phone = number.getText().toString();
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Block contact")
                                .setMessage("Are you sure you want to block this contact?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        blockedNumber = phone;
                                        checkSMSSend = smsSend.isChecked();
                                        smsMessage = bmsg.getText().toString();
                                        //block calls from this contact
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                }
        );

        ublock.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Long phone = Long.parseLong(number.getText().toString());
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Unblock contact")
                                .setMessage("Are you sure you want to unblock this contact?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        blockedNumber = "";
                                        //unblock calls from this contact
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
