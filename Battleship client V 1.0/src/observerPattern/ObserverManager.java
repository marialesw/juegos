package observerPattern;

import models.ShipOrientationInBoard;

public class ObserverManager {

	private IObserver observer;
	
	public ObserverManager(IObserver observer) {
		this.observer = observer;
	}
	
	public void showPanelLocateShips() {
		observer.showPanelLocateShips();
	}
	
	public void showShipInCorrectOrientationHorizontal(ShipOrientationInBoard orientation) {
		observer.showShipInCorrectOrientationHorizontal(orientation);
	}
	
	public void showShipInCorrectOrientationVertical(ShipOrientationInBoard orientation) {
		observer.showShipInCorrectOrientationVertical(orientation);
	}
	
	public void showBoardPanel() {
		observer.showBoardPanel();
	}
	
	public void showWaitingPanel() {
		observer.showWaitingPanel();
	}
}