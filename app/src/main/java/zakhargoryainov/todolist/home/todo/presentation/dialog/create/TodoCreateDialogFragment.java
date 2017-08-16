package zakhargoryainov.todolist.home.todo.presentation.dialog.create;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.blurry.Blurry;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.base.MvpDialogFragment;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.todo.presentation.listener.OnSuccessDismissListener;


public class TodoCreateDialogFragment extends MvpDialogFragment implements TodoCreateView {

    @InjectPresenter
    TodoCreatePresenter presenter;
    @BindView(R.id.edit_text_title)
    EditText titleEditText;
    @BindView(R.id.edit_text_body)
    EditText bodyEditText;
    @BindViews({R.id.view_priority_tier_1, R.id.view_priority_tier_2,
            R.id.view_priority_tier_3, R.id.view_priority_tier_4,})
    TextView[] priorityViews;
    @BindView(R.id.date_picker)
    DatePicker datePicker;
    @BindView(R.id.time_picker)
    TimePicker timePicker;
    @BindView(R.id.button_confirm)
    Button confirmButton;

    private TodoNotation notation;
    private OnSuccessDismissListener listener;
    private int priority = 1;

    public static TodoCreateDialogFragment newInstance(OnSuccessDismissListener listener) {
        TodoCreateDialogFragment fragment = new TodoCreateDialogFragment();
        fragment.listener = listener;
        return fragment;
    }

    @Override
    public void onSuccess() {
        listener.onSuccessfulDismiss();
        dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_todo_create, container, false);
        ButterKnife.bind(this, view);
        initPriorityPicker();
        TodoApplication.getAppComponent().inject(this);
        notation = presenter.getCurrentNotation();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
//                Bitmap bitmap = takeScreenShot(getActivity());
//                bitmap = fastblur(bitmap,15);
//                Drawable drawable = new BitmapDrawable(bitmap);
//                getDialog().getWindow().setBackgroundDrawable(drawable);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                Drawable drawable;
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener.onCancel();
    }

//    @Override
//    public void onCancel(DialogInterface dialog) {
//        super.onCancel(dialog);
//        listener.onCancel();
//    }

    @OnClick(R.id.button_confirm)
    public void onConfirmButtonClick() {
        // todo валидация данных
        notation.setTitle(titleEditText.getText().toString());
        notation.setBody(bodyEditText.getText().toString());
        notation.setPriority(priority);
        notation.setFormattedDeadline(String.format(Locale.ENGLISH, "%02d", datePicker.getDayOfMonth()) + "." +
                String.format(Locale.ENGLISH, "%02d", datePicker.getMonth()) + "." +
                datePicker.getYear() + "   " +
                String.format(Locale.ENGLISH, "%02d", timePicker.getCurrentHour()) + ":" +
                String.format(Locale.ENGLISH, "%02d", timePicker.getCurrentMinute()));
        Calendar calendar = new GregorianCalendar(
                datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        notation.setDeadlineTimestamp(calendar.getTimeInMillis());

        presenter.insertOrUpdateTodoNotation(notation);
    }

    private void initPriorityPicker() {
        for (int i = 0; i < 4; i++) {
            int color = findPriorityColor(i);
            GradientDrawable shape = new GradientDrawable();
            shape.setShape(GradientDrawable.OVAL);
            shape.setStroke(getResources().getDimensionPixelSize(R.dimen.stroke_width_common), color);
            priorityViews[i].setBackground(shape);
            priorityViews[i].setTextColor(color);
            priorityViews[i].setText(String.valueOf(i + 1));
            priorityViews[i].setOnClickListener(v -> {
                for (int j = 0; j < 4; j++) {
                    priorityViews[j].setScaleX(1);
                    priorityViews[j].setScaleY(1);
                    if (v.equals(priorityViews[j])) priority = j + 1;
                }
                v.setScaleX(1.2f);
                v.setScaleY(1.2f);
            });
        }
    }

    private int findPriorityColor(int priority) {
        switch (priority) {
            case 0:
                return ContextCompat.getColor(getContext(), R.color.priority_tier_1);
            case 1:
                return ContextCompat.getColor(getContext(), R.color.priority_tier_2);
            case 2:
                return ContextCompat.getColor(getContext(), R.color.priority_tier_3);
            case 3:
                return ContextCompat.getColor(getContext(), R.color.priority_tier_4);
            default:
                return -1;
        }
    }

    private static Bitmap takeScreenShot(Activity activity)
    {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();

        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height  - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }

    public Bitmap fastblur(Bitmap sentBitmap, int radius) {
        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = ( 0xff000000 & pix[yi] ) | ( dv[rsum] << 16 ) | ( dv[gsum] << 8 ) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }
}
