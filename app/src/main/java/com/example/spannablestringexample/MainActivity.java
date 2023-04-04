package com.example.spannablestringexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int MAX_LINES = 3;
    public static final String TWO_SPACES = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String myReallyLongText = "Bacon ipsum dolor amet porchetta venison ham fatback alcatra tri-tip, turducken strip steak sausage rump burgdoggen pork loin. Spare ribs filet mignon salami, strip steak ball tip shank frankfurter corned beef venison. Pig pork belly pork chop andouille. Porchetta pork belly ground round, filet mignon bresaola chuck swine shoulder leberkas jerky boudin. Landjaeger pork chop corned beef, tri-tip brisket rump pastrami flank.";
        TextView tvLines = findViewById(R.id.tv_text);
        tvLines.setText(myReallyLongText);
        tvLines.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                if (tvLines.getLineCount() > MAX_LINES) {
                    int lastCharShown = tvLines.getLayout().getLineVisibleEnd(MAX_LINES - 1);
                    tvLines.setMaxLines(MAX_LINES);

                    String moreString = getApplicationContext().getString(R.string.more);
                    String suffix = TWO_SPACES + moreString;
//                    // 3 is a "magic number" but it's just basically the length of the ellipsis we're going to insert
                    String actionDisplayText = myReallyLongText.substring(0, lastCharShown - suffix.length() - 3) + "..." + suffix;
                    SpannableString truncatedSpannableString = new SpannableString(actionDisplayText);
                    int startIndex = actionDisplayText.indexOf(moreString);
//                    truncatedSpannableString.setSpan(new ForegroundColorSpan(getApplicationContext().getColor(android.R.color.black)), startIndex, startIndex + moreString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    truncatedSpannableString.setSpan(new UnderlineSpan(), startIndex, startIndex + moreString.length(), 0);
                    tvLines.setText(truncatedSpannableString);
                }
            }
        });

    }
}