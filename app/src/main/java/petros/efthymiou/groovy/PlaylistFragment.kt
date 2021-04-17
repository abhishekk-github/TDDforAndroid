package petros.efthymiou.groovy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlaylistFragment : Fragment() {

    private lateinit var viewModel: PlaylistViewModel
    private lateinit var viewModelFactory: PlaylistViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist_list, container, false)

        viewModelFactory = PlaylistViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)

        viewModel.playlists.observe(this as LifecycleOwner) { playlists ->
            with(view as RecyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = PlaylistRecyclerViewAdapter(playlists)
            }
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}