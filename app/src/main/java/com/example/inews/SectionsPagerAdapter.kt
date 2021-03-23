package com.example.inews
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.inews.Fragment.BeritaIndonesiaFragment
import com.example.inews.Fragment.BeritaInggrisFragment
import com.example.inews.Fragment.bisnis.BeritaBisnis
import com.example.inews.Fragment.hiburan.BeritaHiburanFragment
import com.example.inews.Fragment.kesehatan.BeritaKesehatan
import com.example.inews.Fragment.sains.BeritaSainsFragment
import com.example.inews.Fragment.sports.BeritaSportsFragment
import com.example.inews.Fragment.teknologi.BeritaTeknologi


private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.hiburan, R.string.Olahraga, R.string.Sains, R.string.TEKNOLOGI, R.string.KESEHATAN, R.string.BISNIS, R.string.tab_text_2
)

class SectionsPagerAdapter (private val context: Context, fm: FragmentManager):

    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = BeritaIndonesiaFragment()
            1 -> fragment = BeritaHiburanFragment()
            2 -> fragment = BeritaSportsFragment()
            3 -> fragment = BeritaSainsFragment()
            4 -> fragment = BeritaTeknologi()
            5 -> fragment = BeritaKesehatan()
            6 -> fragment = BeritaBisnis()
//            7 -> fragment = BeritaInggrisFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 7
    }

}
