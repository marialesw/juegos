package observerPattern;

import models.ShipOrientationInBoard;

public interface IObserver{
	void showPanelLocateShips();
	void showShipInCorrectOrientationHorizontal(ShipOrientationInBoard orientation);
	void showShipInCorrectOrientationVertical(ShipOrientationInBoard orientation);
	void showBoardPanel();
	void showWaitingPanel();
}
