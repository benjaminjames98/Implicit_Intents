package tech.bencloud.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText webTxt;
    private EditText locTxt;
    private EditText shaTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webTxt = findViewById(R.id.web_text);
        locTxt = findViewById(R.id.loc_text);
        shaTxt = findViewById(R.id.sha_text);
    }

    public void openWebsite(View view) {
        String url = webTxt.getText().toString();

        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Log.d("ImplicitIntents", "Can't handle this intent!");
    }

    public void locPressed(View view) {
        String loc = locTxt.getText().toString();

        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity((getPackageManager())) != null)
            startActivity(intent);
        else
            Log.d("ImplicitIntents", "Can't handle this intent!");
    }

    public void shaPressed(View view) {
        String txt = locTxt.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_this_text)
                .setText(txt)
                .startChooser();
    }
}
