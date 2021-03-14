package com.example.eshopping.model.sms;

import java.util.List;

public class ForwardFlow {

	private PickupLocation pickup_location;
	private List<Shipments> shipments;
	
	public PickupLocation getPickup_location() {
		return pickup_location;
	}
	public void setPickup_location(PickupLocation pickup_location) {
		this.pickup_location = pickup_location;
	}
	public List<Shipments> getShipments() {
		return shipments;
	}
	public void setShipments(List<Shipments> shipments) {
		this.shipments = shipments;
	}
	
	
}
