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
        String test = "<![CDATA[\n" +
                "<h3 class=\"s1\">The Preaching of John the Baptist</h3> <h5 class=\"r\">(Mark 1.1-8; Luke 3.1-9,15-17; John 1.19-28)</h5> <p class=\"p\"><sup id=\"Matt.3.1\" class=\"v\">1</sup>In those days came John the Baptist, preaching in the wilderness of Judea,<sup id=\"Matt.3.2\" class=\"v\">2</sup> and saying, Repent ye: for the kingdom of heaven is at hand.<sup id=\"Matt.3.3\" class=\"v\">3</sup> For this is he that was spoken of by the prophet Isaiah, saying,</p> <p class=\"q\">The voice of one crying in the wilderness,</p> <p class=\"q\">Prepare ye the way of the Lord,</p> <p class=\"q\">make his paths straight.</p> <p class=\"m\"><sup id=\"Matt.3.4\" class=\"v\">4</sup> And the same John had his raiment of camel's hair, and a leathern girdle about his loins; and his meat was locusts and wild honey.<sup id=\"Matt.3.5\" class=\"v\">5</sup>Then went out to him Jerusalem, and all Judea, and all the region round about Jordan,</p>\n" +
                "]]>";
        aboutIcon.setText(Html.fromHtml("Icons made by <a href=\"http://www.freepik.com\" title=\"Freepik\">Freepik</a> from <a href=\"http://www.flaticon.com\" title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a>"));
//        aboutIcon.setText(Html.fromHtml(test));
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
