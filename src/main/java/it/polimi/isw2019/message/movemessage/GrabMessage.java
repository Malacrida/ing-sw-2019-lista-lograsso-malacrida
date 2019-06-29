package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;


public class GrabMessage extends MoveMessage {

    private int[] featuresAvailable;
    private boolean grabWeapon;
    private String[] weaponCardAvailable;
    private String ammoTileDescription;
    private String powerUpDescription;

    //powerUp è in tmpPowerUp nell'arena -> non ci dovrebbe essere nessun problema.
    //quando la si cambia -> togliere la carta dal mazzo, metterla nel deck del player, togliere la arta del deck del player
    //e metterla in powerUpDischarged!
    private int idPowerUp;


    public GrabMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public GrabMessage(String nicknamePlayer, int[] featuresAvailable) {
        super(nicknamePlayer);
        this.featuresAvailable = featuresAvailable;
    }

    public GrabMessage(String nicknamePlayer, String error,String[] weaponCardAvailable,int[] featuresAvailable,boolean grabWeapon, String ammoTileDescription, String powerUpDescription){
        super(nicknamePlayer,error);
        this.featuresAvailable = featuresAvailable;
        this.weaponCardAvailable = weaponCardAvailable;
        this.grabWeapon = grabWeapon;
        this.ammoTileDescription = ammoTileDescription;
        this.powerUpDescription = powerUpDescription;
    }

    public GrabMessage(String nicknamePlayer, String error) {
        super(nicknamePlayer, error);
    }

    public String[] getWeaponCardAvailable() {
        return weaponCardAvailable;
    }

    public void setWeaponCardAvailable(String[] weaponCardAvailable) {
        this.weaponCardAvailable = weaponCardAvailable;
    }

    public boolean isGrabWeapon() {
        return grabWeapon;
    }

    public void setGrabWeapon(Boolean grabWeapon){
        this.grabWeapon = grabWeapon;
    }


    public int[] getFeaturesAvailable() {
        return featuresAvailable;
    }

    public void setFeaturesAvailable(int[] featuresAvailable) {
        this.featuresAvailable = featuresAvailable;
    }

    public void setGrabWeapon(boolean grabWeapon) {
        this.grabWeapon = grabWeapon;
    }

    public String getAmmoTileDescription() {
        return ammoTileDescription;
    }

    public void setAmmoTileDescription(String ammoTileDescription) {
        this.ammoTileDescription = ammoTileDescription;
    }

    public String getPowerUpDescription() {
        return powerUpDescription;
    }

    public void setPowerUpDescription(String powerUpDescription) {
        this.powerUpDescription = powerUpDescription;
    }

    public int getIdPowerUp() {
        return idPowerUp;
    }

    public void setIdPowerUp(int idPowerUp) {
        this.idPowerUp = idPowerUp;
    }

    @Override
    public void accept(VisitorView visitorview) {
                visitorview.visitGrab(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendGrab(this);
    }


}
