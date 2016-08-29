package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override

        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                mMediaPlayer.release();
            }
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("1.One", "१. एकम्", "ekam", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("2.Two", "२. द्वे ", "dve", R.drawable.number_two, R.raw.number_one));
        words.add(new Word("3.Three ", "३. त्रीणि, ", "trīṇi", R.drawable.number_three, R.raw.number_one));
        words.add(new Word("4.Four ", "४. चत्वारि ", "chatvāri", R.drawable.number_four, R.raw.number_one));
        words.add(new Word("5.Five ", "५. पञ्च ", "pañcha", R.drawable.number_five, R.raw.number_one));
        words.add(new Word("6.Six ", "६. षट  ", "shat", R.drawable.number_six, R.raw.number_one));
        words.add(new Word("7.Seven ", "७. सप्त  ", "Sapta", R.drawable.number_seven, R.raw.number_one));
        words.add(new Word("8.Eight ", "८. अष्ट ", "Ashtha", R.drawable.number_eight, R.raw.number_one));
        words.add(new Word("9.Nine ", "९. नव ", "nava", R.drawable.number_nine, R.raw.number_one));
        words.add(new Word("10.Ten ", "१०. दशन् ", "daśhan", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("11.Eleven ", "११.एकादशन्", "ekādaśhan", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("12.Tewlve ", "१२.द्वादशन् ", "dvādaśha", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("13.Thirteen ", "१३.त्रयोदशन् ", "trayodaśhan", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("14.Fourteen ", "१४.चतुर्दशन् ", "chaturthdashan", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("15.Fifteen ", "१५.पञ्चदशन् ", "pañchadaśhan", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("16.Sixteen ", "१६.षोडशन् ", "ṣhoḍaśhan", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("17.Seventeen ", "१७.सप्तदशन् ", "saptadaśhan", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("18.Eighteen ", "१८.अष्टादशन्  ", "aṣhṭhādaśhan", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("19.Ninteen ", "१९.नवदशन् \n     अथवा एकोनविंशति \n     अथवा ऊनविंशति \n     अथवा एकान्नविंशति ", "navadaśhan or\n ekonaviṁśhati or\n ūnaviṁśhati or\n ekānnaviṁśhati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("20.Twenty ", "२०.विंशति", "viṁśhati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("20. Twenty", "२०. विंशति", "viṁśhati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("21. Twenty One ", "२१. एकविंशति", "ekaviṁśhati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("22. Twenty Two ", "२२. द्वाविंशति", "dvāviṁśhati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("23. Twenty Three ", "२३. त्रयोविंशति", "trayoviṁśhati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("24. Twenty Four ", "२४. चतुर्विंशति", "chaturviṁśhati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("25. Twenty Five ", "२५. पञ्चविंशति", "pañchaviṁśhati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("26. Twenty Six ", "२६. षड्विंशति", "ṣhaḍviṁśhati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("27. Twenty Seven ", "२७. सप्तविंशति", "saptaviṁśhati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("28. Twenty Eight ", "२८. अष्टाविंशति", "aṣhṭāviṁśhati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("29. Twenty Nine	", "२९. नवविंशति \n     अथवा एकोनत्रिंशत् \n     अथवा  ऊनत्रिंशत् \n     अथवा एकान्नत्रिंशत्", "navaviṁśhati \n     अथवाekonatriṁśhat or\n ūnatriṁśhat or\n ekānnatriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("30. Thirty ", "३०. त्रिंशत्", "triṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("31. Thirty One ", "३१.एकत्रिंशत्", "ekatriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("32. Thirty Two ", "३२.द्वात्रिंशत्", "dvātriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("33. Thirty Three ", "३३. त्रयस्त्रिंशत्", "trayastriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("34. Thirty Four", "३४.चतुस्त्रिंशत्", "chatustriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("35. Thirty Five", "३५.पञ्चत्रिंशत्", "pañchatriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("36. Thirty Six", "३६.षट्त्रिंशत्", "ṣhaṭtriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("37. Thirty Seven ", "३७.सप्तत्रिंशत्", "saptatriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("38. Thirty Eight ", "३८.अष्टात्रिंशत्", "aṣhṭātriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("39. Thirty Nine", "३९.नवत्रिंशत् \n     अथवा एकोनचत्वारिंशत् \n     अथवा  ऊनचत्वारिंशत् \n     अथवा  एकान्नचत्वारिंशत्", "navatriṁśhat or\n ekonachatvāriṁśhat or\n ūnachatvāriṁśhat or\n ekānnachatvāriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("40. Forty ", "४०.चत्वारिंशत्", "chatvāriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("41. Forty One ", "४१	.एकचत्वारिंशत्", "ekachatvāriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("42. Forty Two ", "४२.द्वाचत्वारिंशत्", "dvāchatvāriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("43. Forty Three	 ", "४३.त्रयश्चत्वारिंशत् \n     अथवा त्रिचत्वारिंशत्	 ", "trayaśchatvāriṁśhat or\n trichatvāriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("44. Forty Four", "४४.चतुश्चत्वारिंशत्", "chatuśchatvāriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("45. Forty Five", "४५.पञ्चचत्वारिंशत्", "pañchachatvāriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("46. Forty Six", "४६.षट्चत्वारिंशत्", "ṣhaṭchatvāriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("47. Forty Seven", "४७.सप्तचत्वारिंशत्", "saptachatvāriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("48. Forty Eight", "४८.अष्टाचत्वारिंशत् \n     अथवा अष्टचत्वारिंशत्", "aṣhṭāchatvāriṁśhat or\n aṣhṭachatvāriṁśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("49. Forty Nine", "४९.नवचत्वारिंशत् \n     अथवा एकोनपञ्चाशत् \n     अथवा ऊनपञ्चाशत् \n     अथवा एकान्नपञ्चाशत्", "navachatvāriṁśhat or\n ekonapañcāśhat or\n ūnapañcāśhat or\n ekānnapañcāśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("50. Fifty ", "५०.पञ्चाशत्", "pañcāśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("51. Fifty One ", "५१.एकपञ्चाशत्", "ekapañcāśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("52. Fifty Two", "५२.द्वापञ्चाशत् \n     अथवा द्विपञ्चाशत्", "dvāpañcāśhat or\n dvipañcāśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("53. Fifty Three", "५३.त्रयःपञ्चाशत् \n     अथवा त्रिपञ्चाशत्", "trayaḥpañcāśhat or\n tripañcāśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("54. Fifty Four", "५४.चतुःपञ्चाशत्", "chatuḥpañcāśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("55. Fifty Five", "५५.पञ्चपञ्चाशत्", "pañchapañcāśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("56. Fifty Six", "५६.षट्पञ्चाशत्", "ṣhaṭpañcāśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("57. Fifty Seven", "५७.सप्तपञ्चाशत्", "saptapañcāśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("58. Fifty Eight", "५८.अष्टापञ्चाशत् \n     अथवा अष्टपञ्चाशत्", "aṣhṭāpañcāśhat or\n aṣhṭapañcāśhat", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("59. Fifty Nine", "५९.नवपञ्चाशत् \n     अथवा एकोनषष्टि \n     अथवा  ऊनषष्टि \n     अथवा एकान्नषष्टि", "navapañcāśhat or\n ekonaṣhaṣhṭi or\n ūnaṣhaṣhṭi or\n ekānnaṣhaṣhṭi", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("60. Sixty", "६०.षष्टि", "ṣhaṣhṭí", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("61. Sixty One", "६९.एकषष्टि", "ekaṣhaṣhṭi", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("62. Sixty Two", "६२.द्वाषष्टि \n     अथवा द्विषष्टि", "dvāṣhaṣhṭi or\n dviṣhaṣhṭi", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("63. Sixty Three", "६३.त्रयःषष्टि \n     अथवा त्रिषष्टि", "trayaḥṣhaṣhṭi or\n triṣhaṣhṭi", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("64. Sixty Four", "६४.चतुष्षष्टि", "chatuṣṣhaṣhṭi", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("65. Sixty Five", "६५.पञ्चषष्टि", "pañchaṣhaṣhṭi", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("66. Sixty Six", "६६.षट्षष्टि", "ṣhaṭṣhaṣhṭi", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("67. Sixty Seven", "६७.सप्तषष्टि", "saptaṣhaṣhṭi", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("68. Sixty Eight", "६८.अष्टाषष्टि \n     अथवा अष्टषष्टि", "aṣhṭāṣhaṣhṭi or\n aṣhṭaṣhaṣhṭi", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("69. Sixty Nine", "६९.नवषष्टि \n     अथवा एकोनसप्तति \n     अथवा ऊनसप्तति \n     अथवा एकान्नसप्तति", "navaṣhaṣhṭi or\n ekonasaptati or\n ūnasaptati or\n ekānnasaptati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("70. Seventy", "७०.सप्तति", "saptati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("71. Seventy One", "७१.एकसप्तति", "ekasaptati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("72. Seventy Two", "७२.द्वासप्तति \n     अथवा द्विसप्तति", "dvāsaptati or\n dvisaptati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("73. Seventy Three", "७३.त्रयस्सप्तति \n     अथवा त्रिसप्तति", "trayassaptati or\n trisaptati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("74. Seventy Four", "७४.चतुस्सप्तति", "chatussaptati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("75. Seventy Five", "७५.पञ्चसप्तति", "pañchasaptati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("76. Seventy Six", "७६.षट्सप्तति", "ṣhaṭsaptati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("77. Seventy Seven", "७७.सप्तसप्तति", "saptasaptati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("78. Seventy Eight", "७८.अष्टासप्तति \n     अथवा अष्टसप्तति", "aṣhṭāsaptati or\n aṣhṭasaptati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("79. Seventy Nine", "७९.नवसप्तति \n     अथवा एकोनाशीति \n     अथवा ऊनाशीति \n     अथवा एकान्नाशीति", "navasaptati or\n ekonāśhīti or\n ūnāśhīti or\n ekānnāśhīti", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("80. Eighty", "८०.अशीति", "aśhīti", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("81. Eighty One", "८९.एकाशीति", "ekāśhīti", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("82. Eighty Two", "८२.द्व्यशीति", "dvyaśhīti", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("83. Eighty Three", "८३.त्र्यशीति", "tryaśhīti", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("84. Eighty Four", "८४.चतुरशीति", "chaturaśhīti", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("85. Eighty Five", "८५.पञ्चाशीति", "pañcāśhīti", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("86. Eighty Six", "८६.षडशीति", "ṣhaḍaśhīti", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("87. Eighty Seven", "८७.सप्ताशीति", "saptāśhīti", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("88. Eighty Eight", "८८.अष्टाशीति", "aṣhṭāśhīti", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("89. Eighty Nine", "८९.नवाशीति \n     अथवा एकोननवति \n     अथवा ऊननवति \n     अथवा एकान्ननवति", "návāśhīti or\n ekonanavati or\n ūnanavati or\n ekānnanavati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("90. Ninety", "९०.नवति", "navati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("91. Ninety One", "९१.एकनवति", "ekanavati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("92. Ninety Two", "९२.द्वानवति \n     अथवा द्विनवति", "dvānavati or\n dvinavati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("93. Ninety Three", "९३.त्रयोनवति \n     अथवा त्रिनवति", "trayonavati or\n trinavati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("94. Ninety Four", "९४.चतुर्नवति", "chaturnavati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("95. Ninety Five", "९५.पञ्चनवति", "pañchanavati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("96. Ninety Six", "९६.षण्णवति", "ṣhaṇṇavati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("97. Ninety Seven", "९७.सप्तनवति", "saptanavati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("98. Ninety Eight", "९८.अष्टानवति \n     अथवा अष्टनवति", "aṣhṭānavati or\n aṣhṭanavati", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("99. Ninety Nine", "९९.नवनवति \n     अथवा एकोनशत      अथवाऊनशत \n     अथवा एकान्नशत", "navanavati or\n ekonaśhata or\n ūnaśhata or\n ekānnaśhata", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("100. One Hundred", "१००.शत", "śhata", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("200. Two Hundred", "२००.द्विशत \n     अथवा द्वेशते", "dviśhata or\n dveśhate", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("300. Three Hundred", "३००.त्रिशत", "triśhata", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("400. Four Hundred", "४००.चतुःशत", "chatuḥśhata", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("500. Five Hundred", "५००.पञ्चशत", "pañchaśhata", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("600. Six Hundred", "६००.षट्शत", "ṣhaṭśhata", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("700. Seven Hundred", "७००.सप्तशत", "saptaśhata", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("800. Eight Hundred", "८००.अष्टशत", "aṣhṭaśhata", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("900. Nine Hundred", "९००.नवशत", "navaśhata", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("1,000. One Thousand", "१०००.सहस्र \n     अथवा दशशत", "sahasra or\n daśhaśhata", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("2,000. Two Thousand", "२०००.द्विसहस्र", "dvisahasra", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("3,000. Three Thousand", "३०००.त्रिसहस्र", "trisahasra", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("4,000. Four Thousand", "४०००.चतुःसहस्र", "chatuḥsahasra", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("5,000. Five Thousand", "५०००.पञ्चसहस्र", "pañchasahasra", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("6,000. Six Thousand", "६०००.षट्सहस्र", "ṣhaṭsahasra", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("7,000. Seven Thousand", "७०००.सप्तसहस्र", "saptasahasra", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("8,000. Eight Thousand", "८०००.अष्टसहस्र", "aṣhṭasahasra", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("9,000. Nine Thousand", "९०००.नवसहस्र", "navasahasra", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("10,000.  Ten Thousand", "१००००.अयुत", "ayuta", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("100,000. One Lakh", "१०००००.लक्ष", "lakṣha or\n lakṣā", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("1,000,000. Ten Lakh", "१००००००.प्रयुत", "prayuta", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("10,000,000. One Crore", "१०००००००.कोटि", "koṭi", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("100,000,000. Ten Crore", "१००००००००.अर्बुद", "árbuda", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("1,000,000,000. One Trillion", "१०००००००००.अब्ज", "abja", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("10,000,000,000. Ten Trillion", "१००००००००००.खर्व", "kharva", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("100,000,000,000. Hundred Trillion", "१०००००००००००.निखर्व", "nikharva", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("1,000,000,000,000. One Quadrillion", "१००००००००००००.महापद्म", "mahāpadma", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("10,000,000,000,000. Ten Quadrillion", "१०००००००००००००.शङ्कु", "śhaṅku", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("100,000,000,000,000. Hundred Quadrillion", "१००००००००००००००.जलधि", "jaladhi", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("1,000,000,000,000,000. One Quyillion", "१०००००००००००००००.अन्त्य", "antya", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("10,000,000,000,000,000. Ten Quyillion", "१००००००००००००००००.मध्य", "madhya", R.drawable.color_gray, R.raw.number_one));
        words.add(new Word("100,000,000,000,000,000. Hundred Quyillion", "१०००००००००००००००००.परार्ध", "parārdha", R.drawable.color_gray, R.raw.number_one));


        // Create an {@link WordAdapter}, whose data source is a list_item of {@link Word}s. The
        // adapter knows how to create list_item items for each item in the list_item.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);


                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.


                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}