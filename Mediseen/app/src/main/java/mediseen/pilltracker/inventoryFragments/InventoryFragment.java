package mediseen.pilltracker.inventoryFragments;

/**
 * Created by elysi on 2/20/2016.
 */

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mediseen.CreateNoDisplay;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.CustomFontHelper;
import mediseen.customtextview.TextViewPlus;
import mediseen.pilltracker.ChangeVisibilityHelper;
import mediseen.work.pearlsantos.mediseen.R;

public class InventoryFragment extends Fragment {
    public static boolean noPills = true;
    private final String TYPEFACE = "fonts/SignikaNegative-Regular_0.ttf";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);
        LinearLayout pillInventoryLayout = (LinearLayout) rootView.findViewById(R.id.pillInventoryLayout);
        ButtonPlus addPillsButton = (ButtonPlus) rootView.findViewById(R.id.addPillsButton);

        ArrayList<String> lol = new ArrayList<>();

        addPillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                /*
				 * IMPORTANT: We use the "root frame" defined in
				 * "root_fragment.xml" as the reference to replace fragment
				 */
                trans.replace(R.id.root_frame, new AddPillsFragment());

				/*
				 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.commit();
            }
        });

        if (noPills) {
            pillInventoryLayout.setVisibility(View.GONE);
            addPillsButton.setVisibility(View.VISIBLE);
            FrameLayout wholeFrame = (FrameLayout) rootView.findViewById(R.id.wholeFrame);

            CreateNoDisplay.noDisplay(getResources().getString(R.string.noPills), wholeFrame, this);



        } else {
            pillInventoryLayout.setVisibility(View.VISIBLE);
            addPillsButton.setVisibility(View.VISIBLE);

            lol.add("MEDICINE1");
            lol.add("MEDICINE2");
            RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.pillInventory);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            InventoryAdapter adapter = new InventoryAdapter(getActivity(), lol, this);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);

        }


        return rootView;
    }
}