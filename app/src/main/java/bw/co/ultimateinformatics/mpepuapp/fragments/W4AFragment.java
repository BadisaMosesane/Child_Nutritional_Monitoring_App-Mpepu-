package bw.co.ultimateinformatics.mpepuapp.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import bw.co.ultimateinformatics.mpepuapp.R;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class W4AFragment extends Fragment implements OnChartGestureListener {

    private static final String ARG_AGE = "age";
    private static final String ARG_WEIGHT = "weight";


    private int mAge;
    private Double mWeight;


    public W4AFragment() {
        // Required empty public constructor
    }

    public static W4AFragment newInstance(String param_age, String param_weight) {
        W4AFragment fragment = new W4AFragment();
        Bundle args = new Bundle();
        args.putString(ARG_AGE, param_age);
        args.putString(ARG_WEIGHT, param_weight);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAge = getArguments().getInt(ARG_AGE);
            mWeight = getArguments().getDouble(ARG_WEIGHT);

            ArrayList xVals = new ArrayList<String>();
            for (int i = 0; i < 12; i++ ){

                String month = "Month "+ 1;

                xVals.add(month);


            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_w4_a, container, false);
    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
