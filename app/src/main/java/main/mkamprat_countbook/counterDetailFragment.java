package main.mkamprat_countbook;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import main.mkamprat_countbook.content.counterContent;

/**
 * A fragment representing a single counter detail screen.
 * This fragment is either contained in a {@link counterListActivity}
 * in two-pane mode (on tablets) or a {@link counterDetailActivity}
 * on handsets.
 */
public class counterDetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    private counterContent.counterObject mItem;

    public counterDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = counterContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
        }
    }

    public void editCounter(String counterName, int startingValue, int currentValue, String counterComment) {
        mItem.counterName = counterName;
        mItem.startingValue = startingValue;
        mItem.currentValue = currentValue;
        mItem.counterComment = counterComment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.counter_list_content, container, false);

        // Show counter values in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.counterName)).setText(mItem.counterName);
        }

        return rootView;
    }
}
