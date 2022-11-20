package de.christinecoenen.code.zapp.app.mediathek.ui.helper

import androidx.lifecycle.ViewModel
import de.christinecoenen.code.zapp.R
import de.christinecoenen.code.zapp.app.mediathek.controller.downloads.IDownloadController
import de.christinecoenen.code.zapp.models.shows.DownloadStatus
import de.christinecoenen.code.zapp.models.shows.MediathekShow
import de.christinecoenen.code.zapp.repositories.MediathekRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
class ShowMenuHelperViewModel(
	private val mediathekRepository: MediathekRepository,
	private val downloadController: IDownloadController
) : ViewModel() {

	fun getMenuItemsVisibility(show: MediathekShow): Flow<Map<Int, Boolean>> {
		return mediathekRepository
			.getPersistedShowByApiId(show.apiId)
			.mapLatest {
				mapOf(
					R.id.menu_share to true,
					R.id.menu_remove_download to
						(it.downloadStatus in listOf(
							DownloadStatus.FAILED,
							DownloadStatus.COMPLETED
						)),
					R.id.menu_cancel_download to
						(it.downloadStatus in listOf(
							DownloadStatus.QUEUED,
							DownloadStatus.DOWNLOADING
						)),
					R.id.menu_mark_unwatched to (it.playbackPosition > 0),
					R.id.menu_add_bookmark to !it.isBookmarked,
					R.id.menu_remove_bookmark to it.isBookmarked
				)
			}
			.distinctUntilChanged()
	}

	suspend fun deleteDownload(show: MediathekShow) {
		mediathekRepository
			.getPersistedShowByApiId(show.apiId)
			.firstOrNull()
			?.let {
				downloadController.deleteDownload(it.id)
			}
	}

	suspend fun cancelDownload(show: MediathekShow) {
		mediathekRepository
			.getPersistedShowByApiId(show.apiId)
			.firstOrNull()
			?.let {
				downloadController.stopDownload(it.id)
			}
	}

	suspend fun markUnwatched(show: MediathekShow) {
		mediathekRepository.resetPlaybackPosition(show.apiId)
	}

	suspend fun bookmark(show: MediathekShow) {
		mediathekRepository.setBookmarked(show.apiId, true)
	}

	suspend fun removeBookmark(show: MediathekShow) {
		mediathekRepository.setBookmarked(show.apiId, false)
	}
}
