package myapp.csit.nonball.myfeeddata2.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import myapp.csit.nonball.myfeeddata2.FeedStockFragment;
import myapp.csit.nonball.myfeeddata2.FeedProductFragment;
import myapp.csit.nonball.myfeeddata2.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private static final int[] TAB_ICON = new int[]{R.drawable.ic_product, R.drawable.ic_stock};

    private final Context mContext;
    private int PAGES = TAB_TITLES.length;  //จำนวนแท็บ

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FeedProductFragment();
            default:
                return  new FeedStockFragment();
        }
    }

    // ปืดส่วนนี้ VVVV
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mContext.getResources().getString(TAB_TITLES[position]);
//    }
    public View getTabView(int position) {
        View _view = LayoutInflater.from(mContext).inflate(R.layout.custom_tab,null);

        //bind title
        TextView titleTV = _view.findViewById(R.id.title);
        titleTV.setText(TAB_TITLES[position]);

        //bind icon
        ImageView iconTV = _view.findViewById(R.id.icon);
        iconTV.setImageResource(TAB_ICON[position]);

        return _view;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return PAGES;
    }
}