package org.columbuschurch.columbuschurch;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setTitle("About");
            getSupportActionBar().setTitle("About");
            getActionBar().setDisplayShowTitleEnabled(true);

        }catch(NullPointerException e){
            e.printStackTrace();
        }
        TextView aboutIcon = (TextView) findViewById(R.id.about_icon_textView);
        aboutIcon.setText(Html.fromHtml("Icons made by <a href=\"http://www.freepik.com\" title=\"Freepik\">Freepik</a> from <a href=\"http://www.flaticon.com\" title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a>"));
        aboutIcon.setMovementMethod(LinkMovementMethod.getInstance());
        ImageView githubLogo = (ImageView) findViewById(R.id.github_logo);
        githubLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGitHub();
            }
        });
        TextView githubText = (TextView) findViewById(R.id.github_text);
        githubLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGitHub();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void openGitHub(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/cyriacd/ColumbusChurch"));
        startActivity(browserIntent);
    }
}
