package com.example.andrasnemeth.nnotification.quicksettings

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.widget.Toast
import com.example.andrasnemeth.nnotification.R

/**
 * Created by guni8 on 2016. 04. 05..
 */
class NNotificationTileService : TileService() {
    override fun onTileAdded() {
        super.onTileAdded()
    }

    override fun onStartListening() {
        super.onStartListening()
    }

    override fun onClick() {
        super.onClick()
        val tile = qsTile
        if (tile.state == Tile.STATE_ACTIVE) {
            tile.state = Tile.STATE_INACTIVE
            tile.label = getString(R.string.notification_off)
            Toast.makeText(this@NNotificationTileService, "Tile turned off", Toast.LENGTH_SHORT).show()
        } else {
            tile.state = Tile.STATE_ACTIVE
            tile.label = getString(R.string.notification_on)
            Toast.makeText(this@NNotificationTileService, "Tile turned on", Toast.LENGTH_SHORT).show()
        }
        tile.updateTile()
    }
}