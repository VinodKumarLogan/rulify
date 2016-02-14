package in.vinodkumarl.rulify.rulify;

/**
 * Created by vinod on 6/2/16.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;


// Extend the class from BroadcastReceiver to listen when there is a incoming call
public class PhoneCallReceiver extends BroadcastReceiver
{
    // This String will hold the incoming phone number
    private String number;
    private boolean sent = false;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        //Log.w("Rulify", "Receiving a call");
        //Toast.makeText(context,
        //        "Receiving a call", Toast.LENGTH_LONG).show();
        // If, the received action is not a type of "Phone_State", ignore it
        if (!intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            //Toast.makeText(context,
            //        "Not able to get intent", Toast.LENGTH_LONG).show();
            //Log.w("Rulify", "Not able to get intent");
            return;
        }

            // Else, try to do some action
        else
        {
            // Fetch the number of incoming call
            number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            //Toast.makeText(context,
            //        "Incoming call from "+number, Toast.LENGTH_LONG).show();
            //Log.w("Rulify", "Incoming call from " + number);
            //Toast.makeText(context,
            //        number +" "+ MainActivity.blockedNumber,Toast.LENGTH_LONG).show();
            if (MainActivity.blockedNumber.equals(number)) {
                Toast.makeText(context,
                        "This call wil be blocked", Toast.LENGTH_LONG).show();
                disconnectPhoneItelephony(context);
                if (MainActivity.checkSMSSend) {
                    SendSMS sms = new SendSMS();
                    sms.setPhoneNumber(number);
                    sms.setTextMessage(MainActivity.smsMessage);
                    if (!sent) {
                        sms.sendTextMessage(context);
                        sent = true;
                    }
                }
            }
            // Check, whether this is a member of "Black listed" phone numbers stored in the database
            /*if(MainActivity.blockList.contains(new Blacklist(number)))
            {
                // If yes, invoke the method
                disconnectPhoneItelephony(context);
                return;
            }*/
        }
    }

    // Method to disconnect phone automatically and programmatically
    // Keep this method as it is
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void disconnectPhoneItelephony(Context context)
    {
        ITelephony telephonyService;
        TelephonyManager telephony = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        try
        {
            Class c = Class.forName(telephony.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            telephonyService = (ITelephony) m.invoke(telephony);
            telephonyService.endCall();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}