package in.vinodkumarl.rulify.rulify;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by vinod on 12/2/16.
 */
public class SendSMS {
    private String phoneNumber;
    private String textMessage;

    SendSMS () {
        this.phoneNumber = "";
        this.textMessage = "";
    }
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }
    public void  setTextMessage(String value) {
        this.textMessage = value;
    }
    public void sendTextMessage (Context context) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, textMessage, null, null);
            Toast.makeText(context, "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(context, "SMS failed, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
