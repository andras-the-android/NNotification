package com.example.andrasnemeth.nnotification.quicksettings;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.Toast;

import com.example.andrasnemeth.nnotification.R;

/**
 * Created by guni8 on 2016. 04. 05..
 */
public class NNotificationTileService extends TileService {

    @Override
    public void onTileAdded() {
        super.onTileAdded();
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
    }

    @Override
    public void onClick() {
        super.onClick();
        Tile tile = getQsTile();
        if (tile.getState() == Tile.STATE_ACTIVE) {
            tile.setState(Tile.STATE_INACTIVE);
            tile.setLabel(getString(R.string.notification_off));
            Toast.makeText(NNotificationTileService.this, "Tile turned off", Toast.LENGTH_SHORT).show();
        } else {
            tile.setState(Tile.STATE_ACTIVE);
            tile.setLabel(getString(R.string.notification_on));
            Toast.makeText(NNotificationTileService.this, "Tile turned on", Toast.LENGTH_SHORT).show();
        }
        tile.updateTile();
    }
}
