package it.polimi.isw2019.utilities;

import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Database {

    private String dataBaseName = "/db.json";

    private File dataBaseFile;

    private JSONObject dataBaseJsonObject;

    /* Create an ArrayList of AmmoTiles from db.json */

    public Database(){
        InputStream is = getClass().getResourceAsStream(dataBaseName);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder result = new StringBuilder("");
        try {
            while ((line=br.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        dataBaseJsonObject = new JSONObject(result.toString());
    }


    public ArrayList<AmmoTile> loadAmmoTiles() {
        ArrayList<AmmoTile> ammoTiles = new ArrayList<>();

        JSONArray ammoTileObjects = dataBaseJsonObject.getJSONArray("ammotile");

        for (int i = 0; i < ammoTileObjects.length(); i++) {
            JSONObject currentToolCardObject = ammoTileObjects.getJSONObject(i);
            ammoTiles.add(new AmmoTile(currentToolCardObject.getInt("id"),
                    currentToolCardObject.getString("firstElement"),
                    currentToolCardObject.getString("secondElement"),
                    currentToolCardObject.getString("thirdElement")));

        }
        return ammoTiles;
    }

    /* Create an ArrayList of PowerUpCard from db.json */

    public ArrayList<PowerUpCard> loadPowerUpCards() {

        ArrayList<PowerUpCard> powerUpCards = new ArrayList<>();

        JSONArray powerUpCardObjects = dataBaseJsonObject.getJSONArray("powerup");

        for (int i = 0; i < powerUpCardObjects.length(); i++) {
            JSONObject currentToolCardObject = powerUpCardObjects.getJSONObject(i);
            powerUpCards.add(new PowerUpCard(currentToolCardObject.getInt("id"),
                    currentToolCardObject.getString("name"),
                    currentToolCardObject.getString("color"),
                    currentToolCardObject.getString("infoEffect")));

        }
        return powerUpCards;
    }
}