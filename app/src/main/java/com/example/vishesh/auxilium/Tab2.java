package com.example.vishesh.auxilium;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

/*package net.simplifiedcoding.androidtablayout;
*/
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
        import android.widget.MediaController;
        import android.widget.Toast;
        import android.widget.VideoView;



//Our class extending fragment
public class Tab2 extends Fragment
{

    private static String[] names=new String[]{"HEART ATTACK","FRACTURE","FIRE BURN","SEIZURE","CHOKING","BLEEDING","ASTHMA"};
    private View v;
    //Overriden method onCreateView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes

        v = inflater.inflate(R.layout.tab2, container, false);


        final ListView lv = (ListView) v.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, names);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String val=(String)lv.getItemAtPosition(position);
                switch(val)
                {
                    case "HEART ATTACK":
                      showInputDialog("rtsp://r2---sn-a5meknes.googlevideo.com/Cj0LENy73wIaNAmIeYLaLXQ4WBMYDSANFC1smYRXMOCoAUIASARgi--PsdGehqxWigELbHRKMkRsaG1HODQM/AFF248CF59F111504285AA2D0DDAA3F23E516575.269D4E1B5E96334C49F23F8896919DA841D4296F/yt6/1/video.3gp");
                        break;

                    case "FRACTURE":
                       showInputDialog("rtsp://r7---sn-a5m7lnes.googlevideo.com/Cj0LENy73wIaNAn271tYOOFTORMYDSANFC0ymoRXMOCoAUIASARgi--PsdGehqxWigELbHRKMkRsaG1HODQM/C0522280476EF309681DCFDE2D1EC390281A87F9.6FB1DDEC56FBE79D0117979B229697BCBB763ECB/yt6/1/video.3gp");
                        break;

                    case "FIRE BURN":
                      showInputDialog("rtsp://r5---sn-a5meknel.googlevideo.com/Cj0LENy73wIaNAmiO9X1PkPNNhMYDSANFC0FmoRXMOCoAUIASARgi--PsdGehqxWigELbHRKMkRsaG1HODQM/415A3184A485DD1D38BC836040E075B24CBF1751.A164ECDB6B37E87EFB2A25561287CFE43269B057/yt6/1/video.3gp");
                        break;

                    case "SEIZURE":
                        showInputDialog("rtsp://r1---sn-a5meknel.googlevideo.com/Cj0LENy73wIaNAlJwdc-SOEFoxMYDSANFC3DmYRXMOCoAUIASARgi--PsdGehqxWigELbHRKMkRsaG1HODQM/B7955BE057DFD47D5D4108CFF71764CD45C8516C.0DB502E112FB5458F68396CDD342729643154924/yt6/1/video.3gp");
                        break;

                    case "CHOKING":
                        showInputDialog("rtsp://r13---sn-a5m7ln7s.googlevideo.com/Cj0LENy73wIaNAks5LR7ZmUCSxMYDSANFC2Qm4RXMOCoAUIASARgi--PsdGehqxWigELbHRKMkRsaG1HODQM/11B558480EF858CE6455C0DEB68E77C9249C5B30.774629E653E4BA57D5E044BB912FDAD9A768DD92/yt6/1/video.3gp");
                        break;

                    case "BLEEDING":
                        showInputDialog("rtsp://r12---sn-a5m7znes.googlevideo.com/Cj0LENy73wIaNAkiJgFYSJjAyxMYDSANFC38toVXMOCoAUIASARgi--PsdGehqxWigELbHRKMkRsaG1HODQM/4CF2D7D38976A64B43E11FA8085F4A55F3581C03.54CCC8F533559ECF981061D1C27EB776C9E2D64B/yt6/1/video.3gp");
                        break;

                    case "ASTHMA":
                        showInputDialog("rtsp://r1---sn-a5m7lne6.googlevideo.com/Cj0LENy73wIaNAk_ytWdqLMssxMYDSANFC31nYRXMOCoAUIASARgi--PsdGehqxWigELbHRKMkRsaG1HODQM/8D5BD95CB5235542E9DFD7BB6D8248B9171CCBCE.90CEEA114AE2498F1ADF20290994436A23C67100/yt6/1/video.3gp");
                        break;
                }
            }
        });
        return v;
    }

    protected void showInputDialog(final String url)
    {
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        final View promptView = layoutInflater.inflate(R.layout.video_dialog_layout, null);

  //      VideoView videoView5=(VideoView) v.findViewById(R.id.videoView);
  //      videoView5.stopPlayback();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        VideoView videoView4 =(VideoView)promptView.findViewById(R.id.videoView2);
        //videoView4.stopPlayback();
        MediaController mediaController4= new MediaController(getActivity());
        mediaController4.setAnchorView(videoView4);
        Uri uri4=Uri.parse(url);
        videoView4.setMediaController(mediaController4);
        videoView4.setVideoURI(uri4);
        videoView4.requestFocus();
        videoView4.start();
//        Toast.makeText(getActivity(),"seizure",Toast.LENGTH_SHORT).show();

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

}