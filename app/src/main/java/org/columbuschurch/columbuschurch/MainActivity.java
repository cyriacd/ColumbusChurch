package org.columbuschurch.columbuschurch;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
//import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.spec.ECField;
import java.util.Calendar;

import static org.columbuschurch.columbuschurch.R.id.action_about;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager mViewPager;
        SectionsPagerAdapter mSectionsPagerAdapter;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAboutPage(view);
            }
        });

    }

    private void startAboutPage(View v){
        Intent aboutPage = new Intent(v.getContext(),AboutPage.class);
        startActivity(aboutPage);
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

        switch (id) {
            case action_about:
//                Snackbar.make(this.findViewById(android.R.id.content), "Code on Github", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startAboutPage(findViewById(android.R.id.content).getRootView());
                return true;
            case R.id.action_settings:
//                Snackbar.make(this.findViewById(android.R.id.content), "Settings...", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent startSettingsPage = new Intent(this,Settings.class);
                startActivity(startSettingsPage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public int currentPage =0 ;

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView;
            ViewPager viewPager = new ViewPager(this.getContext());
            Log.d("THIS PAGE: ", Integer.toString(viewPager.getCurrentItem()));
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 0:
                    Log.d("Section Number:", Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
                    rootView = inflater.inflate(R.layout.fragment_home, container, false);
                    break;
                case 1:
                    Log.d("Section Number:", Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
                    rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
                    //LinearLayout piclayout = (LinearLayout) rootView.findViewById(R.id.gallery_banners_scroll_layout);
                    ImageView gallery0 = (ImageView) rootView.findViewById(R.id.gallery0);
                    ImageView gallery1 = (ImageView) rootView.findViewById(R.id.gallery1);
                    ImageView gallery2 = (ImageView) rootView.findViewById(R.id.gallery2);
                    gallery0.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                 Intent intent= new Intent(rootView.getContext(), Gallery.class);
                                 intent.putExtra("GALLERY_ID", 0);
                                 startActivity(intent);
                             }
                         }
                    );
                    gallery1.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        Intent intent= new Intent(rootView.getContext(), Gallery.class);
                                                        intent.putExtra("GALLERY_ID", 1);
                                                        startActivity(intent);
                                                    }
                                                }
                    );
                    gallery2.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        Intent intent= new Intent(rootView.getContext(), Gallery.class);
                                                        intent.putExtra("GALLERY_ID", 2);
                                                        startActivity(intent);
                                                    }
                                                }
                    );
                    break;
                case 2:
                    Log.d("Section Number:", Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
                    rootView = inflater.inflate(R.layout.fragment_contact, container, false);
                    TextView addressText = (TextView)rootView.findViewById(R.id.address);
                    addressText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:39.98092039,-83.00011173?q=Sacred+Heart+Catholic+Church+893+Hamlet+St+Columbus+OH"));
                            startActivity(intent);
                        }
                    });
                    break;
                case 3:
                    Log.d("Section Number:", Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
                    rootView = inflater.inflate(R.layout.fragment_news, container, false);
                    Button starts = (Button) rootView.findViewById(R.id.test);
                    starts.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent1= new Intent(getContext(),CurrentReadingNotification.class);
                            getContext().startService(intent1);
                            Intent intent = new Intent(getContext(),Readings.class);
                            intent.putExtra("DAY",0);
                            startActivity(intent);
                        }
                    });
                    break;
                default:
                    rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    break;
            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HOME";
                case 1:
                    return "GALLERY";
                case 2:
                    return "CONTACT";
                case 3:
                    return "NEWS";
            }
            return null;
        }
    }
}
